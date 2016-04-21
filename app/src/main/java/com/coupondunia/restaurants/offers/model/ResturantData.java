package com.coupondunia.restaurants.offers.model;

import java.util.List;

public class ResturantData {
    private String NeighbourhoodName;

    private String CityName;

    private String Timings;

    private String NumCoupons;


    private String LogoURL;

    private String SubFranchiseID;

    private String BrandID;


    private String Latitude;

    private String NeighbourhoodID;

    private String CoverURL;

    private String CityID;

    private String OutletName;

    private String Distance;

    private String PhoneNumber;


    private String OutletID;

    private String Address;

    private String Longitude;

    private String OutletURL;

    private String BrandName;

    private double distanceFromCurrentLocation;

    public double getDistanceFromCurrentLocation() {
        return distanceFromCurrentLocation;
    }

    public void setDistanceFromCurrentLocation(double distanceFromCurrentLocation) {
        this.distanceFromCurrentLocation = distanceFromCurrentLocation;
    }

    private List<Categories> Categories;

    public List<Categories> getCategories() {
        return Categories;
    }

    public void setCategories(List<Categories> Categories) {
        this.Categories = Categories;
    }

    public String getNeighbourhoodName() {
        return NeighbourhoodName;
    }

    public void setNeighbourhoodName(String NeighbourhoodName) {
        this.NeighbourhoodName = NeighbourhoodName;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String CityName) {
        this.CityName = CityName;
    }

    public String getTimings() {
        return Timings;
    }

    public void setTimings(String Timings) {
        this.Timings = Timings;
    }

    public String getNumCoupons() {
        return NumCoupons;
    }

    public void setNumCoupons(String NumCoupons) {
        this.NumCoupons = NumCoupons;
    }


    public String getLogoURL() {
        return LogoURL;
    }

    public void setLogoURL(String LogoURL) {
        this.LogoURL = LogoURL;
    }

    public String getSubFranchiseID() {
        return SubFranchiseID;
    }

    public void setSubFranchiseID(String SubFranchiseID) {
        this.SubFranchiseID = SubFranchiseID;
    }

    public String getBrandID() {
        return BrandID;
    }

    public void setBrandID(String BrandID) {
        this.BrandID = BrandID;
    }


    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String Latitude) {
        this.Latitude = Latitude;
    }

    public String getNeighbourhoodID() {
        return NeighbourhoodID;
    }

    public void setNeighbourhoodID(String NeighbourhoodID) {
        this.NeighbourhoodID = NeighbourhoodID;
    }

    public String getCoverURL() {
        return CoverURL;
    }

    public void setCoverURL(String CoverURL) {
        this.CoverURL = CoverURL;
    }

    public String getCityID() {
        return CityID;
    }

    public void setCityID(String CityID) {
        this.CityID = CityID;
    }

    public String getOutletName() {
        return OutletName;
    }

    public void setOutletName(String OutletName) {
        this.OutletName = OutletName;
    }

    public String getDistance() {
        return Distance;
    }

    public void setDistance(String Distance) {
        this.Distance = Distance;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }


    public String getOutletID() {
        return OutletID;
    }

    public void setOutletID(String OutletID) {
        this.OutletID = OutletID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String Longitude) {
        this.Longitude = Longitude;
    }

    public String getOutletURL() {
        return OutletURL;
    }

    public void setOutletURL(String OutletURL) {
        this.OutletURL = OutletURL;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }
}
