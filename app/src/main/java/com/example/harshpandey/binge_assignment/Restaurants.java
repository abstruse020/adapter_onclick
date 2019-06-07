package com.example.harshpandey.binge_assignment;


public class Restaurants {

    // Name of Restaurants
    private String mRestaurantsName;

    // distance if you want to show
    private String mRestaurantsDistance;

    private String mTiming;

    private String mRestaurantsId;

    // Drawable resource ID
    private String mImageResourceId;

    public Restaurants(String vName, String vDistance, String vTiming, String vId,String vImage)
    {
        mRestaurantsName = vName;
        mRestaurantsDistance = vDistance;
        mTiming = vTiming;
        mRestaurantsId = vId;
        mImageResourceId = vImage;
    }

    public String getRestaurantsName() {
        return mRestaurantsName;
    }

    public String getRestaurantsDistance() {
        return mRestaurantsDistance;
    }

    public String getmTiming(){
        return  mTiming;
    }

    public String getmRestaurantsId(){
        return mRestaurantsId;
    }

    public String getImageResourceId() {
        return mImageResourceId;
    }


}
