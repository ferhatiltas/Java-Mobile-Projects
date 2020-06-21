package com.ferhatiltas.customlistview_editfilter_havalimani;

public class Airport {
    String name,code,city,country;
    int imgSrc;

    public Airport(String name, String code, String city, String country, int imgSrc) {
        this.name = name;
        this.code = code;
        this.city = city;
        this.country = country;
        this.imgSrc = imgSrc;
    }

    public Airport() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }
}
