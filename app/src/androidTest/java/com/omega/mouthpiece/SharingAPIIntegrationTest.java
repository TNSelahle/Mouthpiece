package com.omega.mouthpiece;

import android.net.Uri;
import android.view.View;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.function.Consumer;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class SharingAPIIntegrationTest {
    private RequestQueue mRequestQueue;

    @Test
    public void downloadOneTest() throws Exception {
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());

        String url = "http://102.133.170.83:3000/sharingapi/mouthpiece/download?name=tester";


        //GET request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //getting JSON
                            System.out.println(response.toString());


                        } catch (Exception e) {
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

    @Test
    public void downloadAllTest() throws Exception {
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());

        String url = "http://102.133.170.83:3000/sharingapi/mouthpiece/downloadAll";


        //GET request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //getting JSON
                            System.out.println(response.toString());


                        } catch (Exception e) {
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

}



