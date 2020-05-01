package com.omega.mouthpiece;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class FeedFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_feedback, container, false);
        WebView mywebview = root.findViewById(R.id.webView);
        mywebview.loadUrl("http://www.mouthpiece.tech/");
        return root;
    }
}
