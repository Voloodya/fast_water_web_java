package view;

import java.time.LocalDate;
import java.time.LocalTime;

public class Hydrology {

    private String namePost;
    private LocalDate localDate;
    private int year;
    private int month;
    private int day;
    private LocalTime localTime;
    private double levelSnow;
    private double hardnessSnow;
    private double levelFreezingGround;
    private double temperatureWater;
    private double heightIceOnWater;
    private double lewelWater;

    public Hydrology(String namePost, LocalDate localDate, LocalTime localTime, double levelSnow, double hardnessSnow, double levelFreezingGround, double temperatureWater, double heightIceOnWater, double lewelWater) {
        this.namePost = namePost;
        this.localDate = localDate;
        this.localTime = localTime;
        this.levelSnow = levelSnow;
        this.hardnessSnow = hardnessSnow;
        this.levelFreezingGround = levelFreezingGround;
        this.temperatureWater = temperatureWater;
        this.heightIceOnWater = heightIceOnWater;
        this.lewelWater = lewelWater;
        this.year=localDate.getYear();
        this.month=localDate.getMonthValue();
        this.day=localDate.getDayOfMonth();
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public double getLevelSnow() {
        return levelSnow;
    }

    public double getHardnessSnow() {
        return hardnessSnow;
    }

    public double getLevelFreezingGround() {
        return levelFreezingGround;
    }

    public double getTemperatureWater() {
        return temperatureWater;
    }

    public double getHeightIceOnWater() {
        return heightIceOnWater;
    }

    public double getLewelWater() {
        return lewelWater;
    }

    public String getNamePost() {
        return namePost;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    @Override
    public String toString() {
        return "namePost: "+this.namePost+'\n'+
        "date: "+this.localDate+'\n'+
                "year: "+this.year+'\n'+
        "month: "+this.month+'\n'+
        "day: "+this.day+'\n'+
        "time: "+this.localTime +'\n'+
                "levelSnow: "+this.levelSnow +'\n'+
                "hardnessSnow: "+this.hardnessSnow +'\n'+
                "levelFreezingGround: "+this.levelFreezingGround +'\n'+
                "temperatureWater: "+this.temperatureWater+'\n'+
                "heightIceOnWater: "+this.heightIceOnWater+'\n'+
                "lewelWater: "+this.lewelWater;
    }
}
