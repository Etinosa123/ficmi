package com.hfad.myficmiapp.model;


import java.util.Date;

public class DevotionModel {

    Date date;
   String scripture, passage;

    public DevotionModel(Date date, String scripture, String passage) {
        this.date = date;
        this.scripture = scripture;
        this.passage = passage;
    }

    public DevotionModel() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getScripture() {
        return scripture;
    }

    public void setScripture(String scripture) {
        this.scripture = scripture;
    }

    public String getPassage() {
        return passage;
    }

    public void setPassage(String passage) {
        this.passage = passage;
    }
}
