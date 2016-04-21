package com.coupondunia.restaurants.offers.fragment;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.coupondunia.restaurants.R;
import com.coupondunia.restaurants.offers.GPSTracker;
import com.coupondunia.restaurants.offers.adapter.ResultsAdapter;
import com.coupondunia.restaurants.offers.model.Data;
import com.coupondunia.restaurants.offers.model.RestaurantInfo;
import com.coupondunia.restaurants.offers.model.ResturantData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;


public class NearByRestaurantFragment extends Fragment {

    private final static String URL = "http://staging.couponapitest.com/task_data.txt";
    private final static String TAG = NearByRestaurantFragment.class.getSimpleName();
    private ArrayList<ResturantData> list;
    private ProgressBar progressBar;
    private TextView emptytext;
    private ListView listView;
    private ResultsAdapter adapter;
    private TextView presentAddress;

    public static Fragment newInstance(int position) {
        NearByRestaurantFragment fragment = new NearByRestaurantFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public NearByRestaurantFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_near_by_restaurant, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        emptytext = (TextView) view.findViewById(R.id.emptyText);
        listView = (ListView) view.findViewById(R.id.listview);
        presentAddress = (TextView) view.findViewById(R.id.streetText);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e(TAG, s);
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(RestaurantInfo.class, new ResultsDeserializer());
                Gson gson = gsonBuilder.create();
                RestaurantInfo restaurantInfo = gson.fromJson(s, RestaurantInfo.class);
                createList(restaurantInfo);
                Log.d(TAG, restaurantInfo.getData() + "");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d(TAG, volleyError.toString());
                list = new ArrayList<>();
                emptytext.setText("Connection problem");
                emptytext.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });
        request.setTag(TAG);
        requestQueue.add(request);
    }

    private void createList(RestaurantInfo restaurantInfo) {
        HashMap<String, ResturantData> resturantDataHashMap = restaurantInfo.getData().getList();
        list = new ArrayList<ResturantData>(resturantDataHashMap.values());
        adapter = new ResultsAdapter(getActivity(), list);
        listView.setEmptyView(emptytext);
        listView.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
        sortByDistance();
    }

    private void sortByDistance() {
        try {
            GPSTracker gpsTracker = new GPSTracker(getActivity());
            if (gpsTracker.canGetLocation()) {
                Location location = gpsTracker.getLocation();
                Geocoder   geocoder = new Geocoder(getActivity(), Locale.getDefault());
                List<Address>   addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                presentAddress.setText(address + "," + city);

                for (ResturantData resturantData : list) {
                    Location location1 = new Location(resturantData.getBrandName());
                    location1.setLatitude(Double.parseDouble(resturantData.getLatitude()));
                    location1.setLongitude(Double.parseDouble(resturantData.getLongitude()));
                    resturantData.setDistanceFromCurrentLocation(location.distanceTo(location1));
                }
                Collections.sort(list, new Comparator<ResturantData>() {
                    @Override
                    public int compare(ResturantData lhs, ResturantData rhs) {
                        return (int) (lhs.getDistanceFromCurrentLocation() - rhs.getDistanceFromCurrentLocation());
                    }
                });
                adapter.notifyDataSetChanged();

            } else {
                gpsTracker.showSettingsAlert();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static class ResultsDeserializer implements JsonDeserializer<RestaurantInfo> {

        @Override
        public RestaurantInfo deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
                throws JsonParseException {
            final JsonObject jsonObject = json.getAsJsonObject();
            JsonElement jsonElement = jsonObject.get("data");
            JsonObject object = jsonElement.getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> entrySet = object.entrySet();
            Gson gson = new Gson();
            HashMap<String, ResturantData> dataHashMap = new HashMap<>();
            for (Map.Entry<String, JsonElement> stringJsonElementEntry : entrySet) {
                ResturantData resturantData = gson.fromJson(stringJsonElementEntry.getValue(), ResturantData.class);
                dataHashMap.put(stringJsonElementEntry.getKey(), resturantData);
            }
            Data data = new Data(dataHashMap);
            RestaurantInfo info = new RestaurantInfo(data);
            return info;
        }
    }
}
