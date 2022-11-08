package com.example.course8;

import java.util.ArrayList;

public class CateItem {
    private String cat_name;
    private ArrayList<Product> product;

    public CateItem(String cat_name, ArrayList<Product> product) {
        this.cat_name = cat_name;
        this.product = product;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public ArrayList<Product> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<Product> product) {
        this.product = product;
    }
}
