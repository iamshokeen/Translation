package com.whyshoudi.translation;

import android.content.Context;

/**
 * Created by Saksham Raj Shokeen on 12/21/2017.
 */

public class word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int    mResourceImageId;
    private int    mResourceAudioId;
    private int    checkAudio=-1;


//    set up constructor
//    send the strings you want to use as parameters

    public word(String DefaultTranslation,String MiwokTranslation,int resourceImageId,int resourceAudioId){
        mMiwokTranslation = MiwokTranslation;
        mDefaultTranslation = DefaultTranslation;
        mResourceImageId = resourceImageId;
        mResourceAudioId = resourceAudioId;
    }
    public word(String DefaultTranslation,String MiwokTranslation,int resourceImageId){
        mMiwokTranslation = MiwokTranslation;
        mDefaultTranslation = DefaultTranslation;
        mResourceImageId = resourceImageId;
    }
    public word(String DefaultTranslation,String MiwokTranslation){
        mMiwokTranslation = MiwokTranslation;
        mDefaultTranslation = DefaultTranslation;
    }
    public String getDefaultTranslation() {

        return  mDefaultTranslation ;
    }
    public String getMiwokTranslation()
    {
        return mMiwokTranslation;
    }
    public int getImageResourceId()
    {
        return mResourceImageId;
    }
    public int getAudioResourceId()
    {
        return mResourceAudioId;
    }


    public boolean isHasaudio(){
        checkAudio = mResourceAudioId;
        if (checkAudio != -1){
          return true;
        }
        if(checkAudio == -1) {
            return false;
        }
        else {
            return false;
        }
    }




}
