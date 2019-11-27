package com.example.servicesample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class Picture extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imgPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        imgPicture = findViewById(R.id.imgPicture);
    }

    public void takePicture(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Integer width = imageBitmap.getWidth();
            Integer height = imageBitmap.getHeight();
            Integer new_width = 0;
            Integer new_height = 0;
            if (height > width){
                new_height = 750;
                new_width = Math.round((float)width / height * 750);
            }else{
                new_width = 750;
                new_height = Math.round((float)height / width * 750);
            }
            Bitmap resized = Bitmap.createScaledBitmap(imageBitmap, new_width, new_height, true);
            imgPicture.setImageBitmap(resized);
        }
    }
}
