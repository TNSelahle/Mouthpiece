package com.omega.mouthpiece;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class imageConfirmationFragment extends Fragment {
    private String mExampleString; //
    private int mExampleColor = Color.RED; //
    private float mExampleDimension = 0; //
    private Drawable mExampleDrawable;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

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

    //bitmap - from previous activities
    Bitmap bmImage;
    Uri imageUri;

    public imageConfirmationFragment()
    {
        //bmImage = null;
    }



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
        if (getActivity().getIntent().getExtras() != null) {
            imageUri = Uri.parse(getActivity().getIntent().getStringExtra("imageAEI"));
            vImage1_AEI.setImageURI(imageUri);
            imageUri = Uri.parse(getActivity().getIntent().getStringExtra("imageL"));
            vImage2_L.setImageURI(imageUri);
            imageUri = Uri.parse(getActivity().getIntent().getStringExtra("imageO"));
            vImage3_O.setImageURI(imageUri);
            imageUri = Uri.parse(getActivity().getIntent().getStringExtra("imageCDGKNSTXYZ"));
            vImage4_CDGKNSTXYZ.setImageURI(imageUri);
            imageUri = Uri.parse(getActivity().getIntent().getStringExtra("imageFV"));
            vImage5_FV.setImageURI(imageUri);
            imageUri = Uri.parse(getActivity().getIntent().getStringExtra("imageQW"));
            vImage6_QW.setImageURI(imageUri);
            imageUri = Uri.parse(getActivity().getIntent().getStringExtra("imageBMP"));
            vImage7_BMP.setImageURI(imageUri);
            imageUri = Uri.parse(getActivity().getIntent().getStringExtra("imageU"));
            vImage8_U.setImageURI(imageUri);
            imageUri = Uri.parse(getActivity().getIntent().getStringExtra("imageEe"));
            vImage9_Ee.setImageURI(imageUri);
            imageUri = Uri.parse(getActivity().getIntent().getStringExtra("imageR"));
            vImage10_R.setImageURI(imageUri);
            imageUri = Uri.parse(getActivity().getIntent().getStringExtra("imageTh"));
            vImage11_Th.setImageURI(imageUri);
            imageUri = Uri.parse(getActivity().getIntent().getStringExtra("imageChJSh"));
            vImage12_Ch_J_Sh.setImageURI(imageUri);
        }

        return root;
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
