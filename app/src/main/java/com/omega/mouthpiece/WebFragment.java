package com.omega.mouthpiece;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class WebFragment extends Fragment {

    private WebView mywebview;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_website, container, false);
        mywebview = root.findViewById(R.id.webView);
        mywebview.loadUrl("https://www.mouthpiece.tech/");
        return root;
    }
}
