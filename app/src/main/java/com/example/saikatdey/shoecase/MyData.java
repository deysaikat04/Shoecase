package com.example.saikatdey.shoecase;

class MyData {
    private int id;
    private String uname, itemName, date, grand, status, path, price;

    public  MyData(){   }

    public MyData(int id, String uname, String itemName, String date, String grand, String status, String path, String price) {
        this.id = id;
        this.uname = uname;
        this.itemName = itemName;
        this.date = date;
        this.grand = grand;
        this.status = status;
        this.path = path;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGrand() {
        return grand;
    }

    public void setGrand(String grand) {
        this.grand = grand;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
