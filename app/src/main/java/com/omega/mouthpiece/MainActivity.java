package com.omega.mouthpiece;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText Email;
    private EditText Password;
    private TextView Info;
    private Button Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email =findViewById(R.id.username);
        Password =findViewById(R.id.password);
        Login = findViewById(R.id.loginButton);
        Info = findViewById(R.id.textView3);

        parseJSON();

        Login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                sendAndRequestResponse();
                validate(Email.getText().toString(),Password.getText().toString());
            }
        });

    }
    String emailAPI;
    String passwordAPI;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "http://102.133.170.83:3000/download";
    private void parseJSON() {

        mRequestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("result");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject result = jsonArray.getJSONObject(i);
                                emailAPI = result.getString("_id");
                                passwordAPI = result.getString("__v");
                                Context context = getApplicationContext();
                                CharSequence text = emailAPI;
                                int duration = Toast.LENGTH_SHORT;

                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                            }

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
    }

    private void sendAndRequestResponse() {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getApplicationContext(),"Response :" + response, Toast.LENGTH_LONG).show();//display the response on screen

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mRequestQueue.add(mStringRequest);
    }


    private void validate(String userName, String userPassword){
        if((userName.equals("admin@gmail.com")) && (userPassword.equals("admin"))){
            Intent intent = new Intent(MainActivity.this, LoadingPage.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Logged in Failed :", Toast.LENGTH_LONG).show();
        }
    }


}
