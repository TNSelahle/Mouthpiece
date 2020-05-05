package com.omega.mouthpiece;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;


public class RegisterPage extends AppCompatActivity {
    private Button Register;
    private EditText email;
    private EditText password;
    private EditText password2;
    private TextView errorField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        Register = (Button)findViewById(R.id.button);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        password2 = (EditText)findViewById(R.id.password2);
        errorField = (TextView)findViewById(R.id.errorView);
        //TODO: Implement API Call to register.
        Register.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View view)
            {


                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                String password2Text = password2.getText().toString();

                if (passwordText.equals(password2Text)) {
                    Toast.makeText(getApplicationContext(), "Registering", Toast.LENGTH_SHORT);
                    System.out.println("Registering");
                    register();
                }
                else {
                    passwordMismatch();
                }

            }
        });

        password2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Register.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String passwordText = password.getText().toString();
                String password2Text = password2.getText().toString();
                if (passwordText.equals(password2Text)) {
                    Register.setEnabled(true);
                    errorField.setText("");
                }
                else {
                    Register.setEnabled(false);
                    errorField.setText(R.string.password_mismatch);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            changeTextStatus(true);
        } else {
            changeTextStatus(false);
        }

    }

    private void passwordMismatch() {
        TextView error = (TextView)findViewById(R.id.errorView);
        error.setText(R.string.password_mismatch);
    }

    public void changeTextStatus(boolean isConnected) {
        if (!isConnected) {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }
    }

    private void register() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://102.133.170.83:4000/register";
        JSONObject requestParams = new JSONObject();
        try {
            requestParams.put("email", email.getText().toString());
            requestParams.put("password", password.getText().toString());
            final String requestString = requestParams.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest JSONRequest = new JsonObjectRequest(Request.Method.POST, url, requestParams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("LOG_RESPONSE", "Registered");
                        Intent login = new Intent(getApplicationContext(), LoginPage.class);
                        getApplicationContext().startActivity(login);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error getting response: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}
