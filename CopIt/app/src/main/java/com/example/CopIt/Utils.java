package com.example.CopIt;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Utils {

    /**
     * Loads items from DB into MainActivity's list of items
     * @param context Context used to create GET request to server for Products.
     */
/*    public static void loadItemFromDB(final Context context){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="http://backhomediesel.ddns.net/~liam/project-backhomediesel/html/getItem.php";
        StringRequest strReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("DOWNLOADING");
                        try {
                            JSONArray itemArray = new JSONArray(response);
                            JSONObject tempJSON;
                            ListingViewPresenter.items.clear(); //Delete items already stored
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

                                ListingViewPresenter.items.add(tempItem);
                                //System.out.println(tempItem.toString());
                            }
                            ListingViewPresenter.showItem(context);
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
        });
        queue.add(strReq);
    }*/

    /*public static void uploadItemIntoDB(final Context context, final Item item){
            //Utils.loadItemFromDB(context);
            RequestQueue queue = Volley.newRequestQueue(context);
            String url ="http://backhomediesel.ddns.net/~liam/project-backhomediesel/html/addItem.php";
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
                public void onErrorResponse(VolleyError error) {
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
        }*/


}
