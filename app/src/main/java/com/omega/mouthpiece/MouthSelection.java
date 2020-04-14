package com.omega.mouthpiece;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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

public class MouthSelection extends AppCompatActivity  implements DBAdapter.OnItemClickListener {
    public static final String EXTRA_URL = "imageURL";
    public static final String EXTRA_CREATOR = "creatorName";
    public static final String EXTRA_LIKES = "ratings";
    private static final String EXTRA_DOWNLOADS = "downloads";

    private RecyclerView mRecyclerView;
    private DBAdapter mDBAdapter;
    private ArrayList<MouthItem> mMouthList;
    private RequestQueue mRequestQueue;
    private Button btnFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouth_selection2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        btnFilter=findViewById(R.id.filterButton);

        mMouthList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.filterIcon:
            openFilter();
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

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

    private void parseJSON()
    {
        String url = "https://pixabay.com/api/?key=15576743-14f7b7e30a703aaa50377d29d&q=kitten&image_type=photo&pretty=true";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");

                            for(int i = 0; i < jsonArray.length();i++)
                            {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String creatorName = hit.getString("user");
                                String imageURL = hit.getString("webformatURL");
                                int ratings = hit.getInt("likes");
                                int downloads = hit.getInt("downloads");

                                mMouthList.add(new MouthItem(imageURL,creatorName,ratings,downloads));
                            }
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

}
