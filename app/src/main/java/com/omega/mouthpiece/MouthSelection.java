package com.omega.mouthpiece;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import static com.omega.mouthpiece.filter.ratingCriteria;
import static com.omega.mouthpiece.filter.sortCriteria;


//Mouth Selection for selecting different mouth shapes
public class MouthSelection extends AppCompatActivity  implements DBAdapter.OnItemClickListener {
    //Stats for each mouth shape items
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
    private Button btnFilter;

   //Tried adding navigation drawer
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouth_selection2);

        //Replace default action bar with tool bar, to add items in toolbar add it in menu_item.xml
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //Set up Default Back Button and replaced it the navigation button
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_menu_white_18dp);
        //Testing Navigation Drawer
        //drawerLayout = findViewById(R.id.drawer_layout);
        //TODO: link the navigation drawer to the navigation button

        //Setting up the view for dynamically populating the view through API calls
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //btnFilter=findViewById(R.id.filterButton);
        //creating list and queue for API Calss
        mMouthList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        //Calling API call method, to get JSON and parse it.
        parseJSON(getSortDetails(), getSortRatingsDetails());
    }

    //Method for Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Method for when different toolbar options are clicked.
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.filterIcon:
            openFilter();
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //TODO: Test Filter
    private void openFilter()
    {
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), filter.class);
                startActivity(intent);
            }
        });
    }
    //API call Method
    private void parseJSON(String sortCriteria,String ratingsCriteria)
    {
        //TODO: Use our own hosted API.
        String url = "https://pixabay.com/api/?key=15576743-14f7b7e30a703aaa50377d29d&q=dog&image_type=photo&pretty=true";

        //GET request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //getting JSON
                            JSONArray jsonArray = response.getJSONArray("hits");

                            for(int i = 0; i < jsonArray.length();i++)
                            {
                                //Parsing JSON
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String creatorName = hit.getString("user");
                                String imageURL = hit.getString("webformatURL");
                                int ratings = hit.getInt("likes");
                                int downloads = hit.getInt("downloads");
                                //Adding to list
                                mMouthList.add(new MouthItem(imageURL,creatorName,ratings,downloads));
                            }
                            //Sorting
                            mMouthList=sortBy(mMouthList);
                            mMouthList=sortRatings(mMouthList);//This function crashes app, still working on it - Anrich
                            //TODO:OK
                            mDBAdapter = new DBAdapter(MouthSelection.this,mMouthList);
                            mRecyclerView.setAdapter(mDBAdapter);
                            mDBAdapter.setOnItemClickListener(MouthSelection.this);
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


    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
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
        Intent intent = getIntent();
        sort=intent.getStringExtra(sortCriteria);
        return sort;
    }
    private String getSortRatingsDetails()//Function to retrieve the details from the filter page
    {
        String ratings;
        Intent intent = getIntent();
        ratings=intent.getStringExtra(ratingCriteria);
        return ratings;
    }
    public class sortByName implements Comparator<MouthItem>
    {
        @Override
        public int compare(MouthItem o1, MouthItem o2)
        {
            return o1.getCreator().compareTo(o2.getCreator());
        }
    }
    public class sortByDownloads implements Comparator<MouthItem>
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
            if(sortCriteria=="Name")
                oldList.sort(new sortByName());
            else if(sortCriteria=="downloads")
                oldList.sort(new sortByDownloads());
        }
        return oldList;
    }
    private ArrayList<MouthItem> sortRatings(ArrayList<MouthItem> oldList)//Sorts the data by rating, dividing by 100 since the data we're using is temporary and the likes are what we are using as the rating, so will fix when we use real data
    {
        if(getSortRatingsDetails()==null || getSortRatingsDetails()=="none")
            return oldList;
        else
        {
            for(int x=0; x<oldList.size(); x++)
            {
                if( (oldList.get(x).getRatings()/100) < Integer.parseInt(getSortRatingsDetails()))
                {
                    oldList.remove(x);
                }
            }
            return oldList;
        }
    }
}
