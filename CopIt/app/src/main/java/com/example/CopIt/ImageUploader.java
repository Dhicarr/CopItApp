package com.example.CopIt;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;

public class ImageUploader  {
    public ImageUploader(final PostViewPresenter context, File image) {
        RequestQueue queue = Volley.newRequestQueue(context);
        SimpleMultiPartRequest multiPartRequest = new SimpleMultiPartRequest(Request.Method.POST, MainActivity.serverIP + "/imageUpload.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               System.out.println("error");
            }
        });
        multiPartRequest.addFile("image", image.getAbsolutePath());
        queue.add(multiPartRequest);
    }
}
