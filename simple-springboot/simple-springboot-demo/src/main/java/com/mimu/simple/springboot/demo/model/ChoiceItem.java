package com.mimu.simple.springboot.demo.model;

import lombok.Data;

/**
 author: mimu
 date: 2020/3/5
 */
@Data
public class ChoiceItem {
    private int itemId;
    private String itemKey;
    private String itemValue;
    private int itemType;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
