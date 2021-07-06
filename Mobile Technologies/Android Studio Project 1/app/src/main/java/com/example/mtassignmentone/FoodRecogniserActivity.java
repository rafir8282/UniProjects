package com.example.mtassignmentone;

import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.android.library.camera.CameraHelper;
import com.ibm.watson.developer_cloud.android.library.camera.GalleryHelper;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodRecogniserActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;
    private VisualRecognition visualRecognition;
    private CameraHelper cameraHelper;
    private GalleryHelper galleryHelper;
    private File photoFile;
    private final String api_key = "zN2hC1AkNbPn0X2s0dE31Glkix_4sX7jmgdT3uv5JUqA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_recogniser);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        cameraHelper = new CameraHelper(this);
        galleryHelper = new GalleryHelper(this);
        IamOptions options = new IamOptions.Builder()
                .apiKey(api_key)
                .build();
        visualRecognition = new VisualRecognition("2018-03-19", options);
    }

    public void capture(View view){
        cameraHelper.dispatchTakePictureIntent();
    }

    public void load(View view){
        galleryHelper.dispatchGalleryIntent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CameraHelper.REQUEST_IMAGE_CAPTURE) {
            final Bitmap photo = cameraHelper.getBitmap(resultCode);
            photoFile = cameraHelper.getFile(resultCode);
            imageView.setImageBitmap(photo);
        }

        if (requestCode == GalleryHelper.PICK_IMAGE_REQUEST){
            final Bitmap photo = galleryHelper.getBitmap(resultCode, data);
            photoFile = galleryHelper.getFile(resultCode, data);
            imageView.setImageBitmap(photo);
        }

        runBackgroundThread();
    }

    private void runBackgroundThread(){

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
            InputStream imagesStream = null;
            try {
                imagesStream = new FileInputStream(photoFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
                    .imagesFile(imagesStream)
                    .imagesFilename(photoFile.getName())
                    .threshold((float) 0.6)
                    .classifierIds(Arrays.asList("food"))
                    .build();
            ClassifiedImages result = visualRecognition.classify(classifyOptions).execute();

            Gson gson = new Gson();
            String json = gson.toJson(result);
            Log.d("json", json);
            String name = null;
            double score = 0;
            String gender = null;
            int age = 0;
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("images");
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                JSONArray jsonArray1 = jsonObject1.getJSONArray("classifiers");
                JSONObject jsonObject2 = jsonArray1.getJSONObject(0);
                JSONArray jsonArray2 = jsonObject2.getJSONArray("classes");
                JSONObject jsonObject3 = jsonArray2.getJSONObject(0);
                name = jsonObject3.getString("class");
                score = jsonObject3.getDouble("score");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            final String finalName = name;
            final double finalScore = score;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                textView.setText(
                    "Detected Food: " + finalName + "\n" +
                        "Detected Score: " + finalScore);
                }
            });
            }
        });
    }

}
