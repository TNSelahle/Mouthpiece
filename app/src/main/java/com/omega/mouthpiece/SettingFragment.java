package com.omega.mouthpiece;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class SettingFragment extends Fragment {
    private Button feedback;
//    Switch simpleSwitch1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_setting, container, false);

        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

//        // initiate view's

//        simpleSwitch1 = (Switch) findViewById(R.id.switch2);
//        simpleSwitch1.setOnCheckedChangeListener(new Switch().OnCheckedChangeListener()) {
//            if (simpleSwitch1.isChecked()){
////
//            }
//            else{
//
//            }
//        });
   }

