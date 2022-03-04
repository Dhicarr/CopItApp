package com.example.CopIt;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.Destroyable;import android.view.GestureDetector;import android.view.MotionEvent;


public class ListingViewPresenter extends AppCompatActivity {

    public ArrayList<Item> items;
    public ImageView productImage;
    public ImageView newImage;
    public ImageView imageView = null;
    public ImageView newImg;
    public TextView productTitle;
    public TextView productPrice;
    public TextView productDescription;
    public int itemIndex;

    private MainActivity activity = null;
    private GestureDetectorCompat gesture = null;

    private ListingViewPresenter act = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        this.items = new ArrayList<>();
        this.itemIndex = 0;
        productImage = findViewById(R.id.productImage);
        productPrice = findViewById(R.id.priceTextView);
        productDescription = findViewById(R.id.descriptionTextView);
        productTitle = findViewById(R.id.titleTextView);

        linkDislikeButton();
        linkLikeButton();
        linkSpinner();
      
        DetectorSwipeGesture gestureListener = new DetectorSwipeGesture(this);
        gestureListener.setActivity(this.activity);
        gesture = new GestureDetectorCompat(this, gestureListener);

    }

    public void downloadCategories(String category) {
        this.itemIndex = 0; //Reset index when changing categories
        Map<String, String> options = new HashMap<String, String>(); //Default is no options'
        options.put("category", category);
        new ProductDownloader(this, options); //Calls showItem upon finish downloading.
    }

    private void linkSpinner() {
        final Spinner categorySpinner = (Spinner) findViewById(R.id.listing_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
          downloadCategories(categorySpinner.getSelectedItem().toString());
      }

      @Override
      public void onNothingSelected(AdapterView<?> parentView) {

      }

  });

    }
    public boolean onTouchEvent(MotionEvent event) {
        gesture.onTouchEvent(event);
        return true;
    }

    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    public ListingViewPresenter getAct() {
        return this.act;
    }

    public void showItem () {
        if (this.itemIndex >= this.items.size()) {
            this.productImage.setImageResource(R.drawable.noimage);

            this.productDescription.setText("");
            this.productPrice.setText("");
            this.productTitle.setText("");


            textAnimation t = new textAnimation();
            t.onPostExecute(productTitle, productPrice, productDescription);
        } else {
            Item item = this.items.get(this.itemIndex);
            productTitle.setText(item.title);
            productPrice.setText(Double.toString(item.price));
            productDescription.setText(item.description);

            textAnimation t = new textAnimation();
            t.onPostExecute(productTitle, productPrice, productDescription);
            new ImageDownloader().execute(productImage, item.imgsrc);


        }
    }



    public void likeItem() {
        if (this.itemIndex < this.items.size()) {
            Map<String, String> options = new HashMap<>();
            options.put("bid", "admin"); //TODO: Replace with current userid of logged in user.)
            options.put("id", this.items.get(this.itemIndex).id);
            new ProductLiker(this, options);
            nextItem();
        }
    }

    public void nextItem() {
            this.itemIndex++;
            this.showItem();
    }

    private void linkLikeButton() {
        Button likeButton = findViewById(R.id.likeButton);
        likeButton.setOnClickListener(new View.OnClickListener() {
                                             public void onClick(View view) {
                                                 likeItem();
                                             }
                                         }
        );
    }

    private void linkDislikeButton() {
        Button dislikeButton = findViewById(R.id.dislikeButton);
        dislikeButton.setOnClickListener(new View.OnClickListener() {
                                         public void onClick(View view) {
                                            nextItem();
                                         }
                                     }
        );
    }

}
