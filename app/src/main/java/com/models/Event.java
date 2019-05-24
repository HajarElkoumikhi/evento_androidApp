package com.models;

public class Event {

    private String title = "";
    private String description = "";
    private String city = "";
    private String country = "";

    public Event(String title, String description, String city, String country) {
        this.title = title;
        this.description = description;
        this.city = city;
        this.country = country;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
