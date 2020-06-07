package entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.sql.Time;

@Entity
@Table(name = "Flood", schema = "db_fastwater", catalog = "")
public class Flood implements Serializable,Comparable<Flood> {

    private int idFlood;
    // private Integer postId;
    private Date date;
    private Time time;
    private Double longitudeDay;
    private Integer numberDaysYear;
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
    private double levelWater;
    private int warningFlood;
    private String dataSource;
    private Post postId;


    public Flood() {    }

    public Flood(Date date, Time time, Double longitudeDay, Double snow, Double rain, Double snowRain, Double relativeHumidity,Double humidityDeficit, Double temperatureDay, Double temperatureNight,Double temperatureMidle,Double temperatureMax,Double temperatureMin,Double levelSnow, Double waterReserveInSnow, Double levelFreezingGround, Double temperatureWater, Double heightIceOnWater, double levelWater, int warningFlood, Post postId, Double solaractivity, String dataSource) {
        this.date = date;
        this.numberDaysYear=NumberDaysBetwenDate(new Date(date.getYear(),1,1),date);
        this.time = time;
        this.longitudeDay = longitudeDay;
        this.snow = snow;
        this.rain = rain;
        this.snowRain = snowRain;
        this.relativeHumidity = relativeHumidity;
        this.humidityDeficit = humidityDeficit;
        this.temperatureDay = temperatureDay;
        this.temperatureNight = temperatureNight;
        this.temperatureMidle=temperatureMidle;
        this.temperatureMax=temperatureMax;
        this.temperatureMin=temperatureMin;
        this.solaractivity = solaractivity;
        this.levelSnow = levelSnow;
        this.waterReserveInSnow = waterReserveInSnow;
        this.levelFreezingGround = levelFreezingGround;
        this.temperatureWater = temperatureWater;
        this.heightIceOnWater = heightIceOnWater;
        this.levelWater = levelWater;
        this.warningFlood = warningFlood;
        this.postId = postId;
        this.dataSource=dataSource;
    }
    public Flood(Date date, Time time, Double longitudeDay, Double snow, Double rain, Double snowRain, Double relativeHumidity,Double humidityDeficit, Double temperatureDay, Double temperatureNight,Double temperatureMax,Double temperatureMin,Double levelSnow, Double waterReserveInSnow, Double levelFreezingGround, Double temperatureWater, Double heightIceOnWater, double levelWater, int warningFlood, Post postId, Double solaractivity, String dataSource) {
        this.date = date;
        this.numberDaysYear=NumberDaysBetwenDate(new Date(date.getYear(),1,1),date);
        this.time = time;
        this.longitudeDay = longitudeDay;
        this.snow = snow;
        this.rain = rain;
        this.snowRain = snowRain;
        this.relativeHumidity = relativeHumidity;
        this.humidityDeficit = humidityDeficit;
        this.temperatureDay = temperatureDay;
        this.temperatureNight = temperatureNight;
        this.temperatureMax=temperatureMax;
        this.temperatureMin=temperatureMin;
        this.temperatureMidle=(temperatureMax+temperatureMin)/2;
        this.solaractivity = solaractivity;
        this.levelSnow = levelSnow;
        this.waterReserveInSnow = waterReserveInSnow;
        this.levelFreezingGround = levelFreezingGround;
        this.temperatureWater = temperatureWater;
        this.heightIceOnWater = heightIceOnWater;
        this.levelWater = levelWater;
        this.warningFlood = warningFlood;
        this.postId = postId;
        this.dataSource=dataSource;
    }

    public Flood(Date date, Integer numberDaysYear, Double snowRain, Double relativeHumidity, Double humidityDeficit, Double temperatureDay, Double temperatureNight, Double temperatureMax, Double temperatureMin, Double solaractivity, Double levelSnow, Double waterReserveInSnow, Double levelFreezingGround, Double heightIceOnWater, double levelWater, String dataSource, Post postId) {
        this.date = date;
        this.time = new Time(00,00,00);
        this.numberDaysYear = numberDaysYear;
        this.snowRain = snowRain;
        this.relativeHumidity = relativeHumidity;
        this.humidityDeficit = humidityDeficit;
        this.temperatureDay = temperatureDay;
        this.temperatureNight = temperatureNight;
        this.temperatureMax = temperatureMax;
        this.temperatureMin = temperatureMin;
        this.temperatureMidle=(temperatureMax+temperatureMin)/2;
        this.solaractivity = solaractivity;
        this.levelSnow = levelSnow;
        this.waterReserveInSnow = waterReserveInSnow;
        this.levelFreezingGround = levelFreezingGround;
        this.heightIceOnWater = heightIceOnWater;
        this.levelWater = levelWater;
        this.dataSource = dataSource;
        this.postId = postId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Flood")
    public int getIdFlood() {
        return idFlood;
    }

    public void setIdFlood(int idFlood) {
        this.idFlood = idFlood;
    }

//    @Basic
//    @Column(name = "PostID")
//    public int getPostId() {
//        return postId;
//    }
//
//    public void setPostId(int postId) {
//        this.postId = postId;
//    }

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "Date")
    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    @Column(name = "NumberDaysYear")
    public Integer getNumberDaysYear() {
        return numberDaysYear;
    }

