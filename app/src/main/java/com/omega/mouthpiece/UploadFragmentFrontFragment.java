package com.omega.mouthpiece;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.omega.mouthpiece.R;
import com.omega.mouthpiece.UploadMouthsFrontPage;
import com.omega.mouthpiece.mouthCreation_ImageUpload;


public class UploadFragmentFrontFragment extends Fragment {


    private Button Accept;
    private Button Decline;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_mouthupload_front, container, false);
        Accept = root.findViewById(R.id.Accept_button);
        Decline = root.findViewById(R.id.Decline_button);

        Accept.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getContext(), mouthCreation_ImageUpload.class);
                startActivity(intent);
            }
        });
        Decline.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Intent intent = new Intent(RegisterPage.this, MouthSelection.class);
                //startActivity(intent);
            }
        });
        return root;
    }
}
