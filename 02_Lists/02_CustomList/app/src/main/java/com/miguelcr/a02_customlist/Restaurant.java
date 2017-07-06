package com.miguelcr.a02_customlist;

/**
 * Created by miguelcampos on 6/7/17.
 */

public class Restaurant {
    String photoPath;
    String name;
    String telephone;
    float rating;

    public Restaurant(String photoPath, String name, String telephone, float rating) {
        this.photoPath = photoPath;
        this.name = name;
        this.telephone = telephone;
        this.rating = rating;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
