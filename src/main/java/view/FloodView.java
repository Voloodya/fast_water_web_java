package view;

import java.io.Serializable;
import java.math.RoundingMode;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.Date;

public class FloodView implements Serializable,Comparable<FloodView> {

    private int idPost;
    private String namePost;
    private Integer numberPostForNrlNtwrk;
    private int idFlood;
    private String geographKoordsPost;
    private String nameLocality;
    private Date date;
    private Integer numberDaysYear;
    private Time time;
    private Double longitudeDay;
    private Double snow;
    private Double rain;
    private Double snowRain;
    private Double relativeHumidity;
    private Double humidityDeficit;
    private Double temperatureDay;
    private Double temperatureNight;
    private Double temperatureMidle;
    private Double temperatureMax;
    private Double temperatureMin;
    private Double solaractivity;
    private Double levelSnow;
    private Double waterReserveInSnow;
    private Double levelFreezingGround;
    private Double temperatureWater;
    private Double heightIceOnWater;
    private Double levelWater;
    private Integer warningFlood;
    private  String dataSource;
    private Double changeLevelWater;
    private Double speedChangeLevelWater;
    private Double changeLevelSnow;
    private Double speedChangeLevelSnow;
    private Double downfallBefore3days;
    private Double changeSnowBefore10days;
    private Double changeWaterBefore3dayMiddle;
    private Double temperatureMiddleBefore3days;
    private Double forecastChangeLevelWater;
    private DecimalFormat df;


    public FloodView() {
        df = new DecimalFormat("####.####");
        df.setRoundingMode(RoundingMode.HALF_UP);
    }

    public FloodView(int idPost,String namePost, int idFlood, String geographKoordsPost, String nameLocality, Date date, Integer numberDaysYear, Double humidityDeficit, Time time, Double longitudeDay, Double snow, Double rain, Double snowRain, Double relativeHumidity, Double temperatureDay, Double temperatureNight, Double temperatureMidle, Double temperatureMax, Double temperatureMin, Double solaractivity, Double levelSnow, Double waterReserveInSnow, Double levelFreezingGround, Double temperatureWater, Double heightIceOnWater, double levelWater, int warningFlood,String dataSource) {
        df = new DecimalFormat("#####.####");
        df.setRoundingMode(RoundingMode.HALF_UP);
        this.idPost=idPost;
        this.namePost=namePost;
        this.idFlood = idFlood;
        this.geographKoordsPost = geographKoordsPost;
        this.nameLocality = nameLocality;
        this.date = date;
        this.numberDaysYear=numberDaysYear;
        this.time = time;
        this.longitudeDay = longitudeDay;
        this.snow = snow;
        this.rain = rain;
        this.snowRain = snowRain;
        this.relativeHumidity = relativeHumidity;
        this.humidityDeficit=humidityDeficit!=null?humidityDeficit:0;
        this.temperatureDay = temperatureDay;
        this.temperatureNight = temperatureNight;
        this.temperatureMidle = (double)Math.round(temperatureMidle * 10000d) / 10000d;
        this.temperatureMax = temperatureMax;
        this.temperatureMin = temperatureMin;
        this.solaractivity =(double)Math.round(solaractivity * 10000d) / 10000d;
        this.levelSnow = (double)Math.round(levelSnow * 10000d) / 10000d;
        this.waterReserveInSnow =(double)Math.round(waterReserveInSnow * 10000d) / 10000d;;
        this.levelFreezingGround =(double)Math.round(levelFreezingGround * 10000d) / 10000d;;
        this.temperatureWater = temperatureWater;
        this.heightIceOnWater =(double)Math.round(heightIceOnWater * 10000d) / 10000d;;
        this.levelWater =(double)Math.round(levelWater * 10000d) / 10000d;;
        this.warningFlood = warningFlood;
        this.dataSource=dataSource;

        if(namePost.equals("Оренбург001")) this.numberPostForNrlNtwrk=1;
        else if (namePost.equals("Орск001")) this.numberPostForNrlNtwrk=2;
        else this.numberPostForNrlNtwrk=3;
    }

    public int getIdFlood() {
        return idFlood;
    }

    public String getNamePost() {
        return namePost;
    }

    public int getNumberPostForNrlNtwrk() {
        if(this.numberPostForNrlNtwrk!=null) return numberPostForNrlNtwrk;
        else return 0;
    }

    public String getGeographKoordsPost() {
        return geographKoordsPost;
    }

    public String getNameLocality() {
        return nameLocality;
    }

    public Date getDate() {
        return date;
    }

    public Integer getNumberDaysYear() {
        return numberDaysYear;
    }

    public Time getTime() {
        return this.time;
    }

    public Double getLongitudeDay() {
        return longitudeDay;
    }

    public Double getSnow() {
        return snow;
    }

    public Double getRain() {
        return rain;
    }

    public Double getSnowRain() {
        return snowRain;
    }

    public Double getRelativeHumidity() {
        return relativeHumidity;
    }

    public Double getHumidityDeficit() {
        return humidityDeficit;
    }

    public Double getTemperatureDay() {
        return temperatureDay;
    }

