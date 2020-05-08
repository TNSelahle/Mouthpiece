package com.omega.mouthpiece;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import static com.omega.mouthpiece.SelectionFragment.EXTRA_CREATOR;
import static com.omega.mouthpiece.SelectionFragment.EXTRA_LIKES;
import static com.omega.mouthpiece.SelectionFragment.EXTRA_POSITION;
import static com.omega.mouthpiece.SelectionFragment.EXTRA_URL;
import static com.omega.mouthpiece.SelectionFragment.EXTRA_DOWNLOADS;

public class DetailActivity extends AppCompatActivity {
    private Button btn;
    //Base View to display items
    private RecyclerView mRecyclerView;
    //To get list of items/MouthList
    private DBAdapter mDBAdapter;
    //List of mouth items
    private ArrayList<MouthItem> mMouthList;
    //Queue for API Calls
    private RequestQueue mRequestQueue;
    //TODO: Test Filter
    private ProgressBar loading;
    private Button filterBtn;
    private View temp;
    JSONArray jsonArray;
    Image preview;

    String base64Image;
    Bitmap decodedByte;

    @Override

    //Activity view for when clicked on mouth item in mouth selection
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        btn = findViewById(R.id.selectButton);

        Intent intent = getIntent();
        String imageURL = intent.getStringExtra(EXTRA_URL);
        String creatorName = intent.getStringExtra(EXTRA_CREATOR);
        int ratings = intent.getIntExtra(EXTRA_LIKES, 0);
        int downloads = intent.getIntExtra(EXTRA_DOWNLOADS, 0);

        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textViewCreator = findViewById(R.id.text_view_creator_detail);
        TextView textViewLikes = findViewById(R.id.text_view_like_detail);
        TextView textViewDownloads = findViewById(R.id.text_view_downloads);

        Picasso.with(this).load(imageURL).fit().centerInside().into(imageView);
        textViewCreator.setText(creatorName);
        textViewLikes.setText("Ratings: " + ratings);
        textViewDownloads.setText("Downloads: "+ downloads);

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                Intent intent = new Intent(DetailActivity.this, Converter.class);
                startActivity(intent);
            }
        });
    }

    //API call Method
    private void parseJSON()
    {

        String url = "http://102.133.170.83:3000/sharingapi/mouthpiece/downloadAll";

        final ArrayList<String> batchImageUri = new ArrayList<String>();

        //GET request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //getting JSON
                            jsonArray = response.getJSONArray("result");
                            int positionNumber = getIntent().getIntExtra(EXTRA_POSITION, 0);;
                            JSONObject hit = jsonArray.getJSONObject(positionNumber);
                            JSONObject array  =  hit.getJSONObject("formants");

                            // gets all mouth shapes
                            getAllImageUriIntoArray(batchImageUri, array);

                            convertAllBase64ToImage(batchImageUri);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }

    public void getAllImageUriIntoArray(ArrayList<String> arr, JSONObject jObj) throws JSONException {
        String imageUri = "";

        for(int i = 0; i < 12; i++)
        {
            //String imageURL0 = array.getString("f0");
            switch(i)
            {
                case 0:
                    imageUri = jObj.getString("f" + i);
                    arr.add(imageUri);
                    break;
                case 1:
                    imageUri = jObj.getString("f1");
                    arr.add(imageUri);
                    break;
                case 2:
                    imageUri = jObj.getString("f2");
                    arr.add(imageUri);
                    break;
                case 3:
                    imageUri = jObj.getString("f3");
                    arr.add(imageUri);
                    break;
                case 4:
                    imageUri = jObj.getString("f4");
                    arr.add(imageUri);
                    break;
                case 5:
                    imageUri = jObj.getString("f5");
                    arr.add(imageUri);
                    break;
                case 6:
                    imageUri = jObj.getString("f6");
                    arr.add(imageUri);
                    break;
                case 7:
                    imageUri = jObj.getString("f7");
                    arr.add(imageUri);
                    break;
                case 8:
                    imageUri = jObj.getString("f8");
                    arr.add(imageUri);
                    break;
                case 9:
                    imageUri = jObj.getString("f9");
                    arr.add(imageUri);
                    break;
                case 10:
                    imageUri = jObj.getString("f10");
                    arr.add(imageUri);
                    break;
                case 11:
                    imageUri = jObj.getString("f11");
                    arr.add(imageUri);
                    break;
                case 12:
                    imageUri = jObj.getString("f12");
                    arr.add(imageUri);
                    break;
            }
        }
    }

    public void convertAllBase64ToImage(ArrayList<String> arrUri){

        byte[] decodedString;
        String path = this.getFilesDir() + "/MouthpiecesTest";
        Bitmap decodedImageFormats;
        for (int i = 0; i < 12; i++)
        {
            base64Image = arrUri.get(i);
            decodedString = Base64.decode(base64Image, Base64.DEFAULT);
            decodedImageFormats = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            File directory = new File(path); // get main folder
            File file = new File(directory, "f"+i + ".jpg");

            if (!file.exists()) {
                Log.d("path", file.toString());
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(file);
                    decodedImageFormats.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.flush();
                    fos.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                FileOutputStream fosRedo = null;
                try {
                    fosRedo = new FileOutputStream(file);
                    decodedImageFormats.compress(Bitmap.CompressFormat.JPEG, 100, fosRedo);
                    fosRedo.flush();
                    fosRedo.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}