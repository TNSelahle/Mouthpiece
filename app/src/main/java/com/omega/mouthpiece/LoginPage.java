package com.omega.mouthpiece;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import static com.omega.mouthpiece.Email.SendSuccessfullUploadEmail;

public class LoginPage extends AppCompatActivity {


    private EditText Email;
    private EditText Password;
    private Button Login;
    private Button Register;
    private String jsonEmail;
    private String jsonPassword;
    private CheckBox rememberMe;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email =findViewById(R.id.username);
        Password =findViewById(R.id.password);
        Login = findViewById(R.id.loginButton);
        Register = findViewById(R.id.registerButton);
        rememberMe = findViewById(R.id.rememberMe);

        sharedPreferences=getSharedPreferences("LoginPrefs",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        //Store auto login preferences:
        String mail=sharedPreferences.getString("Email","");
        String password=sharedPreferences.getString("Password","");
        Boolean checked=sharedPreferences.getBoolean("Remember",false);

        Email.setText(mail);
        Password.setText(password);
        rememberMe.setChecked(checked);

        Login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //If remember me is checked, auto fill email and password
                if(rememberMe.isChecked())
                {
                    editor.putString("Email",Email.getText().toString());
                    editor.putString("Password",Password.getText().toString());
                    editor.putBoolean("Remember",rememberMe.isChecked());
                    editor.commit();
                }
                jsonEmail = Email.getText().toString();
                jsonPassword = Password.getText().toString();
                login();

                Intent intent = new Intent(LoginPage.this, MainActivity.class);
                startActivity(intent);
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

    private String url = "http://102.133.170.83:4000/login";
    private JSONObject jsonBodyParse;

    private void login()
    {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        try {

            jsonBodyParse = new JSONObject();
            jsonBodyParse.put("email", jsonEmail);
            jsonBodyParse.put("password", jsonPassword);
            final String loginRequestBody = jsonBodyParse.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Just a test to see if emails are sending
        /*try
        {
            SendSuccessfullUploadEmail("anrich96@gmail.com","Anrich96","www.google.com","my ID");
        }
        catch (JSONException e)
        {
            Log.e("sendingEmail",e.toString());
        }*/
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
