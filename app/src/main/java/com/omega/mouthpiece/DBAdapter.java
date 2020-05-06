package com.omega.mouthpiece;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DBAdapter extends RecyclerView.Adapter<DBAdapter.DBViewHolder> {

    private Context mContext;
    private ArrayList<MouthItem> mMouthList;
    private OnItemClickListener mListener;

    //Activity for Mouth Items
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public DBAdapter(Context context, ArrayList<MouthItem> mouthList)
    {
        mContext = context;
        mMouthList = mouthList;
    }

    @Override
    public DBViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(mContext).inflate(R.layout.mouth_item,parent,false);
        return new DBViewHolder(v);
    }

    @Override
    public void onBindViewHolder( DBViewHolder holder, int position) {

        MouthItem currentItem = mMouthList.get(position);

        String imageURL = currentItem.getImageURL();
        String creatorName = currentItem.getCreator();
        int ratings = currentItem.getRatings();

        holder.mTextViewCreator.setText(creatorName);
        holder.mTextViewRatings.setText("Ratings: " + ratings);
        Picasso.with(mContext).load(imageURL).fit().centerInside().into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mMouthList.size();
    }


    public class DBViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView mImageView;
        public TextView mTextViewCreator;
        public TextView mTextViewRatings;

        public DBViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewCreator = itemView.findViewById(R.id.text_view_creator);
            mTextViewRatings = itemView.findViewById(R.id.text_view_rating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
