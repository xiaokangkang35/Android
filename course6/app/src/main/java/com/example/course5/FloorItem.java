package com.example.course5;

import java.util.ArrayList;

public class FloorItem {
    private String img_src;
    private ArrayList<Product> products;

    public FloorItem(String img_src, ArrayList<Product> products) {
        this.img_src = img_src;
        this.products = products;
    }

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
