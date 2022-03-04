package com.example.CopIt;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.CopIt.R;
import com.example.CopIt.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    public ImageView productImage;


    private GestureDetectorCompat gesture = null;

    public static User user;
    public static String serverIP ="http://backhomediesel.ddns.net/~liam/project-backhomediesel/html"; //External use.
    //public static String serverIP = "http://192.168.2.57/~liam/project-backhomediesel/html"; //Local use only.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkSignupButton();
        linkLogin();


    }

    private void linkSignupButton() {
        Button signupBut = (Button) findViewById(R.id.signup);
        signupBut.setOnClickListener(new View.OnClickListener() {
                                         public void onClick(View view) {
                                             startActivity(new Intent(MainActivity.this, RegisterViewPresenter.class));
                                         }
                                     }
        );
    }



    private void linkLogin(){
        Button backToLoginBut = (Button)findViewById(R.id.loginButton);
        backToLoginBut.setOnClickListener(new View.OnClickListener(){
                                              public void onClick(View view) {
                                                  verifyLogins();
                                              }
                                          }
        );
    }
    private void verifyLogins(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = MainActivity.serverIP +  "/checkUserLogin.php";
        final EditText username = findViewById(R.id.Usernamebox);
        final EditText password = findViewById(R.id.Passwordbox);
        StringRequest strReq = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject loginArray = new JSONObject(response);
                            JSONObject tempJSON;
                            Log.i("test", "Response is: " + response);
                            if (loginArray.get("error").equals(false)) {
                                Toast.makeText(getApplicationContext(), "Login failed!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, menuViewPresenter.class));
                            }
                            //username.setText("");
                            password.setText("");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(com.android.volley.error.VolleyError error) {
                System.out.println("Error" + error.getMessage());
                Toast.makeText(getApplicationContext(), "Server error! Try again later", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username.getText().toString());
                params.put("password", password.getText().toString());
                return params;
            }
        };
        queue.add(strReq);
    }

}
