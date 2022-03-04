package com.example.CopIt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
public class menuViewPresenter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        linkLogOut();
        linkMyPosts();
        linkListing();
        linkContactUs();
    }
    private void linkLogOut(){
        Button backToLoginBut = (Button)findViewById(R.id.LogOutButton);
        backToLoginBut.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Log.d("test", "sdfsdfdsf");
                finish();
                //startActivity(new Intent(menuViewPresenter.this, MainActivity.class));
            }
        }
        );
    }
    private void linkMyPosts(){
        Button postings = (Button)findViewById(R.id.My_PostButton);
        postings.setOnClickListener(new View.OnClickListener(){
                                              public void onClick(View view) {
                                                  startActivity(new Intent(menuViewPresenter.this, PostViewPresenter.class));
                                              }
                                          }
        );
    }

    private void linkListing() {
        Button listing = (Button)findViewById(R.id.ListingButton);
        listing.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                startActivity(new Intent(menuViewPresenter.this, ListingViewPresenter.class));
            }
        }
        );
    }

    private void linkContactUs() {
        Button listing = (Button)findViewById(R.id.contactButton);
        listing.setOnClickListener(new View.OnClickListener(){
                                       public void onClick(View view) {
                                           startActivity(new Intent(menuViewPresenter.this, ContactUsViewPresenter.class));
                                       }
                                   }
        );
    }
}