    public void setNumberDaysYear(Integer numberDaysYear) {
        this.numberDaysYear =numberDaysYear;
    }

    @Basic
    @Column(name = "Time")
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Basic
    @Column(name = "LongitudeDay")
    public Double getLongitudeDay() {
        return longitudeDay;
    }

    public void setLongitudeDay(Double longitudeDay) {
        this.longitudeDay = longitudeDay;
    }

    @Basic
    @Column(name = "Snow")
    public Double getSnow() {
        return snow;
    }

    public void setSnow(Double snow) {
        this.snow = snow;
    }

    @Basic
    @Column(name = "Rain")
    public Double getRain() {
        return rain;
    }

    public void setRain(Double rain) {
        this.rain = rain;
    }

    @Basic
    @Column(name = "SnowRain")
    public Double getSnowRain() {
        return snowRain;
    }

    public void setSnowRain(Double snowRain) {
        this.snowRain = snowRain;
    }

    @Basic
    @Column(name = "HumidityDeficit")
    public Double getHumidityDeficit() {
        if(humidityDeficit!=null) return humidityDeficit;
        else return 0.0;
    }

    public void setHumidityDeficit(Double humidityDeficit) {
        this.humidityDeficit = humidityDeficit;
    }

    @Basic
    @Column(name = "RelativeHumidity")
    public Double getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(Double relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }


    @Basic
    @Column(name = "TemperatureDay")
    public Double getTemperatureDay() {
        return temperatureDay;
    }

    public void setTemperatureDay(Double temperatureDay) {
        this.temperatureDay = temperatureDay;
    }

    @Basic
    @Column(name = "TemperatureNight")
    public Double getTemperatureNight() {
        return temperatureNight;
    }

    public void setTemperatureNight(Double temperatureNight) {
        this.temperatureNight = temperatureNight;
    }

    @Basic
    @Column(name = "Temperature")
    public Double getTemperatureMidle() {
        return temperatureMidle;
    }

    public void setTemperatureMidle(Double temperatureMidle) {
        this.temperatureMidle = temperatureMidle;
    }

    @Basic
    @Column(name = "TemperatureMax")
    public Double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(Double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    @Basic
    @Column(name = "TemperatureMin")
    public Double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(Double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    @Basic
    @Column(name = "SolarActivity")
    public Double getSolarActivity() {
        return solaractivity;
    }

    public void setSolarActivity(Double solaractivity) {
        this.solaractivity = solaractivity;
    }

    @Basic
    @Column(name = "LevelSnow")
    public Double getLevelSnow() {
        return levelSnow;
    }

    public void setLevelSnow(Double levelSnow) {
        this.levelSnow = levelSnow;
    }

    @Basic
    @Column(name = "WaterReserveInSnow")
    public Double getWaterReserveInSnow() {
        return waterReserveInSnow;
    }

    public void setWaterReserveInSnow(Double waterReserveInSnow) {
        this.waterReserveInSnow = waterReserveInSnow;
    }

    @Basic
    @Column(name = "LevelFreezingGround")
    public Double getLevelFreezingGround() {
        return levelFreezingGround;
    }

    public void setLevelFreezingGround(Double levelFreezingGround) {
        this.levelFreezingGround = levelFreezingGround;
    }

    @Basic
    @Column(name = "TemperatureWater")
    public Double getTemperatureWater() {
        return temperatureWater;
    }

    public void setTemperatureWater(Double temperatureWater) {
        this.temperatureWater = temperatureWater;
    }

    @Basic
    @Column(name = "HeightIceOnWater")
    public Double getHeightIceOnWater() {
        return heightIceOnWater;
    }

    public void setHeightIceOnWater(Double heightIceOnWater) {
        this.heightIceOnWater = heightIceOnWater;
    }

    @Basic
    @NotNull
    @Column(name = "LevelWater")
    public double getLevelWater() {
        return levelWater;
    }

    public void setLevelWater(double levelWater) {
        this.levelWater = levelWater;
    }

    @Basic
    @Column(name = "DataSource")
    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    @Basic
    @NotNull
    @Column(name = "WarningFlood")
    public int getWarningFlood() {
        return warningFlood;
    }

    public void setWarningFlood(int warningFlood) {
        this.warningFlood = warningFlood;
    }


    @ManyToOne
    @JoinColumn(name = "PostID", referencedColumnName = "Id_Post", nullable = false)
    public Post getPostId() {
        return postId;
    }

    public void setPostId(Post postId) {
        this.postId = postId;
    }

    @Override
    public int compareTo(Flood o) {
        int id=this.getPostId().getIdPost()-o.getPostId().getIdPost();
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

    public static  Integer NumberDaysBetwenDate(Date dateStart,Date datafinish){

        // Количество дней между датами в миллисекундах
        long difference = datafinish.getTime() - dateStart.getTime();
        // Перевод количества дней между датами из миллисекунд в дни
        Integer days =  (int)(difference / (24 * 60 * 60 * 1000)); // миллисекунды / (24ч * 60мин * 60сек * 1000мс)

        return days;
    }

}
