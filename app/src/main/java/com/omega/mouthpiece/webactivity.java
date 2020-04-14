package com.omega.mouthpiece;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
public class webactivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webactivity);
        WebView mywebview = findViewById(R.id.webView);
        mywebview.loadUrl("http://www.mouthpiece.tech/");

    }
}





