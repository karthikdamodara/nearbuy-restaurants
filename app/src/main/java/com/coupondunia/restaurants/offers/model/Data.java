package com.coupondunia.restaurants.offers.model;

import java.util.HashMap;
import java.util.Map;

public class Data {


    public Data(Map<String, ResturantData> list) {
        this.list = (HashMap<String, ResturantData>) list;
    }

    private HashMap<String, ResturantData> list;

    public HashMap<String, ResturantData> getList() {
        return list;
    }

    public void setList(HashMap<String, ResturantData> list) {
        this.list = list;
    }


    public Data() {
    }


}
