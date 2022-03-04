package com.example.CopIt;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

public class ProductLiker {
    public ProductLiker(final ListingViewPresenter context, final Map<String, String> options) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = MainActivity.serverIP + "/likeItem.php";
        StringRequest strReq = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("TRUE")) {
                            Toast.makeText(context.getApplicationContext(), "Item liked", Toast.LENGTH_SHORT).show();
                        }
                        System.out.println(" Liked item response - " + response);
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
                return options;
            }
        };
        queue.add(strReq);
    }
}
