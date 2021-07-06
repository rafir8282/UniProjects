package com.example.mtassignmentone;

import java.util.Date;

public class Food {
    String title;
    int imageResource;
    Date date;

    public Food(String title, int imageResource, Date date) {
        this.title = title;
        this.imageResource = imageResource;
        this.date = date;
    }

    public String getTitle(){
        return this.title;
    }

    public int getImageResource(){
        return this.imageResource;
    }

    public String getDateString(){
        return this.date.toString();
    }

    @Override
    public String toString(){
        return title;
    }
}
