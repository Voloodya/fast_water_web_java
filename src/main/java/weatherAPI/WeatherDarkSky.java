package weatherAPI;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class WeatherDarkSky {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String zoneID="Asia/Yekaterinburg";
    String latitude;
    String longitude;
    String koords;
    String date;
    LocalDateTime localDateTime;
    LocalDate localDate;
    int year;
    int month;
    int day;
    LocalTime localTime;
    int hour;
    int minute;
    int second;
    String localDateStr;
    String localTimeStr;
    String clouds; //Облачность
    String cloudCover; //Процент неба затянутого облаками
    String sunriseTime;
    String sunsetTime;
    Long lonitudeDay_seconds;
    String precipIntensity; //Интенсивность осадков (в дюймах жидкой воды в час) в данный момент
    String precipIntensityMax;//Максимальное значение интенсивности осадков в течении дня
    String precipIntensityMaxTime;
    String snow;
    String rain;
    String sleet; //Мокрый снег (снег с дождём)
    String temperature;//Температура в данный момент времени
    String temperatureMin;//Минимальная температура
    String temperatureMinTime;
    String temperatureMax; //Максимальная температура
    String temperatureMaxTime;
    String temperatureHigh; //Дневная температура (самая высокая)
    String temperatureLow; //Ночная низкая температура
    String dewPoint;
    String humidity;

    public WeatherDarkSky() {
    }

    public WeatherDarkSky(String latitude, String longitude, String date, String clouds, String cloudCover, String sunriseTime, String sunsetTime, String precipIntensity, String temperatureMin, String temperatureMinTime, String temperatureMax, String temperatureMaxTime, String temperatureHigh, String temperatureLow, String dewPoint, String humidity) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.koords=latitude+" "+longitude;
        this.date = date;
        this.clouds = clouds;
        this.cloudCover = cloudCover;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
        this.precipIntensity = precipIntensity;
        this.temperatureMin = temperatureMin;
        this.temperatureMinTime = temperatureMinTime;
        this.temperatureMax = temperatureMax;
        this.temperatureMaxTime = temperatureMaxTime;
        this.temperatureHigh = temperatureHigh;
        this.temperatureLow = temperatureLow;
        this.dewPoint = dewPoint;
        this.humidity = humidity;
        this.localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.valueOf(date)), ZoneId.of(zoneID));
        this.localDate=localDateTime.toLocalDate();
        this.localTime=localDateTime.toLocalTime();
        this.localDateStr=localDate.toString();
        this.localTimeStr=localTime.toString();
        this.year=localDate.getYear();
        this.month=localDate.getMonthValue();
        this.day=localDate.getDayOfMonth();
        this.hour=localTime.getHour();
        this.minute=localTime.getMinute();
        this.second=localTime.getSecond();
        if(this.sunriseTime!=null && this.sunsetTime!=null)
            this.lonitudeDay_seconds=(Long.valueOf(sunsetTime)-Long.valueOf(sunriseTime));
        this.snow="0";
        this.rain="0";
        this.sleet="0";
    }

    public WeatherDarkSky(String latitude, String longitude, String date, String clouds, String cloudCover, String sunriseTime, String sunsetTime, String precipIntensity, String temperatureMin, String temperatureMinTime,
                          String temperatureMax, String temperatureMaxTime, String temperatureHigh, String temperatureLow, String dewPoint, String humidity, String osadky,String precipIntensityMax) {
        this(latitude,longitude,date,clouds,cloudCover,sunriseTime,sunsetTime,precipIntensity,temperatureMin,temperatureMinTime,temperatureMax, temperatureMaxTime, temperatureHigh, temperatureLow, dewPoint, humidity);
        if(osadky.equals("snow")) {this.snow=!precipIntensityMax.equals("") ? precipIntensityMax:precipIntensity;}
        if(osadky.equals("rain")) {this.rain=!precipIntensityMax.equals("") ? precipIntensityMax:precipIntensity;}
        if(osadky.equals("sleet")) {this.sleet=!precipIntensityMax.equals("") ? precipIntensityMax:precipIntensity;}
    }

    public WeatherDarkSky(String latitude, String longitude, String date, String clouds, String cloudCover, String precipIntensity, String temperature, String dewPoint, String humidity, String temperatureHigh, String temperatureLow) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.koords=latitude+" "+longitude;
        this.date = date;
        this.clouds = clouds;
        this.cloudCover = cloudCover;
        this.precipIntensity = precipIntensity;
        this.temperature = temperature;
        this.dewPoint = dewPoint;
        this.humidity = humidity;
        this.temperatureHigh = temperatureHigh;
        this.temperatureLow = temperatureLow;
        this.localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.valueOf(date)), ZoneId.of(zoneID));
        this.localDate=localDateTime.toLocalDate();
        this.localTime=localDateTime.toLocalTime();
        this.localDateStr=localDate.toString();
        this.localTimeStr=localTime.toString();
        this.year=localDate.getYear();
        this.month=localDate.getMonthValue();
        this.day=localDate.getDayOfMonth();
        this.hour=localTime.getHour();
        this.minute=localTime.getMinute();
        this.second=localTime.getSecond();
        this.snow="0";
        this.rain="0";
        this.sleet="0";
    }

    public WeatherDarkSky(String latitude, String longitude, String date, String clouds, String cloudCover, String precipIntensity, String temperature, String dewPoint, String humidity, String osadky, String temperatureHigh, String temperatureLow) {
        this (latitude,longitude,date,clouds,cloudCover,precipIntensity,temperature,dewPoint, humidity,temperatureHigh, temperatureLow);
        if(osadky.equals("snow")) this.snow=precipIntensity;
        if(osadky.equals("rain")) this.rain=precipIntensity;
        if(osadky.equals("sleet")) this.sleet=precipIntensity;
    }


    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getDate() {
        return date;
    }

    public String getClouds() {
        return clouds;
    }

    public String getCloudCover() {
        return cloudCover;
    }

    public String getSunriseTime() {
        return sunriseTime;
    }

    public String getSunsetTime() {
        return sunsetTime;
    }

    public Long getLonitudeDay_seconds() {
        return lonitudeDay_seconds;
    }

    public String getPrecipIntensity() {
        return precipIntensity;
    }

    public String getSnow() {
        return snow;
    }

    public String getRain() {
        return rain;
    }

    public String getSleet() {
        return sleet;
    }

    public String getTemperatureMin() {
        return temperatureMin;
    }

    public String getTemperatureMinTime() {
        return temperatureMinTime;
    }

    public String getTemperatureMax() {
        return temperatureMax;
    }

    public String getTemperatureMaxTime() {
        return temperatureMaxTime;
    }

    public String getTemperatureHigh() {
        return temperatureHigh;
    }

    public String getTemperatureLow() {
        return temperatureLow;
    }

    public String getDewPoint() {
        return dewPoint;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getKoords() {
        return koords;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getPrecipIntensityMax() {
        return precipIntensityMax;
    }

    public String getPrecipIntensityMaxTime() {
        return precipIntensityMaxTime;
    }

    public String getTemperature() {
        return temperature;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public String getLocalDateStr() {
        return localDateStr;
    }

    public String getLocalTimeStr() {
        return localTimeStr;
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

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }


    @Override
    public String toString() {
        return "latitude = "+ latitude + "\n"+
        "longitude = "+ longitude+ "\n"+
        "date = "+ date+"\n"+
        "clouds = "+ clouds+"\n"+
        "cloudCover = "+ cloudCover+"\n"+
        "sunriseTime = "+ sunriseTime+"\n"+
        "sunsetTime = "+ sunsetTime+"\n"+
        "lonitudeDay_seconds= "+lonitudeDay_seconds+"\n"+
        "precipIntensity = "+ precipIntensity+"\n"+
        "snow = "+ snow+"\n"+
        "rain = "+ rain+"\n"+
        "sleet = "+ sleet+"\n"+
        "temperatureMin = "+ temperatureMin+"\n"+
        "temperatureMinTime = "+ temperatureMinTime+"\n"+
        "temperatureMax = "+ temperatureMax+"\n"+
        "temperatureMaxTime = "+ temperatureMaxTime+"\n"+
        "temperatureHigh = "+ temperatureHigh+"\n"+
        "temperatureLow = "+ temperatureLow+"\n"+
        "dewPoint = "+ dewPoint+"\n"+
        "humidity = "+ humidity+"\n";
    }
}
