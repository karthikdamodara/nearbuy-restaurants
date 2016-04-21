package com.coupondunia.restaurants.offers.model;

public class Categories {
    private String Name;

    private String CategoryType;

    private String ParentCategoryID;

    private String OfflineCategoryID;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getCategoryType() {
        return CategoryType;
    }

    public void setCategoryType(String CategoryType) {
        this.CategoryType = CategoryType;
    }

    public String getParentCategoryID() {
        return ParentCategoryID;
    }

    public void setParentCategoryID(String ParentCategoryID) {
        this.ParentCategoryID = ParentCategoryID;
    }

    public String getOfflineCategoryID() {
        return OfflineCategoryID;
    }

    public void setOfflineCategoryID(String OfflineCategoryID) {
        this.OfflineCategoryID = OfflineCategoryID;
    }

}
