package com.omega.mouthpiece;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class openWebsiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_open_website);

//        FloatingActionButton fab = findViewById(R.id.fab);

    }
    public void openBrowser(View view)
    {
//        String packageName = "com.android.browser";
//        String className = "com.android.browser.BrowserActivity";
        Intent internetIntent = new Intent(Intent.ACTION_VIEW);
//        internetIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//        internetIntent.setClassName(packageName, className);
        String url = "http://www.mouthpiece.tech";
        internetIntent.setData(Uri.parse(url));
        openWebsiteActivity.this.startActivity(internetIntent);

    }

}
