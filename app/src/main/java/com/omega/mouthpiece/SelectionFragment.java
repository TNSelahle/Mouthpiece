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
    private Button filterBtn;
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
        filterBtn= (Button)root.findViewById(R.id.filterIcon);
        if(filterBtn!=null) {
            filterBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), filter.class);
                    startActivity(intent);
                }
            });
        }

        return root;
    }

    //API call Method
    private void parseJSON(String sortCriteria,String ratingsCriteria)
    {
        //TODO: Use our own hosted API.
        //TODO: Finish
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


                                base64Image = imageURL;
                                convertBase64ToImage();



                                String creatorName = hit.getString("name");
                                int ratings =0;
                                ratings= hit.getInt("rating");
                                int downloads = hit.getInt("downloads");
                                //Adding to list
                                Uri imageU = getImageUri(decodedByte, creatorName);

                                imageURL = imageU.toString();


                                mMouthList.add(new MouthItem(imageURL,creatorName,ratings,downloads));
                            }

                            loading.setVisibility(View.GONE);
                            //Sorting
                            mMouthList=sortBy(mMouthList);
                            mMouthList=sortRatings(mMouthList);
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


    }
    public Uri getImageUri(Bitmap inImage, String n) {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);


        String path = MediaStore.Images.Media.insertImage(getContext().getContentResolver(), inImage, n, null);
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