    public Double getTemperatureNight() {
        return temperatureNight;
    }

    public Double getTemperatureMidle() {
        return temperatureMidle;
    }

    public Double getTemperatureMax() {
        return temperatureMax;
    }

    public Double getTemperatureMin() {
        return temperatureMin;
    }

    public Double getSolaractivity() {
        return solaractivity;
    }

    public Double getLevelSnow() {
        return levelSnow;
    }

    public Double getWaterReserveInSnow() {
        return waterReserveInSnow;
    }

    public Double getLevelFreezingGround() {
        return levelFreezingGround;
    }

    public Double getTemperatureWater() {
        return temperatureWater;
    }

    public Double getHeightIceOnWater() {
        return heightIceOnWater;
    }

    public Double getLevelWater() {
        return levelWater;
    }

    public int getWarningFlood() {
        return warningFlood;
    }

    public String getDataSource() {
        return dataSource;
    }

    public int getIdPost() {
        return idPost;
    }

    public Double getChangeLevelWater() {
        if(this.changeLevelWater!=null) return changeLevelWater;
        else return 0.0;
    }

    public Double getSpeedChangeLevelWater() {
        return speedChangeLevelWater;
    }

    public Double getChangeLevelSnow() {
        if(this.changeLevelSnow!=null) return changeLevelSnow;
        else return 0.0;
    }

    public Double getSpeedChangeLevelSnow() {
        return speedChangeLevelSnow;
    }

    public Double getDownfallBefore3days() {
        if(this.downfallBefore3days!=null) return downfallBefore3days;
        else return 0.0;
    }

    public Double getChangeSnowBefore10days() {
        if(this.changeSnowBefore10days!=null) return changeSnowBefore10days;
        else return 0.0;
    }

    public Double getChangeWaterBefore3dayMiddle() {
        if(this.changeWaterBefore3dayMiddle!=null) return changeWaterBefore3dayMiddle;
        else return 0.0;
    }

    public Double getTemperatureMiddleBefore3days() {
        if(this.temperatureMiddleBefore3days!=null) return temperatureMiddleBefore3days;
        else return 0.0;
    }

    public Double getForecastChangeLevelWater() {
        return forecastChangeLevelWater;
    }

    public void setLevelWater(Double levelWater) {
        this.levelWater = (double)Math.round(levelWater * 10000d)/10000d;
    }

    public void setChangeLevelWater(FloodView obj) {
        this.changeLevelWater =(double)Math.round(( this.levelWater-obj.levelWater) * 10000d) / 10000d;
    }
    public void setChangeLevelWater(Double fofecast) {
        this.changeLevelWater =(double)Math.round(fofecast * 10000d) / 10000d;
    }

    public void setSpeedChangeLevelWater(FloodView obj) {
        if (this.getTime()!=null & obj.getTime()!=null) {
            int changeHour = (int) ((this.date.getTime() - obj.getDate().getTime()) / (60 * 60 * 100)) +
                    (int) ((this.time.getTime() - obj.getTime().getTime()) / (60 * 60 * 100));
            int changeHour2 = changeHour != 0 ? changeHour : 1;

            this.speedChangeLevelWater =(double)Math.round(( (this.levelWater - obj.levelWater) / changeHour2) * 10000d) / 10000d;
        }
    }

    public void setChangeLevelSnow(FloodView obj) {
        this.changeLevelSnow = (double)Math.round(((this.levelSnow-obj.levelSnow) * 10000d) / 10000d);
    }

    public void setSpeedChangeLevelSnow(FloodView obj) {
        if (this.getTime()!=null & obj.getTime()!=null) {
            int changeHour = (int) ((this.date.getTime() - obj.getDate().getTime()) / (60 * 60 * 100)) +
                    (int) ((this.time.getTime() - obj.getTime().getTime()) / (60 * 60 * 100));
            int changeHour2 = changeHour != 0 ? changeHour : 1;
            this.speedChangeLevelSnow =(double)Math.round((((this.levelSnow - obj.levelSnow) / changeHour2) * 10000d) / 10000d);
        }
    }

    public void setDownfallBefore3days(Double downfallBefore3days) {
        this.downfallBefore3days = downfallBefore3days;
    }

    public void setChangeSnowBefore10days(Double changeSnowBefore10days) {
        this.changeSnowBefore10days = changeSnowBefore10days;
    }

    public void setChangeWaterBefore3dayMiddle(Double changeWaterBefore3dayMiddle) {
        this.changeWaterBefore3dayMiddle = changeWaterBefore3dayMiddle;
    }

    public void setTemperatureMiddleBefore3days(Double temperatureMiddleBefore3days) {
        this.temperatureMiddleBefore3days = temperatureMiddleBefore3days;
    }

    public void setForecastChangeLevelWater(Double forecastChangeLevelWater) {
        this.forecastChangeLevelWater = forecastChangeLevelWater;
    }

    @Override
    public int compareTo(FloodView o) {
        int id=this.idPost-o.idPost;
        if(id!=0){
            return id;
        }
        else{
            int date=this.date.compareTo(o.date);
            if(date!=0) return date;
            else{
                return this.time.compareTo(o.time);
            }
        }
    }
}
