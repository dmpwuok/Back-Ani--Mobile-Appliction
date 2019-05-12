package com.prabathweerapana.back_ani.models;

public class ModelCalls {

    private String name,number,duration,date;

    public ModelCalls(String number, String duration, String date) {
        this.number = number;
        this.duration = duration;
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
