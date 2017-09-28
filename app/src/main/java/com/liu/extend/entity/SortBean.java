package com.liu.extend.entity;


import java.io.Serializable;

public class SortBean implements Serializable {
    private String name;
    private String tag;
    private boolean isTitle;
    private Integer image;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isTitle() {
        return isTitle;
    }

    public void setTitle(boolean title) {
        isTitle = title;
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    public SortBean(String name) {
        this.name = name;
    }
    public SortBean(String name,Integer image) {
        this.name = name;
        this.image=image;
    }
    public String getName() {
        return name;
    }







}
