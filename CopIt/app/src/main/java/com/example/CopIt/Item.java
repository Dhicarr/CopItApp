package com.example.CopIt;

import java.util.HashMap;
import java.util.Map;

public class Item {
    public String id;
    public String sid; //Id of seller
    public String title;
    public double price;
    public String category;
    public String description;
    public String imgsrc;

    public Item() {
        this.id = null;
        this.sid = null; //Id of seller
        this.title = null;
        this.price = 0.00;
        this.category = null;
        this.description = null;
        this.imgsrc = null;
    }

    public Item(String sid, String title, Double price, String category, String description, String imgsrc) {
        this.sid = sid;
        this.title = title;
        this.price = price;
        this.category = category;
        this.description = description;
        this.imgsrc = imgsrc;
    }

    @Override
    public String toString() {
        return "Item " + this.id + " Seller " + this.sid + " Price: " + this.price + " Category: "
                + this.category + " Description: " + this.description + " Imgsrc " + this.imgsrc;
    }

    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("sid", this.sid);
        params.put("title", this.title);
        params.put("price", Double.toString(this.price));
        params.put("category", this.category);
        params.put("description", this.description);
        params.put("imgsrc", this.imgsrc);
        return params;
    }

}
