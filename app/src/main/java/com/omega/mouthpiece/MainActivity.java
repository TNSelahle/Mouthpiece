package com.omega.mouthpiece;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import static com.omega.mouthpiece.localNotification.makeToast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private Button filterBtn;
    private String API_key = "";
    private String Email = "";
    private JSONObject jsonBodyParse;
    private String url = "http://102.133.170.83:4000/getUsersViaAPI";
    private String usernameString;
    private TextView username;
    private TextView email;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendJsonFeedback();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Creates a folder to store users mouth images for upload and download
        File folder = new File(getFilesDir() + "/MouthpiecesTest");
        if(!folder.exists())
        {
            //Toast.makeText(this, "Root file does not exists", Toast.LENGTH_SHORT).show();
            folder.mkdir();
        }
        else
        {
            //Toast.makeText(this, "This file exists", Toast.LENGTH_SHORT).show();
            //check what user is logged in and download necessary folder
        }
        sharedPreferences=getApplicationContext().getSharedPreferences("LoginPrefs",MODE_PRIVATE);

        Email=sharedPreferences.getString("Email","kevin@gmail.com");
        API_key=sharedPreferences.getString("API","");


        String apiTemp = API_key;
        System.out.println(apiTemp);
        /*
        if(API_key=="")
        {
            System.out.println("lmao");
        }
        System.out.println("lmao");
        System.out.println(API_key);
        */

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);
        username = (TextView) headerView.findViewById(R.id.nav_username);
        email =(TextView) headerView.findViewById(R.id.nav_email);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_select_mouth, R.id.nav_create_mouth, R.id.nav_training,R.id.nav_website,R.id.nav_help,R.id.nav_feedback,R.id.nav_settings)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }


    private void sendJsonFeedback() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        try {

            jsonBodyParse = new JSONObject();
            jsonBodyParse.put("API_key",GlobalVariableMode.gAPI_key);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBodyParse,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println("Test 2" + response.toString());
                            usernameString = response.getString("username");
                            Email = response.getString("email");
                            username.setText(usernameString);
                            email.setText(Email);
                            GlobalVariableMode.gUsername = usernameString;
                            GlobalVariableMode.gEmail = Email;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //Function that runs when hamburger Icon is clicked:
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filterIcon:
                Intent intent = new Intent(MainActivity.this, filter.class);
                startActivity(intent);
                return true;
            case R.id.searchIcon:
                System.out.println("Search");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
