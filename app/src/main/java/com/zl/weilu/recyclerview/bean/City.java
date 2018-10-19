package com.zl.weilu.recyclerview.bean;

/**
 * @Description:
 * @Author: weilu
 * @Time: 2018/10/18 0018 13:50.
 */
public class City {
    
    private int id;
    private String cityName;
    private String firstLetter;

    public City(int id, String cityName, String firstLetter) {
        this.id = id;
        this.cityName = cityName;
        this.firstLetter = firstLetter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }
}
