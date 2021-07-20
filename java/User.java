package com.example.hobbyroute_ver_1_2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class User {

    private String id, name, keyword;

    public User (String name, String keyword, String id){

        this.name = name;
        this.id = id;
        this.keyword = keyword;

    }

    public String getName(){
        return name;
    }

    public String getKeyword(){
        return keyword;
    }

    public String getId(){ return id; }

    public void setKeyword(String keyword){
        this.keyword = keyword;
    }

}
