package com.example.coronavirus;

public class Item {
    private String country;
    private int cases;
    private int deaths;
    private int recovered;
    private double latitude;
    private double longitude;
    private int todayCases;
    private int todayDeaths;
    private int active;
    private int critical;
    public Item(String coun,int cas,int dea,int rec,double lat,double lon,
                int tc,int td,int act,int cri){
        country=coun;
        cases=cas;
        deaths=dea;
        recovered=rec;
        latitude=lat;
        longitude=lon;
        todayCases=tc;
        todayDeaths=td;
        active=act;
        critical=cri;
    }

    public int getTodayDeaths() {
        return todayDeaths;
    }

    public int getTodayCases() {
        return todayCases;
    }


    public int getCritical() {
        return critical;
    }


    public int getActive() {
        return active;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getCases() {
        return cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public String getCountry() {
        return country;
    }
}
