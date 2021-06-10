package com.hfad.myficmiapp.model;


import java.io.Serializable;

public class TheWordModel implements Serializable {
    String topic;
    String speaker;
    String message;
    String text;
    String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String key_id;

    String favStatus;

    public TheWordModel() {
    }

    public TheWordModel(String topic, String speaker, String message, String text, String key_id, String favStatus) {
        this.topic = topic;
        this.speaker = speaker;
        this.message = message;
        this.text = text;
        this.key_id = key_id;
        this.favStatus = favStatus;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;
    }


}
