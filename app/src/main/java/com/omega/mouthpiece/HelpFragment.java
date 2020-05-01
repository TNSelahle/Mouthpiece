package com.omega.mouthpiece;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class HelpFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_help, container, false);
        WebView mywebview = root.findViewById(R.id.webView);
        mywebview.loadUrl("https://www.microsoft.com/en-us/software-download/faq");
        return root;
    }
}
