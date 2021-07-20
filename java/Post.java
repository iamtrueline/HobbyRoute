package com.example.hobbyroute_ver_1_2;

public class Post {
    private String name;
    private String date;
    private String title;
    private String contents;
    private int id;

    public Post(int id, String date, String name, String title, String contents){
        this.name = name;
        this.title = title;
        this.contents = contents;
        this.date = date;
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public String getTitle(){
        return title;
    }
    public int getID(){
        return id;
    }
    public String getDate(){
        return date;
    }

    public String getContents(){
        return contents;
    }
}
