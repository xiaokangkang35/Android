package com.example.course8;

import java.util.ArrayList;

public class CateBean {
    private String cate_name;
    private ArrayList<CateItem> cateItems;

    public CateBean(ArrayList<CateItem> cateItems,String cate_name) {
        this.cate_name = cate_name;
        this.cateItems = cateItems;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public ArrayList<CateItem> getCateItems() {
        return cateItems;
    }

    public void setCateItems(ArrayList<CateItem> cateItems) {
        this.cateItems = cateItems;
    }
}
