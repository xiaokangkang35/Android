package com.example.course5;

public class Product {
    private String name;
    private String image_src;

    public Product(String name, String image_src) {
        this.name = name;
        this.image_src = image_src;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_src() {
        return image_src;
    }

    public void setImage_src(String image_src) {
        this.image_src = image_src;
    }

}
