package com.omega.mouthpiece;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

    public class imageConfirmation extends AppCompatActivity {
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

    public imageConfirmation()
    {
        //bmImage = null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_image_confirmation);

        vImage1_AEI = findViewById(R.id.image1_AEI);
        vImage2_L = findViewById(R.id.image2_L);
        vImage3_O = findViewById(R.id.image3_O);
        vImage4_CDGKNSTXYZ = findViewById(R.id.image4_CDGKNSTXYZ);
        vImage5_FV = findViewById(R.id.image5_FV);
        vImage6_QW = findViewById(R.id.image6_QW);
        vImage7_BMP = findViewById(R.id.image7_BMP);
        vImage8_U = findViewById(R.id.image8_U);
        vImage9_Ee = findViewById(R.id.image9_Ee);
        vImage10_R = findViewById(R.id.image10_R);
        vImage11_Th = findViewById(R.id.image11_Th);
        vImage12_Ch_J_Sh = findViewById(R.id.image12_ChJSh);

    }

    public void accept()
    {
        //TODO: implement the functionality for if button 'Accept' is clicked

    }
    public void cancel()
    {
        //TODO: implement functionality for if button 'Cancel' is clicked
    }
    public void mouth1_AEI(Bitmap bI_AEI)
    {
        //TODO: implement function to change mouth shape A,E,I
        vImage1_AEI.setImageBitmap(bI_AEI);
    }
    public void mouth2_L()
    {
        //TODO: implement function to change mouth shape L
    }
    public void mouth3_O()
    {
        //TODO: implement function to change mouth shape O
    }
    public void mouth4_CDGK()
    {
        //TODO: implement function to change mouth shape C,D,G,K,N,S,T,X,Y,Z
    }
    public void mouth5_FV()
    {
        //TODO: implement function to change mouth shape F,V
    }
    public void mouth6_QW()
    {
        //TODO: implement function to change mouth shape Q,W
    }
    public void mouth7_BMP()
    {
        //TODO: implement function to change mouth shape B,M,P
    }
    public void mouth8_U()
    {
        //TODO: implement function to change mouth shape U
    }
    public void mouth9_Ee()
    {
        //TODO: implement function to change mouth shape Ee
    }
    public void mouth10_R()
    {
        //TODO: implement function to change mouth shape R
    }
    public void mouth11_Th()
    {
        //TODO: implement function to change mouth shape Th
    }
    public void mouth12_ChJSh()
    {
        //TODO: implement function to change mouth shape Ch,J,Sh
    }




}
