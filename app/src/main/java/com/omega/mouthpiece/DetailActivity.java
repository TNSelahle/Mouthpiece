package com.omega.mouthpiece;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import static com.omega.mouthpiece.MouthSelection.EXTRA_CREATOR;
import static com.omega.mouthpiece.MouthSelection.EXTRA_LIKES;
import static com.omega.mouthpiece.MouthSelection.EXTRA_URL;
import static com.omega.mouthpiece.MouthSelection.EXTRA_DOWNLOADS;

public class DetailActivity extends AppCompatActivity {
    private Button btn;
    @Override

    //Activity view for when clicked on mouth item in mouth selection
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        btn = findViewById(R.id.selectButton);
        Intent intent = getIntent();
        String imageURL = intent.getStringExtra(EXTRA_URL);
        String creatorName = intent.getStringExtra(EXTRA_CREATOR);
        int ratings = intent.getIntExtra(EXTRA_LIKES, 0);
        int downloads = intent.getIntExtra(EXTRA_DOWNLOADS, 0);
        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textViewCreator = findViewById(R.id.text_view_creator_detail);
        TextView textViewLikes = findViewById(R.id.text_view_like_detail);
        TextView textViewDownloads = findViewById(R.id.text_view_downloads);

        Picasso.with(this).load(imageURL).fit().centerInside().into(imageView);
        textViewCreator.setText(creatorName);
        textViewLikes.setText("Ratings: " + ratings);
        textViewDownloads.setText("Downloads: "+ downloads);

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(DetailActivity.this, Converter.class);
                startActivity(intent);
            }
        });
    }
}