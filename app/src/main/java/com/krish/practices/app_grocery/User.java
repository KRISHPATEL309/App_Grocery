package com.krish.practices.app_grocery;

public class User {
    String Categoris_Name;
    String Image;

    public User(){

    }

    public User(String categoris_Name, String image) {
        Categoris_Name = categoris_Name;
        Image = image;
    }

    public String getCategoris_Name() {
        return Categoris_Name;
    }

    public void getCategoris_Name(String categoris_Name) {
        Categoris_Name = categoris_Name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
