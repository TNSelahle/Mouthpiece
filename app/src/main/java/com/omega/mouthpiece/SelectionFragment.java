package com.omega.mouthpiece;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Comparator;

import static com.omega.mouthpiece.filter.ratingCriteria;
import static com.omega.mouthpiece.filter.sortCriteria;

public class SelectionFragment extends Fragment implements DBAdapter.OnItemClickListener {



    public static final String EXTRA_URL = "imageURL";
    public static final String EXTRA_CREATOR = "creatorName";
    public static final String EXTRA_LIKES = "ratings";
    public static final String EXTRA_DOWNLOADS = "downloads";



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
    private Button btnFilter;
    private View temp;
    Image preview;

    String base64Image;
    Bitmap decodedByte;
    ImageView img;
   // private ProgressBar loadingSpinner;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_selection, container, false);
        //Setting up the view for dynamically populating the view through API calls
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        loading = (ProgressBar)root.findViewById(R.id.pBar);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //btnFilter=findViewById(R.id.filterButton);
        //creating list and queue for API Calss
        mMouthList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(getActivity());

        //loadingSpinner = root.findViewById(R.id.progressBarFragSelection);

        //Calling API call method, to get JSON and parse it.
//        loadingSpinner.setVisibility(View.VISIBLE);
        parseJSON(getSortDetails(), getSortRatingsDetails());
    //    loadingSpinner.setVisibility(View.GONE);





        //TODO: Finish Implement base64 conversion

        /*
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m1);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        //decode base64 string to image
        imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        image.setImageBitmap(decodedImage);
         */


        return root;
    }




    //TODO: Test Filter
    private void openFilter()
    {
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getContext(), filter.class);
                startActivity(intent);
            }
        });
    }
    //API call Method
    private void parseJSON(String sortCriteria,String ratingsCriteria)
    {
        //TODO: Use our own hosted API.
        String url = "http://102.133.170.83:3000/sharingapi/mouthpiece/downloadAll";

        //GET request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //getting JSON


                            JSONArray jsonArray = response.getJSONArray("result");

                            for(int i = 0; i <jsonArray.length();i++)
                            {
                                //Parsing JSON
                                JSONObject hit = jsonArray.getJSONObject(i);
                                JSONObject array  =  hit.getJSONObject("formants");
                                String imageURL = array.getString("f0");
                                System.out.println(imageURL);

                                base64Image = imageURL;
                                //base64Image = "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";
                                convertBase64ToImage();

                                Log.d("Before URI test", "YE");



                                Log.d("URL:", imageURL);

                                String creatorName = hit.getString("name");
                                int ratings =0;
                                ratings= hit.getInt("rating");
                                int downloads = hit.getInt("downloads");
                                //Adding to list
                                Uri imageU = getImageUri(/*this.getContext(), */decodedByte, creatorName);

                                imageURL = imageU.toString();

                                //mMouthList.add(new MouthItem(imageURL,creatorName,ratings,downloads));
                                mMouthList.add(new MouthItem(imageURL,creatorName,ratings,downloads));
                            }

                            loading.setVisibility(View.GONE);
                            //Sorting
                            mMouthList=sortBy(mMouthList);
                            mMouthList=sortRatings(mMouthList);//This function crashes app, still working on it - Anrich
                            //TODO:OK
                            mDBAdapter = new DBAdapter(getContext(),mMouthList);
                            mRecyclerView.setAdapter(mDBAdapter);
                            mDBAdapter.setOnItemClickListener(SelectionFragment.this);
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

    public void convertBase64ToImage(){

        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        if(decodedByte == null){
            Log.d("Conversion", "notworking");
        }
        else{
            Log.d("IMG", decodedByte.toString());
        }

    }
    public Uri getImageUri(/*Context inContext, */Bitmap inImage, String n) {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);


        Log.d("Testing:", "123");

        String path = MediaStore.Images.Media.insertImage(getContext().getContentResolver(), inImage, n, null);
        //String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }


    public void onItemClick(int position) {

        Intent detailIntent = new Intent(getContext(), DetailActivity.class);
        MouthItem clickedItem = mMouthList.get(position);

        detailIntent.putExtra(EXTRA_URL, clickedItem.getImageURL());
        detailIntent.putExtra(EXTRA_CREATOR, clickedItem.getCreator());
        detailIntent.putExtra(EXTRA_LIKES, clickedItem.getRatings());
        detailIntent.putExtra(EXTRA_DOWNLOADS, clickedItem.getDownloads());
        startActivity(detailIntent);
    }
    private String getSortDetails()//Function to retrieve the details from the filter page
    {
        String sort;
        Intent intent = getActivity().getIntent();
        sort=intent.getStringExtra(sortCriteria);
        //if(sort==null)
        //    sort="Name";
        return sort;
    }
    private String getSortRatingsDetails()//Function to retrieve the details from the filter page
    {
        String ratings;
        Intent intent = getActivity().getIntent();
        ratings=intent.getStringExtra(ratingCriteria);
        /*if(ratings==null)
            ratings="2";*/
        return ratings;
    }
    public static class sortByName implements Comparator<MouthItem>
    {
        @Override
        public int compare(MouthItem o1, MouthItem o2)
        {
            return o1.getCreator().compareTo(o2.getCreator());
        }
    }
    public static class sortByDownloads implements Comparator<MouthItem>
    {
        @Override
        public int compare(MouthItem o1, MouthItem o2)
        {
            if(o1.getDownloads()>o2.getDownloads())
                return 1;
            else if(o1.getDownloads()<o2.getDownloads())
                return -1;
            else
                return 0;
        }
    }
    private ArrayList<MouthItem> sortBy(ArrayList<MouthItem> oldList)
    {
        if(getSortDetails()==null || getSortDetails()=="none")
            return oldList;
        else
        {
            if(getSortDetails()=="Name") {
                oldList.sort(new sortByName());
            }
            else if(getSortDetails()=="downloads") {
                oldList.sort(new sortByDownloads());
            }
        }
        return oldList;
    }
    private ArrayList<MouthItem> sortRatings(ArrayList<MouthItem> oldList)
    {
        if(getSortRatingsDetails()==null || getSortRatingsDetails()=="none")
            return oldList;
        else
        {
            int size=oldList.size();
            //Log.e("Filter: size",Integer.toString(size));
            int generalCounter=0;
            int[] removeIndexes = new int[size];
            for(int i=0; i<size; i++)
                removeIndexes[i]=-1;

            //Log.e("Filter: size",Integer.toString(size));
            for(int x=0; x<size; x++)
            {
                //Log.e("Filter: rating",Integer.toString(oldList.get(x).getRatings()));
                if(oldList.get(x).getRatings() < Integer.parseInt(getSortRatingsDetails()))
                {
                    removeIndexes[generalCounter]=x;
                    generalCounter++;
                    //oldList.remove(x);
                }
            }
            for (int a=0; a<size;a++)
            {
                if(removeIndexes[a]!=-1)
                {
                    oldList.remove(removeIndexes[a]);
                    size--;
                }
            }
            return oldList;
        }
    }
}






