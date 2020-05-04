package com.omega.mouthpiece ;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ImageUploadFragment extends Fragment {

    private static final int RESULT_LOAD_IMAGE = 1;

    private Button btnUpload;
    private Button btnNext;
    //private Button btnConfirm;
    private Button btnCancel;

    private TextView mouthShapeNumber;
    private ImageView egImage;

    public ImageView userImage;
    private ImageView userImage2;
    private ImageView userImage3;
    private ImageView userImage4;
    private ImageView userImage5;
    private ImageView userImage6;
    private ImageView userImage7;
    private ImageView userImage8;
    private ImageView userImage9;
    private ImageView userImage10;
    private ImageView userImage11;
    private ImageView userImage12;

    //Int var used to go through the example images and cycle between images the user needs to upload
    private int i = 1;
    // Uri var used store user's selected images
    public Uri imageUri, imageUriL, imageUriO, imageUriCDGKNSTXYZ, imageUriFV, imageUriQW;
    public Uri imageUriBMP, imageUriU, imageUriEe, imageUriR, imageUriTh, imageUriChJSh;

    ArrayList<Uri> imageUriList = new ArrayList<Uri>();
    Bundle imageBundle = new Bundle(12);

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == getActivity().RESULT_OK && data != null) {
            Uri selectedImage = data.getData();

            switch (i) {
                case 1:
                    userImage.setImageURI(selectedImage);
                    imageUri = data.getData();
                    imageUriList.add(imageUri);
                    break;
                case 2:
                    userImage2.setImageURI(selectedImage);
                    imageUriL = data.getData();
                    imageUriList.add(imageUriL);
                    break;
                case 3:
                    userImage3.setImageURI(selectedImage);
                    imageUriO = data.getData();
                    imageUriList.add(imageUriO);
                    break;
                case 4:
                    userImage4.setImageURI(selectedImage);
                    imageUriCDGKNSTXYZ = data.getData();
                    imageUriList.add(imageUriCDGKNSTXYZ);
                    break;
                case 5:
                    userImage5.setImageURI(selectedImage);
                    imageUriFV = data.getData();
                    imageUriList.add(imageUriFV);
                    break;
                case 6:
                    userImage6.setImageURI(selectedImage);
                    imageUriQW = data.getData();
                    imageUriList.add(imageUriQW);
                    break;
                case 7:
                    userImage7.setImageURI(selectedImage);
                    imageUriBMP = data.getData();
                    imageUriList.add(imageUriBMP);
                    break;
                case 8:
                    userImage8.setImageURI(selectedImage);
                    imageUriU = data.getData();
                    imageUriList.add(imageUriU);
                    break;
                case 9:
                    userImage9.setImageURI(selectedImage);
                    imageUriEe = data.getData();
                    imageUriList.add(imageUriEe);
                    break;
                case 10:
                    userImage10.setImageURI(selectedImage);
                    imageUriR = data.getData();
                    imageUriList.add(imageUriR);
                    break;
                case 11:
                    userImage11.setImageURI(selectedImage);
                    imageUriTh = data.getData();
                    imageUriList.add(imageUriTh);
                    break;
                case 12:
                    userImage12.setImageURI(selectedImage);
                    imageUriChJSh = data.getData();
                    imageUriList.add(imageUriChJSh);
                    break;
            }

        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_mouthupload_image, container, false);
        btnUpload = root.findViewById(R.id.btn_choose_image);
        btnNext = root.findViewById(R.id.btn_nxt);
        btnNext.bringToFront();
        btnCancel = root.findViewById(R.id.btn_cancel);

        mouthShapeNumber = root.findViewById(R.id.textView2);

        egImage = root.findViewById(R.id.imageView2);
        userImage = root.findViewById(R.id.uImage1);
        userImage2 = root.findViewById(R.id.uImage2);
        userImage3 = root.findViewById(R.id.uImage3);
        userImage4 = root.findViewById(R.id.uImage4);
        userImage5 = root.findViewById(R.id.uImage5);
        userImage6 = root.findViewById(R.id.uImage6);
        userImage7 = root.findViewById(R.id.uImage7);
        userImage8 = root.findViewById(R.id.uImage8);
        userImage9 = root.findViewById(R.id.uImage9);
        userImage10 = root.findViewById(R.id.uImage10);
        userImage11= root.findViewById(R.id.uImage11);
        userImage12 = root.findViewById(R.id.uImage12);

        userImage.setVisibility(userImage.VISIBLE);
        userImage.bringToFront();
        userImage2.setVisibility(userImage2.INVISIBLE);
        userImage3.setVisibility(userImage3.INVISIBLE);
        userImage4.setVisibility(userImage4.INVISIBLE);
        userImage5.setVisibility(userImage5.INVISIBLE);
        userImage6.setVisibility(userImage6.INVISIBLE);
        userImage7.setVisibility(userImage7.INVISIBLE);
        userImage8.setVisibility(userImage8.INVISIBLE);
        userImage9.setVisibility(userImage9.INVISIBLE);
        userImage10.setVisibility(userImage10.INVISIBLE);
        userImage11.setVisibility(userImage11.INVISIBLE);
        userImage12.setVisibility(userImage12.INVISIBLE);


        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), RESULT_LOAD_IMAGE);
            }
        });

        mouthShapeNumber.setText("Mouth Shape " + i + "/12");
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(i == 12) {

                    imageConfirmationFragment fragment2 = new imageConfirmationFragment();
                    imageBundle.putParcelable("imageAEI", imageUri);
                    imageBundle.putParcelable("imageL", imageUriL);
                    imageBundle.putParcelable("imageO", imageUriO);
                    imageBundle.putParcelable("imageCDGKNSTXYZ", imageUriCDGKNSTXYZ);
                    imageBundle.putParcelable("imageFV", imageUriFV);
                    imageBundle.putParcelable("imageQW", imageUriQW);
                    imageBundle.putParcelable("imageBMP", imageUriBMP);
                    imageBundle.putParcelable("imageU", imageUriU);
                    imageBundle.putParcelable("imageEe", imageUriEe);
                    imageBundle.putParcelable("imageR", imageUriR);
                    imageBundle.putParcelable("imageTh", imageUriTh);
                    imageBundle.putParcelable("imageChJSh", imageUriChJSh);
                    fragment2.setArguments(imageBundle);
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment, fragment2);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                else {
                    i = i + 1;
                    mouthShapeNumber.setText("Mouth Shape " + i + "/12");
                    switch(i)
                    {
                        case 1:
                            egImage.setImageResource(R.drawable.mouth_formants_1);
                            userImage.setVisibility(userImage.VISIBLE);
                                break;
                        case 2:
                            egImage.setImageResource(R.drawable.mouth_formants_2);
                            userImage.setVisibility(userImage.INVISIBLE);
                            userImage2.setVisibility(userImage2.VISIBLE);
                            userImage2.bringToFront();
                            break;
                        case 3:
                            egImage.setImageResource(R.drawable.mouth_formants_3);
                            userImage2.setVisibility(userImage2.INVISIBLE);
                            userImage3.setVisibility(userImage3.VISIBLE);
                            userImage3.bringToFront();
                            break;
                        case 4:
                            egImage.setImageResource(R.drawable.mouth_formants_4);
                            userImage3.setVisibility(userImage3.INVISIBLE);
                            userImage4.setVisibility(userImage4.VISIBLE);
                            userImage4.bringToFront();
                            break;
                        case 5:
                            egImage.setImageResource(R.drawable.mouth_formants_5);
                            userImage4.setVisibility(userImage4.INVISIBLE);
                            userImage5.setVisibility(userImage5.VISIBLE);
                            userImage5.bringToFront();
                            break;
                        case 6:
                            egImage.setImageResource(R.drawable.mouth_formants_6);
                            userImage5.setVisibility(userImage5.INVISIBLE);
                            userImage6.setVisibility(userImage6.VISIBLE);
                            userImage6.bringToFront();
                            break;
                        case 7:
                            egImage.setImageResource(R.drawable.mouth_formants_7);
                            userImage6.setVisibility(userImage6.INVISIBLE);
                            userImage7.setVisibility(userImage7.VISIBLE);
                            userImage7.bringToFront();
                            break;
                        case 8:
                            egImage.setImageResource(R.drawable.mouth_formants_8);
                            userImage7.setVisibility(userImage7.INVISIBLE);
                            userImage8.setVisibility(userImage8.VISIBLE);
                            userImage8.bringToFront();
                            break;
                        case 9:
                            egImage.setImageResource(R.drawable.mouth_formants_9);
                            userImage8.setVisibility(userImage8.INVISIBLE);
                            userImage9.setVisibility(userImage9.VISIBLE);
                            userImage9.bringToFront();
                            break;
                        case 10:
                            egImage.setImageResource(R.drawable.mouth_formants_10);
                            userImage9.setVisibility(userImage9.INVISIBLE);
                            userImage10.setVisibility(userImage10.VISIBLE);
                            userImage10.bringToFront();
                            break;
                        case 11:
                            egImage.setImageResource(R.drawable.mouth_formants_11);
                            userImage10.setVisibility(userImage10.INVISIBLE);
                            userImage11.setVisibility(userImage11.VISIBLE);
                            userImage11.bringToFront();
                            break;
                        case 12:
                            egImage.setImageResource(R.drawable.mouth_formants_12);
                            userImage11.setVisibility(userImage11.INVISIBLE);
                            userImage12.setVisibility(userImage12.VISIBLE);
                            userImage12.bringToFront();
                            btnNext.setText("Click to Confirm");
                            break;
                    }
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UploadMouthFrontFragment fragment2 = new UploadMouthFrontFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


        return root;

    }
}
