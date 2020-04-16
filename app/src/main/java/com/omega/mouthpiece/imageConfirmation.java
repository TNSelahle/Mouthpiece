package com.omega.mouthpiece;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class imageConfirmation extends AppCompatActivity {
    private String mExampleString; //
    private int mExampleColor = Color.RED; //
    private float mExampleDimension = 0; //
    private Drawable mExampleDrawable;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_image_confirmation);
    }

    public void accept()
    {
        //TODO: implement the functionality for if button 'Accept' is clicked
    }
    public void cancel()
    {
        //TODO: implement functionality for if button 'Cancel' is clicked
    }
    public void mouth1_AEI()
    {
        //TODO: implement function to change mouth shape A,E,I
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
