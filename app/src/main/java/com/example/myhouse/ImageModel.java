/**
 * Image class model
 */

package com.example.myhouse;

public class ImageModel {
    String City;
    String Description;
    String Price;
    String image;

    // Constructor
    public ImageModel() {
    }

    public ImageModel(String city, String description, String price, String image) {
        City = city;
        Description = description;
        Price = price;
        this.image = image;
    }

    // Getters and setters
    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
