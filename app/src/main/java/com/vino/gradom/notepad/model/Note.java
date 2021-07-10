package com.vino.gradom.notepad.model;

import java.io.Serializable;

public class Note implements Serializable {

    private String title;
    private String description;
    private String imageURI;

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }
}
