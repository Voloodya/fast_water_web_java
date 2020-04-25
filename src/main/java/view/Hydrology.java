package view;

import java.time.LocalDate;
import java.time.LocalTime;

public class Hydrology {

    private String namePost;
    private LocalDate localDate;
    private int year;
    private int month;
    private int day;
    private int countDay;
    private double downfall;
    private LocalTime localTime;
    private double levelSnow;
    private double changeSnow;
    private double reserveWater;
    private double levelFreezingGround;
    private double temperatureWater;
    private double temperatureMin;
    private double temperatureMax;
    private double relativeHumidityAir;
    private double deficitHumidityAir;
    private double sunShine;
    private double heightIceOnWater;
    private double lewelWater;
    private double changeWater;

    public Hydrology(String namePost, LocalDate localDate, LocalTime localTime, double levelSnow, double reserveWater, double levelFreezingGround, double temperatureWater, double heightIceOnWater, double lewelWater) {
        this.namePost = namePost;
        this.localDate = localDate;
        this.localTime = localTime;
        this.levelSnow = levelSnow;
        this.reserveWater = reserveWater;
        this.levelFreezingGround = levelFreezingGround;
        this.temperatureWater = temperatureWater;
        this.heightIceOnWater = heightIceOnWater;
        this.lewelWater = lewelWater;
        this.year=localDate.getYear();
        this.month=localDate.getMonthValue();
        this.day=localDate.getDayOfMonth();
    }

    public Hydrology(String namePost, LocalDate localDate, int countDay, double downfall, double levelSnow, double changeSnow,
                     double reserveWater, double levelFreezingGround, double temperatureMin,
                     double temperatureMax, double relativeHumidityAir, double deficitHumidityAir, double sunShine,
                     double heightIceOnWater, double lewelWater, double changeWater) {
        this.namePost = namePost;
        this.localDate = localDate;
        this.countDay = countDay;
        this.downfall = downfall;
        this.levelSnow = levelSnow;
        this.changeSnow = changeSnow;
        this.reserveWater = reserveWater;
        this.levelFreezingGround = levelFreezingGround;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.relativeHumidityAir = relativeHumidityAir;
        this.deficitHumidityAir = deficitHumidityAir;
        this.sunShine = sunShine;
        this.heightIceOnWater = heightIceOnWater;
        this.lewelWater = lewelWater;
        this.changeWater = changeWater;
    }

    public String getNamePost() {
        return namePost;
    }

    public LocalDate getLocalDate() {
        return localDate;
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

    public int getCountDay() {
        return countDay;
    }

    public double getDownfall() {
        return downfall;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public double getLevelSnow() {
        return levelSnow;
    }

    public double getChangeSnow() {
        return changeSnow;
    }

    public double getReserveWater() {
        return reserveWater;
    }

    public double getLevelFreezingGround() {
        return levelFreezingGround;
    }

    public double getTemperatureWater() {
        return temperatureWater;
    }

    public double getTemperatureMin() {
        return temperatureMin;
    }

    public double getTemperatureMax() {
        return temperatureMax;
    }

    public double getRelativeHumidityAir() {
        return relativeHumidityAir;
    }

    public double getDeficitHumidityAir() {
        return deficitHumidityAir;
    }

    public double getSunShine() {
        return sunShine;
    }

    public double getHeightIceOnWater() {
        return heightIceOnWater;
    }

    public double getLewelWater() {
        return lewelWater;
    }

    public double getChangeWater() {
        return changeWater;
    }

    public void setNamePost(String namePost) {
        this.namePost = namePost;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setCountDay(int countDay) {
        this.countDay = countDay;
    }

    public void setDownfall(double downfall) {
        this.downfall = downfall;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public void setLevelSnow(double levelSnow) {
        this.levelSnow = levelSnow;
    }

    public void setChangeSnow(double changeSnow) {
        this.changeSnow = changeSnow;
    }

    public void setReserveWater(double reserveWater) {
        this.reserveWater = reserveWater;
    }

    public void setLevelFreezingGround(double levelFreezingGround) {
        this.levelFreezingGround = levelFreezingGround;
    }

    public void setTemperatureWater(double temperatureWater) {
        this.temperatureWater = temperatureWater;
    }

    public void setTemperatureMin(double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public void setTemperatureMax(double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public void setRelativeHumidityAir(double relativeHumidityAir) {
        this.relativeHumidityAir = relativeHumidityAir;
    }

    public void setDeficitHumidityAir(double deficitHumidityAir) {
        this.deficitHumidityAir = deficitHumidityAir;
    }

    public void setSunShine(double sunShine) {
        this.sunShine = sunShine;
    }

    public void setHeightIceOnWater(double heightIceOnWater) {
        this.heightIceOnWater = heightIceOnWater;
    }

    public void setLewelWater(double lewelWater) {
        this.lewelWater = lewelWater;
    }

    public void setChangeWater(double changeWater) {
        this.changeWater = changeWater;
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
                "reserveWater: "+this.reserveWater +'\n'+
                "levelFreezingGround: "+this.levelFreezingGround +'\n'+
                "temperatureWater: "+this.temperatureWater+'\n'+
                "heightIceOnWater: "+this.heightIceOnWater+'\n'+
                "lewelWater: "+this.lewelWater;
    }
}
