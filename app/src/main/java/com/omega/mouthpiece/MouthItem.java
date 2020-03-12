package com.omega.mouthpiece;

public class MouthItem {
    private String mImageURL;
    private String mCreator;
    private int mRatings;

    public MouthItem(String imageURL, String creator,int ratings)
    {
        mImageURL = imageURL;
        mCreator = creator;
        mRatings = ratings;
    }

    public String getImageURL()
    {
        return mImageURL;
    }

    public String getCreator()
    {
        return mCreator;
    }

    public int getRatings()
    {
        return mRatings;
    }


}
