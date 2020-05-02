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
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_image_confirmation);

        vImage1_AEI = (ImageView) findViewById(R.id.image1_AEI);
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

        //sets the image from the mouth creation activity.
        if(getIntent().getExtras() != null)
        {
            imageUri = Uri.parse(getIntent().getStringExtra("imageAEI"));
            vImage1_AEI.setImageURI(imageUri);
            imageUri = Uri.parse(getIntent().getStringExtra("imageL"));
            vImage2_L.setImageURI(imageUri);
            imageUri = Uri.parse(getIntent().getStringExtra("imageO"));
            vImage3_O.setImageURI(imageUri);
            imageUri = Uri.parse(getIntent().getStringExtra("imageCDGKNSTXYZ"));
            vImage4_CDGKNSTXYZ.setImageURI(imageUri);
            imageUri = Uri.parse(getIntent().getStringExtra("imageFV"));
            vImage5_FV.setImageURI(imageUri);
            imageUri = Uri.parse(getIntent().getStringExtra("imageQW"));
            vImage6_QW.setImageURI(imageUri);
            imageUri = Uri.parse(getIntent().getStringExtra("imageBMP"));
            vImage7_BMP.setImageURI(imageUri);
            imageUri = Uri.parse(getIntent().getStringExtra("imageU"));
            vImage8_U.setImageURI(imageUri);
            imageUri = Uri.parse(getIntent().getStringExtra("imageEe"));
            vImage9_Ee.setImageURI(imageUri);
            imageUri = Uri.parse(getIntent().getStringExtra("imageR"));
            vImage10_R.setImageURI(imageUri);
            imageUri = Uri.parse(getIntent().getStringExtra("imageTh"));
            vImage11_Th.setImageURI(imageUri);
            imageUri = Uri.parse(getIntent().getStringExtra("imageChJSh"));
            vImage12_Ch_J_Sh.setImageURI(imageUri);
        }
    }

}
