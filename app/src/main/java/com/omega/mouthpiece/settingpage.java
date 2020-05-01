package com.omega.mouthpiece;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class settingpage extends AppCompatActivity {
    private Button feedback;
//    Switch simpleSwitch1;

@Override
   protected void onCreate(Bundle savedInstanceState) {


       super.onCreate(savedInstanceState);
       setContentView(R.layout.settings);

    feedback = findViewById(R.id.feedbackBTn);
    feedback.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(settingpage.this, FeedbackPage.class));
        }
    });

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
}
