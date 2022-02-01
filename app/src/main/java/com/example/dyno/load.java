package com.example.dyno;

import com.google.firebase.database.Exclude;

public class load {
    private String mName;
    private String mImageUrl;
    private static String mKey;
    public load() {
        //empty constructor needed
    }

    public load(String  name,String imageUrl) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        mName = name;
        mImageUrl = imageUrl;
    }

    public load(String trim) {
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }
    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    @Exclude
    public String getKey(){
        return mKey;
    }
    @Exclude
    public static void setKey(String key){
        mKey =key;
    }



}
