package com.omega.mouthpiece;

import android.net.Uri;
import android.view.View;
import android.widget.Toast;

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

import static androidx.test.InstrumentationRegistry.getContext;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class FeedbackAPIIntegration {
    private String url = "http://102.133.170.83:5000/addFeedback";
    private JSONObject jsonBodyParse;
    @Test
    public void feedbackTest() throws Exception {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        try {

            jsonBodyParse = new JSONObject();
            jsonBodyParse.put("email", "TestingFragEmail");
            jsonBodyParse.put("name", "TestingName");
            jsonBodyParse.put("subject", "TestUpload");
            jsonBodyParse.put("description", "This is an API call to test the feedback api call");
            final String fbRequestBody = jsonBodyParse.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBodyParse,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "String Response : "+ response.toString(), Toast.LENGTH_SHORT).show();
                        //resultTextView.setText("String Response : "+ response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error getting response" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);

    }


}

