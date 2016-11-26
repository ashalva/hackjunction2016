package com.junction.hack.busjunctionchallenge.models;

import java.io.Serializable;

public class Story implements Serializable {
    private int image;
    private String title;
    private String description;
    private int likes;
    private int suprise;
    private int hart;

    public Story(int image, String title, String description, int likes, int suprise, int hart) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.likes = likes;
        this.suprise = suprise;
        this.hart = hart;
    }

    public Story(int image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getSuprise() {
        return suprise;
    }

    public void setSuprise(int suprise) {
        this.suprise = suprise;
    }

    public int getHart() {
        return hart;
    }

    public void setHart(int hart) {
        this.hart = hart;
    }
}
