package com.example.form;

// 操作を行うデータのsetterとgetterを記述
// IDはパス変数から取得するので不要

public class ItemForm {

    private String name;

    private Integer price;


    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getPrice() {
        return this.price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
}