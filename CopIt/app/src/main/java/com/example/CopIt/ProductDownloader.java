package com.example.CopIt;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProductDownloader {

    /**
     * Loads items from DB into MainActivity's list of items
     * @param context Context used to create GET request to server for Products.
     */
    public ProductDownloader(final ListingViewPresenter context, final Map<String, String> options){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = MainActivity.serverIP + "/getItem.php";
        StringRequest strReq = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            JSONObject responseObject = new JSONObject(response);
                            if (responseObject.get("error").equals(true)) {
                                Toast.makeText(context.getApplicationContext(), "Failed to load items. Try again later", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            JSONArray itemArray = responseObject.getJSONArray("items");
                            JSONObject tempJSON;
                            context.items.clear(); //Delete items already stored
                            for (int i = 0; i < itemArray.length(); i++) {
                                Item tempItem = new Item();
                                tempJSON = itemArray.getJSONObject(i);
                                tempItem.id = tempJSON.getString("id");
                                tempItem.sid = tempJSON.getString("sid");
                                tempItem.title = tempJSON.getString("title");
                                tempItem.price = Double.parseDouble(tempJSON.getString("price"));
                                tempItem.category = tempJSON.getString("category");
                                tempItem.description = tempJSON.getString("description");
                                tempItem.imgsrc = tempJSON.getString("imgsrc");

                                context.items.add(tempItem);
                            }
                            context.showItem();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context.getApplicationContext(), "Failed to load items. Try again later", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error" + error.getMessage());
                Toast.makeText(context.getApplicationContext(), "Server error! Try again later", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                System.out.println("Getting params");
                return options;
            }
        };
        queue.add(strReq);
    }

}
