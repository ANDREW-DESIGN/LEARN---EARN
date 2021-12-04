package com.example.dyno;

import com.google.firebase.database.Exclude;

public class Model {
    String tutor_name,courses,timings,base_price,phone;
    private static String mKey;


    public Model() {
    }

    public Model(String tutor_name, String courses, String timings, String base_price, String phone) {
        this.tutor_name=tutor_name;
        this.courses=courses;
        this.timings=timings;
        this.base_price=base_price;
        this.phone=phone;
    }

    public String getTutor_name() {
        return tutor_name;
    }

    public String getCourses() {
        return courses;
    }

    public String getTimings() {
        return timings;
    }

    public String getBase_price() {
        return base_price;
    }

    public String getPhone() {
        return phone;
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

