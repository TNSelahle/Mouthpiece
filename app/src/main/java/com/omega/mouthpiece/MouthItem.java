package com.omega.mouthpiece;

public class MouthItem {
    private String mImageURL;
    private String mCreator;
    private int mRatings;
    private int mDownloads;

    public MouthItem(String imageURL, String creator, int ratings, int downloads)
    {
        mImageURL = imageURL;
        mCreator = creator;
        mRatings = ratings;
        mDownloads= downloads;
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

    public int getDownloads()
    {
        return mDownloads;
    }
}
