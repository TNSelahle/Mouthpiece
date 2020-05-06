package com.omega.mouthpiece;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

//send date to database
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;

public class imageConfirmationFragment extends Fragment {
    private String mExampleString; //
    private int mExampleColor = Color.RED; //
    private float mExampleDimension = 0; //
    private Drawable mExampleDrawable;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

    private static final int STORAGE_PERMISSION_CODE = 2342;

    //imageView Variables
    private ImageView helperIV;
    private ImageView vImage1_AEI;
    private ImageView vImage2_L;
    private ImageView vImage3_O;
    private ImageView vImage4_CDGKNSTXYZ;
    private ImageView vImage5_FV;
    private ImageView vImage6_QW;
    private ImageView vImage7_BMP;
    private ImageView vImage8_U;
    private ImageView vImage9_Ee;
    private ImageView vImage10_R;
    private ImageView vImage11_Th;
    private ImageView vImage12_Ch_J_Sh;

    Uri imageUri;
    Bundle imageBundle = new Bundle();
    Button btnAccept;
    Button btnCancel;
    String encodeImage; // converts image(s) to base64

    //API variables
    private RequestQueue feedbackRequestQueue;
    private StringRequest feedbackStringRequest;
    private JSONObject jsonBodyParse;
    private JSONObject jsonMouthpieceParse;
    //private String urlGetUsers = "http://102.133.170.83:5000/getUsers";
    private String urlUpload = "http://102.133.170.83:3000/sharingapi/mouthpiece/upload";

    private String email;
    private String username;
    private Integer mouthpieceID;
    //private String[] mouthpiecesArr;
    String[] mouthpiecesArr;
    private Integer rating;
    private Integer downloads;
    private String dateTime;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mouthupload_confirmation, container, false);

        vImage1_AEI = root.findViewById(R.id.image1_AEI);
        vImage2_L = root.findViewById(R.id.image2_L);
        vImage3_O = root.findViewById(R.id.image3_O);
        vImage4_CDGKNSTXYZ = root.findViewById(R.id.image4_CDGKNSTXYZ);
        vImage5_FV = root.findViewById(R.id.image5_FV);
        vImage6_QW = root.findViewById(R.id.image6_QW);
        vImage7_BMP = root.findViewById(R.id.image7_BMP);
        vImage8_U = root.findViewById(R.id.image8_U);
        vImage9_Ee = root.findViewById(R.id.image9_Ee);
        vImage10_R = root.findViewById(R.id.image10_R);
        vImage11_Th = root.findViewById(R.id.image11_Th);
        vImage12_Ch_J_Sh = root.findViewById(R.id.image12_ChJSh);

        //sets the image from the mouth creation activity.
        if(imageBundle != null)
        {
            imageBundle = getArguments();
            imageUri = imageBundle.getParcelable("imageAEI");
            vImage1_AEI.setImageURI(imageUri);
            imageUri = imageBundle.getParcelable("imageL");
            vImage2_L.setImageURI(imageUri);
            imageUri = imageBundle.getParcelable("imageO");
            vImage3_O.setImageURI(imageUri);
            imageUri = imageBundle.getParcelable("imageCDGKNSTXYZ");
            vImage4_CDGKNSTXYZ.setImageURI(imageUri);
            imageUri = imageBundle.getParcelable("imageFV");
            vImage5_FV.setImageURI(imageUri);
            imageUri = imageBundle.getParcelable("imageQW");
            vImage6_QW.setImageURI(imageUri);
            imageUri = imageBundle.getParcelable("imageBMP");
            vImage7_BMP.setImageURI(imageUri);
            imageUri = imageBundle.getParcelable("imageU");
            vImage8_U.setImageURI(imageUri);
            imageUri = imageBundle.getParcelable("imageEe");
            vImage9_Ee.setImageURI(imageUri);
            imageUri = imageBundle.getParcelable("imageR");
            vImage10_R.setImageURI(imageUri);
            imageUri = imageBundle.getParcelable("imageTh");
            vImage11_Th.setImageURI(imageUri);
            imageUri = imageBundle.getParcelable("imageChJSh");
            vImage12_Ch_J_Sh.setImageURI(imageUri);
        }

        btnAccept = root.findViewById(R.id.confrimButtonAccept);
        btnCancel = root.findViewById(R.id.confirmButtonCancel);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // TODO: upload to ShareAPI
            // TODO: Access Internal Storage
            // TODO: find a way to create and access to app specific folder

                getUserInfo();
                uploadMouthPieces();

                UploadMouthFrontFragment fragment4 = new UploadMouthFrontFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment4);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadMouthFrontFragment fragment3 = new UploadMouthFrontFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment3);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        /*getParentFragmentManager().setFragmentResultListner("key",this, new FragmentResultListner()
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle buundle){

            }
        )*/

        return root;
    }
    //function for retrieving the user's username and email for uploading
    public void getUserInfo(){
        //mock data for now

        username = "tester";
        email = "testing@gmail.com";

        //real parse data
        mouthpieceID = 0;
        downloads = 0;
        rating = 0;
        //dateTime = "08:00 Mon";

        //set mouthpieces (mock data for now)
        mouthpiecesArr = new String[12];
        for (int i = 0; i < 12; i++){
            mouthpiecesArr[i] = "testBase64Code" + i;
        }


        try {
            jsonMouthpieceParse = new JSONObject();
            jsonMouthpieceParse.put("f0", mouthpiecesArr[0]);
            jsonMouthpieceParse.put("f1", mouthpiecesArr[1]);
            jsonMouthpieceParse.put("f2", mouthpiecesArr[2]);
            jsonMouthpieceParse.put("f3", mouthpiecesArr[3]);
            jsonMouthpieceParse.put("f4", mouthpiecesArr[4]);
            jsonMouthpieceParse.put("f5", mouthpiecesArr[5]);
            jsonMouthpieceParse.put("f6", mouthpiecesArr[6]);
            jsonMouthpieceParse.put("f7", mouthpiecesArr[7]);
            jsonMouthpieceParse.put("f8", mouthpiecesArr[8]);
            jsonMouthpieceParse.put("f9", mouthpiecesArr[9]);
            jsonMouthpieceParse.put("f10", mouthpiecesArr[10]);
            jsonMouthpieceParse.put("f11", mouthpiecesArr[11]);
            final String uploadRequestBody = jsonMouthpieceParse.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }




    }
    //function for uploading the user created mouthpieces
    public void uploadMouthPieces(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        //send info through

        try {
            jsonBodyParse = new JSONObject();
            jsonBodyParse.put("name", username);
            jsonBodyParse.put("email", email);
            jsonBodyParse.put("mouthpiece_id", mouthpieceID);
            jsonBodyParse.put("downloads", downloads);
            jsonBodyParse.put("rating", rating);
            //jsonBodyParse.put("dataTime", rating);
            jsonBodyParse.put("formants", jsonMouthpieceParse);
            //jsonBodyParse.put("formants", jsonMouthpieceParse);
            final String uploadRequestBody = jsonBodyParse.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlUpload, jsonBodyParse,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getActivity(), "String Response : "+ response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error getting response" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);

    }

    private void requestStoragePermission(){
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;
        ActivityCompat.requestPermissions(getActivity(),new String []{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //if()
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        ((MainActivity) getActivity())
                .setActionBarTitle("Image Confirmation");

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }



}
