package com.example.bottomnaviagtion;

public class inbuxModel {
    private String itemName;
    private String itemDesc;
    private int id;
    private boolean isChecked;

    public inbuxModel(String itemName  , String itemDesc , int id) {
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.id = id;

    }

    public String getItemName() {
        return itemName;
    }
    public int getId() {
        return id;
    }
    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
