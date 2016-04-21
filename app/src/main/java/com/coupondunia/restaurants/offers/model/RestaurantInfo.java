package com.coupondunia.restaurants.offers.model;

public class RestaurantInfo {


    private Status status;

    private Data data;

    public RestaurantInfo(Data data) {
        this.data = data;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public class Status {
        private String message;

        private String rcode;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getRcode() {
            return rcode;
        }

        public void setRcode(String rcode) {
            this.rcode = rcode;
        }

    }


}

