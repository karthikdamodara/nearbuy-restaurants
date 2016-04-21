package com.coupondunia.restaurants.offers.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.coupondunia.restaurants.R;
import com.coupondunia.restaurants.offers.model.Categories;
import com.coupondunia.restaurants.offers.model.ResturantData;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ResultsAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private Context context;
    private ArrayList<ResturantData> list;

    public ResultsAdapter(Context context, ArrayList<ResturantData> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
            holder = ViewHolder.create(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final ResturantData resturantData = list.get(position);
        holder.name.setText(resturantData.getBrandName());
        if (resturantData.getDistanceFromCurrentLocation() == 0) {
            holder.streetText.setText(resturantData.getDistance() + " " + resturantData.getNeighbourhoodName() + "," + resturantData.getCityName());
        } else {
            DecimalFormat df = new DecimalFormat("#");
            holder.streetText.setText(df.format(resturantData.getDistanceFromCurrentLocation()) + " m, " + " " + resturantData.getNeighbourhoodName() + "," + resturantData.getCityName());
            holder.streetText.setClickable(true);
            holder.streetText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 openGoogleDirectionsApp(resturantData.getLatitude(),resturantData.getLongitude());
                }
            });
        }
        List<Categories> categories = resturantData.getCategories();
        holder.offersLayout.removeAllViews();
        if (categories != null && !categories.isEmpty()) {
            for (Categories category : categories) {
                TextView textView = new TextView(context);
                textView.setTextAppearance(context, R.style.TextAppearance_AppCompat_Medium);
                textView.setText(" " + context.getResources().getString(R.string.dotted_line) + category.getName());
                textView.setTextColor(Color.BLACK);
                textView.setMaxLines(1);
                holder.offersLayout.addView(textView);
            }
        }
        holder.offersText.setText(resturantData.getNumCoupons() + " Offers");
        Glide.with(context).load(resturantData.getLogoURL()).centerCrop().placeholder(R.drawable.ic_launcher)
                .crossFade().into(holder.logo);


        return convertView;
    }


    public void openGoogleDirectionsApp(String latitude, String longitude) {
        try {
            if (latitude == null || longitude == null) {
                Toast.makeText(context, "Sorry,Address not available", Toast.LENGTH_SHORT).show();
                return;
            }
            String navigationUrl = "google.navigation:q=" + latitude + "," + longitude +
                    "&mode=d";
            Uri gmmIntentUri = Uri.parse(navigationUrl);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            context.startActivity(mapIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static class ViewHolder {
        public final ImageView logo;
        public final TextView name;
        public final TextView offersText;
        public final ImageView favouratesIcon;
        public final LinearLayout offersLayout;
        public final TextView streetText;

        private ViewHolder(ImageView logo, TextView name, ImageView favouratesIcon, LinearLayout offersLayout, TextView streetText, TextView offersText) {
            this.logo = logo;
            this.name = name;
            this.favouratesIcon = favouratesIcon;
            this.offersLayout = offersLayout;
            this.streetText = streetText;
            this.offersText = offersText;
        }

        public static ViewHolder create(View rootView) {
            ImageView logo = (ImageView) rootView.findViewById(R.id.logo);
            TextView name = (TextView) rootView.findViewById(R.id.name);
            ImageView favouratesIcon = (ImageView) rootView.findViewById(R.id.favouratesIcon);
            LinearLayout offersLayout = (LinearLayout) rootView.findViewById(R.id.offersLayout);
            TextView streetText = (TextView) rootView.findViewById(R.id.streetText);
            TextView offersText = (TextView) rootView.findViewById(R.id.offersText);
            return new ViewHolder(logo, name, favouratesIcon, offersLayout, streetText, offersText);
        }
    }

}
