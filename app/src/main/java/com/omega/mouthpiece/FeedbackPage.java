package com.omega.mouthpiece;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class FeedbackPage extends AppCompatActivity {

    private EditText nameUser;
    private String jsonName;
    private EditText descriptionFeedback;
    private String jsonDescr;
    private Spinner optionVal;
    private String jsonOption;
    private Button submitButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_feedback);

        submitButton = findViewById(R.id.submitBtn);
        nameUser = findViewById(R.id.nameInput);
        descriptionFeedback = findViewById(R.id.descriptionFeedback);
        optionVal = findViewById(R.id.subjectsDropdown);

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                jsonName = nameUser.getText().toString();
                jsonDescr = descriptionFeedback.getText().toString();
                jsonOption = optionVal.getSelectedItem().toString();

                String jsonParse = "{" +
                        "\"name\"" + "\"" + jsonName + "\"," +
                        "\"subject\"" + "\"" + jsonOption + "\"," +
                        "\"description\"" + "\"" + jsonDescr + "\"" +
                        "}";
                Toast.makeText(getApplicationContext(), jsonParse, Toast.LENGTH_SHORT).show();
            }
        });

    }

}
