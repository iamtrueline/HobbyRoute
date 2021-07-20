package com.example.hobbyroute_ver_1_2;

public class Hobby implements Comparable<Hobby>{
    private String name, keyword, image;
    private int location;
    private double mark;

    public Hobby (String name, String keyword, String image, int location, double mark){

        this.name = name;
        this.image = image;
        this.keyword = keyword;
        this.location = location;
        this.mark = mark;
    }

    public String getName(){
        return name;
    }

    public String getKeyword(){
        return keyword;
    }

    public String getImage(){
        return image;
    }

    public int getLocation(){
        return location;
    }

    public double getMark(){
        return mark;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setKeyword(String keyword){
        this.keyword = keyword;
    }

    public void setImage(String image){
        this.image = image;
    }

    public void setLocation(int location){
        this.location = location;
    }

    public void setMark(double mark){
        this.mark = mark;
    }

    public int compareTo(Hobby hobby){
        if(this.mark<hobby.getMark()) {
            return 1;
        } else if(this.mark==hobby.getMark()){
            return 0;
        }
        else
            return -1;
    }
}
