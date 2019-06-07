package com.example.harshpandey.binge_assignment;

public class menu_items {
    private String itemName;

    // distance if you want to show
    private String itemPrice;

    private String itemDecsription;

    public menu_items(String vName,String price, String description)
    {
        itemName = vName;
        itemDecsription = description;
        itemPrice = price;
       //mImageResourceId = imageResourceId; image not added
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getItemDecsription() {
        return itemDecsription;
    }


}
