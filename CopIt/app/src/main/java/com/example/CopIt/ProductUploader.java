package com.example.CopIt;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

public class ProductUploader {

    public ProductUploader(final PostViewPresenter context, final Item item){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = MainActivity.serverIP + "/addItem.php";
        StringRequest strReq = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("UPLOADING");
                        if (response.equals("TRUE")) {
                            Toast.makeText(context.getApplicationContext(), "Item posted for sale!", Toast.LENGTH_SHORT).show();
                        }
                        System.out.println(" Add item response - " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(com.android.volley.error.VolleyError error) {
                // System.out.println("Add item ERR response -" + error.networkResponse);
                Toast.makeText(context.getApplicationContext(), "Could not post item, try again later!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return item.getParams();
            }
        };
        queue.add(strReq);
    }
}
