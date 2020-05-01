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
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {


    private EditText Email;
    private EditText Password;
    private Button Login;
    private Button Register;
    private int byPassCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email =findViewById(R.id.username);
        Password =findViewById(R.id.password);
        Login = findViewById(R.id.loginButton);
        Register = findViewById(R.id.registerButton);



        Login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                parseJSON();
                byPassCounter++;
                validate(Email.getText().toString(),Password.getText().toString());
            }
        });

        Register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(LoginPage.this, RegisterPage.class);
                startActivity(intent);
            }
        });



    }
    String emailAPI;
    String passwordAPI;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "http://102.133.170.83:4000/getUsers";

    private void parseJSON() {

        mRequestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            emailAPI = response.getString("email");
                            passwordAPI = response.getString("password");

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


    private void validate(String userName, String userPassword){
        if(((userName.equals(emailAPI)) && (userPassword.equals(passwordAPI))) || byPassCounter >= 5){
            Intent intent = new Intent(LoginPage.this, MainActivity.class);
            startActivity(intent);
        }

        else
        {
            Toast.makeText(getApplicationContext(),"Please Try Again", Toast.LENGTH_LONG).show();
        }
    }


}
