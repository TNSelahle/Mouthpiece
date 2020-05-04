package com.omega.mouthpiece;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class FeedbackFragment extends Fragment {

    private EditText nameUser;
    private String jsonName;
    private EditText descriptionFeedback;
    private String jsonDescr;
    private Spinner optionVal;
    private String jsonOption;
    private String jsonEmail;
    private Button submitButton;
    private Button exitButton;
    private CheckBox anon;
    private Boolean isAnon;
    private EditText email;
    private TextView instruct;
    private TextView emailHeader;
    private TextView nameHeader;

    private RequestQueue feedbackRequestQueue;
    private StringRequest feedbackStringRequest;
    private JSONObject jsonBodyParse;
    private ConstraintLayout FeedbackConstr;
   // private String url = "102.133.170.83:5000/getFeedback";
    private String url = "http://102.133.170.83:5000/addFeedback";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final FragmentManager fm = getFragmentManager();

        View root = inflater.inflate(R.layout.fragment_feedback, container, false);

        submitButton = root.findViewById(R.id.submitBtn);
        nameUser = root.findViewById(R.id.nameInput);
        descriptionFeedback = root.findViewById(R.id.descriptionFeedback);
        optionVal = root.findViewById(R.id.subjectsDropdown);
        exitButton = root.findViewById(R.id.exitButton);
        anon = root.findViewById(R.id.anonCheck);
        email = root.findViewById(R.id.emailInput);
        isAnon = anon.isChecked();
        emailHeader = root.findViewById(R.id.emailText);
        nameHeader = root.findViewById(R.id.nameOfUser);
        FeedbackConstr = root.findViewById(R.id.feedbackLayout);
        instruct = root.findViewById(R.id.feedbackInstr);
        setTheme();


        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                jsonName = nameUser.getText().toString();
                jsonDescr = descriptionFeedback.getText().toString();
                jsonOption = optionVal.getSelectedItem().toString();
                jsonEmail = email.getText().toString();

                sendJsonFeedback();
                if(fm.getBackStackEntryCount() > 0){
                    fm.popBackStack();
                }
                else{
                    startActivity(new Intent(getContext(), MainActivity.class));
                }

                //

            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fm.getBackStackEntryCount() > 0){
                    fm.popBackStack();
                }
                else{
                    startActivity(new Intent(getContext(), MainActivity.class));
                }
                //startActivity(new Intent(getContext(), SettingFragment.class));
            }
        });
        anon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                            @Override
                                            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                                                if (isChecked){
                                                    email.setEnabled(false);
                                                    emailHeader.setTextColor(Color.parseColor("#aaaaaa"));
                                                    nameHeader.setTextColor(Color.parseColor("#aaaaaa"));
                                                    nameUser.setText("Anonymous");
                                                    nameUser.setEnabled(false);
                                                }
                                                else{
                                                    email.setEnabled(true);
                                                    emailHeader.setTextColor(Color.parseColor("#EE1C31"));
                                                    nameHeader.setTextColor(Color.parseColor("#EE1C31"));
                                                    nameUser.setEnabled(true);
                                                }
                                            }
                                        }
        );

        return root;
    }

    private void sendJsonFeedback() {

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        try {

            jsonBodyParse = new JSONObject();
            jsonBodyParse.put("email", jsonEmail);
            jsonBodyParse.put("name", jsonName);
            jsonBodyParse.put("subject", jsonOption);
            jsonBodyParse.put("description", jsonDescr);
            final String fbRequestBody = jsonBodyParse.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBodyParse,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getActivity(), "String Response : "+ response.toString(), Toast.LENGTH_SHORT).show();
                        //resultTextView.setText("String Response : "+ response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error getting response" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);

    }

    public void setTheme() {
        if(GlobalVariableMode.mode == true){

            FeedbackConstr.setBackgroundColor(Color.parseColor("#000000"));
            //button colours
            instruct.setTextColor(Color.parseColor("#FFFFFF"));
            email.setTextColor(Color.parseColor("#FFFFFF"));
            descriptionFeedback.setTextColor(Color.parseColor("#FFFFFF"));
            nameUser.setTextColor(Color.parseColor("#FFFFFF"));
            anon.setTextColor(Color.parseColor("#FFFFFF"));
            //optionVal.setForeground(Color.parseColor("#FFFFFF"));
            //button colours

        }
        else{

            FeedbackConstr.setBackgroundColor(Color.parseColor("#FFFFFF"));
            //button colours
            instruct.setTextColor(Color.parseColor("#000000"));
            email.setTextColor(Color.parseColor("#000000"));
            descriptionFeedback.setTextColor(Color.parseColor("#000000"));
            nameUser.setTextColor(Color.parseColor("#000000"));
            anon.setTextColor(Color.parseColor("#000000"));
            //button colours

        }

    }

}