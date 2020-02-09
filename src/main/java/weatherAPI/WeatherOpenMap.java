package weatherAPI;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class WeatherOpenMap {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String zoneID="Asia/Yekaterinburg";

    String latitude;
    String longitude;
    String koords;
    String date;
    LocalDateTime localDateTime;
    LocalDate localDate;
    LocalTime localTime;
    String localDateStr;
    String localTimeStr;
    String clouds; //Cloudiness, %
    String dateTimeStr;
    String sunriseTime;
    String sunsetTime;
    Long lonitudeDay_seconds;
    String temperature;
    String temperatureMin;
    String temperatureMax;
    String humidity;
    String snow1h; //Объем за последний час
    String snow3h;//Объем за последний 3 часа
    String rain1h;//Объем за последний час
    String rain3h;//Объем за последний 3 часа

    public WeatherOpenMap(String latitude, String longitude, String date, String temperature, String temperatureMin, String temperatureMax, String humidity, String clouds) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.koords=latitude+" "+longitude;
        this.date = date;
        this.temperature = temperature;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.humidity = humidity;
        this.clouds = clouds;
        if(!this.date.equals(null)){
            this.localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.valueOf(date)), ZoneId.of(zoneID));
            this.dateTimeStr=localDateTime.format(DTF);
            this.localDate=localDateTime.toLocalDate();
            this.localTime=localDateTime.toLocalTime();
            this.localDateStr=localDate.toString();
            this.localTimeStr=localTime.toString();
        }
    }


    public WeatherOpenMap(String latitude, String longitude, String date, String temperature, String temperatureMin, String temperatureMax, String humidity, String clouds, String typeOsadky, String countOsadky) {
        this (latitude, longitude,date,temperature,temperatureMin,temperatureMax,humidity, clouds);
        if(typeOsadky.equals("snow")) this.snow3h = countOsadky;
        if(typeOsadky.equals("rain")) this.rain3h = countOsadky;
        if(!this.date.equals(null)){
        this.localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.valueOf(date)), ZoneId.of(zoneID));
        this.dateTimeStr=localDateTime.format(DTF);
        this.localDate=localDateTime.toLocalDate();
        this.localTime=localDateTime.toLocalTime();
        this.localDateStr=localDate.toString();
        this.localTimeStr=localTime.toString();}
    }

    public WeatherOpenMap(String latitude, String longitude, String date, String sunriseTime, String sunsetTime, String temperature, String temperatureMin, String temperatureMax, String humidity, String clouds, String x) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.koords=latitude+" "+longitude;
        this.date = date;
        this.sunriseTime = sunriseTime;
        this.sunsetTime=sunsetTime;
        this.temperature = temperature;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.humidity = humidity;
        this.clouds = clouds;
        if(!this.date.equals(null)){
            this.localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.valueOf(date)), ZoneId.of(zoneID));
            this.dateTimeStr=localDateTime.format(DTF);
            this.localDate=localDateTime.toLocalDate();
            this.localTime=localDateTime.toLocalTime();
            this.localDateStr=localDate.toString();
            this.localTimeStr=localTime.toString();}
        if(this.sunriseTime!=null && this.sunsetTime!=null)
            this.lonitudeDay_seconds=(Long.valueOf(sunsetTime)-Long.valueOf(sunriseTime));
    }

    public WeatherOpenMap(String latitude, String longitude, String date, String sunriseTime, String sunsetTime, String temperature, String temperatureMin, String temperatureMax, String humidity, String clouds, String x, String typeOsadky, String countOsadkyh1, String countOsadkyh3) {
        this (latitude, longitude, date, sunriseTime, sunsetTime, temperature, temperatureMin, temperatureMax, humidity, clouds, x);
        if(typeOsadky.equals("snow")){this.snow1h = countOsadkyh1; this.snow3h = countOsadkyh3;}
        if(typeOsadky.equals("rain")) {this.rain1h = countOsadkyh1; this.rain3h = countOsadkyh3;}
    }

    public static DateTimeFormatter getDTF() {
        return DTF;
    }

    public static String getZoneID() {
        return zoneID;
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getClouds() {
        return clouds;
    }

    public String getDateTimeStr() {
        return dateTimeStr;
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

    public String getTemperature() {
        return temperature;
    }

    public String getTemperatureMin() {
        return temperatureMin;
    }

    public String getTemperatureMax() {
        return temperatureMax;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getSnow1h() {
        return snow1h;
    }

    public String getSnow3h() {
        return snow3h;
    }

    public String getRain1h() {
        return rain1h;
    }

    public String getRain3h() {
        return rain3h;
    }

    public String getKoords() {
        return koords;
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

    @Override
    public String toString() {
        return "latitude ="+ latitude+"\n"+
       "longitude ="+ longitude+"\n"+
        "date ="+ date+"\n"+
        "dateTimeStr="+dateTimeStr+"\n"+
        "temperature ="+ temperature+"\n"+
        "temperatureMin ="+ temperatureMin+"\n"+
        "temperatureMax ="+ temperatureMax+"\n"+
        "snow1h ="+ snow1h+"\n"+
        "rain1h ="+ rain1h+"\n"+
        "snow3h ="+ snow3h+"\n"+
        "rain3h ="+ rain3h+"\n"+
        "humidity ="+ humidity+"\n"+
        "clouds ="+ clouds+"\n";
    }
}
