package com.omega.mouthpiece;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class FeedbackPage extends AppCompatActivity {

    private EditText nameUser;
    private String jsonName;
    private EditText descriptionFeedback;
    private String jsonDescr;
    private Spinner optionVal;
    private String jsonOption;
    private String jsonEmail;
    private Button submitButton;
    private Button exitButton;
    private CheckBox anon;
    private Boolean isAnon;
    private EditText email;
    private TextView emailHeader;
    private TextView nameHeader;

    private RequestQueue feedbackRequestQueue;
    private StringRequest feedbackStringRequest;
    private JSONObject jsonBodyParse;
   // private String url = "102.133.170.83:5000/getFeedback";
    private String url = "http://102.133.170.83:5000/addFeedback";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_feedback);

        submitButton = findViewById(R.id.submitBtn);
        nameUser = findViewById(R.id.nameInput);
        descriptionFeedback = findViewById(R.id.descriptionFeedback);
        optionVal = findViewById(R.id.subjectsDropdown);
        exitButton = findViewById(R.id.exitButton);
        anon = findViewById(R.id.anonCheck);
        email = findViewById(R.id.emailInput);
        isAnon = anon.isChecked();
        emailHeader = findViewById(R.id.emailText);
        nameHeader = findViewById(R.id.nameOfUser);



        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                jsonName = nameUser.getText().toString();
                jsonDescr = descriptionFeedback.getText().toString();
                jsonOption = optionVal.getSelectedItem().toString();
                jsonEmail = email.getText().toString();
                /*
                String jsonParse = "{" +
                        "\"name\"" + "\"" + jsonName + "\"," +
                        "\"subject\"" + "\"" + jsonOption + "\"," +
                        "\"description\"" + "\"" + jsonDescr + "\"" +
                        "}";

                 */
                sendJsonFeedback();
                //Toast.makeText(getApplicationContext(), jsonBodyParse, Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(FeedbackPage.this, settingpage.class));
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FeedbackPage.this, settingpage.class));
            }
        });
        anon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked){
                    email.setEnabled(false);
                    emailHeader.setTextColor(Color.parseColor("#333138"));
                    nameHeader.setTextColor(Color.parseColor("#333138"));
                    nameUser.setText("Anonymous");
                    nameUser.setEnabled(false);
                }
                else{
                    email.setEnabled(true);
                    emailHeader.setTextColor(Color.parseColor("#EE1C31"));
                    nameHeader.setTextColor(Color.parseColor("#EE1C31"));
                    nameUser.setEnabled(true);
                }
            }
        }
        );

    }

    private void sendJsonFeedback() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        try {

            jsonBodyParse = new JSONObject();
            jsonBodyParse.put("email", jsonEmail);
            jsonBodyParse.put("name", jsonName);
            jsonBodyParse.put("subject", jsonOption);
            jsonBodyParse.put("description", jsonDescr);
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