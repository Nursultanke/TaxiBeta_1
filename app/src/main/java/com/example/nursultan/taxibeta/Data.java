package com.example.nursultan.taxibeta;

import android.net.Uri;

public class Data {
    String name, age;
    Uri img;

    public Uri getImg() {
        return img;
    }

    public void setImg(Uri img) {
        this.img = img;
    }

    public Data() {

    }

    public Data(String name, String age) {
        this.name = name;
        this.age = age;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

