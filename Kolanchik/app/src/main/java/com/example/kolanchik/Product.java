package com.example.kolanchik;

public class Product {
    String name;
    String description;
    String date;
    String img;
    Integer price;
    Integer quentity;

    public Product(){}

    public Product(String name, String description, String date, String img, Integer price, Integer quentity) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.img = img;
        this.price = price;
        this.quentity = quentity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuentity() {
        return quentity;
    }

    public void setQuentity(Integer quentity) {
        this.quentity = quentity;
    }
}
