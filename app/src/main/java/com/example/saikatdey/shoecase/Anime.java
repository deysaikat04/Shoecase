package com.example.saikatdey.shoecase;

public class Anime {
    private int id;
    private String name, price, category, size, image, path, rating;
    private String inr = "INR: ";


    public  Anime(){   }

    public Anime(int id, String name, String price, String category, String size, String path ,String rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.size = size;
        this.path = path;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
