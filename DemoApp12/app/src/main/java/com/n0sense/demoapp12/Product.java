package com.n0sense.demoapp12;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Product {
    private String goodsName;
    private String goodsSmallLogo;

    public Product(String goodsName, String goodsSmallLogo) {
        this.goodsName = goodsName;
        this.goodsSmallLogo = goodsSmallLogo;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsSmallLogo() {
        return goodsSmallLogo;
    }

    public void setGoodsSmallLogo(String goodsSmallLogo) {
        this.goodsSmallLogo = goodsSmallLogo;
    }
}
