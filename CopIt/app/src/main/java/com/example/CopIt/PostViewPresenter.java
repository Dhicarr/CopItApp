package com.example.CopIt;
import com.example.CopIt.ProductUploader;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostViewPresenter extends AppCompatActivity {

    public String category;
    private String currentImagePath = "";
    private String currentImageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        linkPostButton();
        linkSpinner();
        setupAddPic();
    }

    private void setupAddPic() {
        ImageView imgView = (ImageView) findViewById(R.id.productImage);
        imgView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String fileName = "photo";
                File storeDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//                    File imgFile = File.createTempFile(fileName, ".jpg", storeDir);
//                    currentPhotoPath = imgFile.getAbsolutePath();
//                    Uri imgURI = FileProvider.getUriForFile(PostViewPresenter.this, "com.example.CopIt.fileprovider", imgFile);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (intent.resolveActivity(getPackageManager()) != null){
                    File imgFile = null;
                    try {
                        imgFile = getImageFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if(imgFile != null){
                        Uri imgURI = FileProvider.getUriForFile(PostViewPresenter.this, "com.example.CopIt.fileprovider", imgFile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgURI);
                        startActivityForResult(intent, 1);
                    }
                }
            }
        }
        );
    }


    private File getImageFile() throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imgName = "IMG_" + timeStamp;
        File storeDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File imgFile = File.createTempFile(imgName, ".jpg", storeDir);
        currentImageName = imgFile.getName();
        currentImagePath = imgFile.getAbsolutePath();
        return imgFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK){
            Bitmap bitmap = BitmapFactory.decodeFile(currentImagePath);
            ImageView imgView = (ImageView) findViewById(R.id.productImage);
            imgView.setImageBitmap(bitmap);
        }
    }

    private void linkPostButton() {
        Button postButton = (Button) findViewById(R.id.Post);
        postButton.setOnClickListener(new View.OnClickListener() {
                                         public void onClick(View view) {
                                             //Listener
                                             createItem();
                                         }
                                     }
        );
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private void linkSpinner() {
            final Spinner categorySpinner = (Spinner) findViewById(R.id.new_post_spinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.Categories, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categorySpinner.setAdapter(adapter);


            categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                setCategory(categorySpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
    }

    public void createItem() {
        EditText title = findViewById(R.id.Title);
        EditText price = findViewById(R.id.Price);
        EditText username = findViewById(R.id.Usernamebox);
        EditText description = findViewById(R.id.Product_description);
        ImageView prodImg = (ImageView) findViewById(R.id.productImage);
        Item newItem = new Item();
        newItem.sid = "admin"; //TODO: Replace with current userid of logged in user.
        newItem.title = title.getText().toString();
        newItem.category = this.category;
        newItem.price = Double.parseDouble(price.getText().toString());
        newItem.description = description.getText().toString();


        if (currentImagePath.equals("")) {  //if there's no picture yet give error message via toast
//            newItem.imgsrc = "https://www.salonlfc.com/wp-content/uploads/2018/01/image-not-found-1-scaled-1150x647.png";
            Toast.makeText(getApplicationContext(), "Product needs to have a picture attached!", Toast.LENGTH_LONG).show();
        }
        else {      //if picture already posted, proceed to add the item into db
            System.out.println("inside");
            File file = new File(currentImagePath);
            ImageUploader imageUploader = new ImageUploader(this, file);
            newItem.imgsrc = MainActivity.serverIP + "/images/" + currentImageName;
            ProductUploader uploader = new ProductUploader(this, newItem); //Upload item to DB.
            Toast.makeText(getApplicationContext(), "Product Added!", Toast.LENGTH_SHORT).show();
        }
    }


}
