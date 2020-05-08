package com.omega.mouthpiece;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class LoginPage extends AppCompatActivity {


    private EditText Email;
    private EditText Password;
    private Button Login;
    private Button Register;
    private String url;
    private String jsonEmail;
    private String jsonPassword;
    private CheckBox rememberMe;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnSkip = findViewById(R.id.button4);
        url = "http://102.133.170.83:4000/login";
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
                //login();

                AsyncTask<String, String, String> execute = new CallAPI();
                execute.execute();

            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginPage.this, MainActivity.class);
                startActivity(i);
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

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public void onBackPressed() {
        //do nothing
        //Prevents the user from going back into the app after they have signed out
    }
    private class CallAPI extends AsyncTask<String, String, String> {



        public CallAPI(){
            //set context variables if required
        }
        private boolean success = false;
        private String APIKey = "";
        private JSONObject jsonBodyParse;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Display the progress bar.
            //findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            try {
                jsonBodyParse = new JSONObject();

                if(isValidEmail(jsonEmail))
                {
                    jsonBodyParse.put("email", jsonEmail);
                }
                else
                {
                    jsonBodyParse.put("username", jsonEmail);
                }

                jsonBodyParse.put("password", jsonPassword);
            } catch (JSONException e) {
                e.printStackTrace();
            }



            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBodyParse,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //Toast.makeText(getApplicationContext(), "String Response : "+ response.toString(), Toast.LENGTH_SHORT).show();
                            //resultTextView.setText("String Response : "+ response.toString());
                            try {

                                success = response.getBoolean("logged");
                                //System.out.println(success);
                                if(success)
                                {
                                    APIKey = response.getString("key");
                                    GlobalVariableMode.gAPI_key = APIKey;
                                    if(rememberMe.isChecked()) {
                                        editor.putString("API", APIKey);
                                        editor.commit();
                                    }
                                    Intent main = new Intent(LoginPage.this, MainActivity.class);
                                    LoginPage.this.startActivity(main);
                                }
                                else
                                {
                                    String msg = response.getString("message");
                                    String details = response.getString("details");
                                    Toast.makeText(getApplicationContext(), "\t\t\t\t\t\t\t\t\t"+msg+"\n"+details, Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error getting response" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(jsonObjectRequest);


            if(APIKey!=null && success == true)
            {
               return "success";
            }
            else
                return "fail";
        }



        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //findViewById(R.id.loadingPanel).setVisibility(View.GONE);

        }
    }






}
