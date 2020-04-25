package view;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class FloodView implements Serializable,Comparable<FloodView> {

    private int idPost;
    private Long idFlood;
    private String geographKoordsPost;
    private String nameLocality;
    private Date date;
    private Time time;
    private Double longitudeDay;
    private Double snow;
    private Double rain;
    private Double snowRain;
    private Double airHumidity;
    private Double temperatureDay;
    private Double temperatureNight;
    private Double temperature;
    private Double temperatureMax;
    private Double temperatureMin;
    private Double solaractivity;
    private Double levelSnow;
    private Double hardnessSnow;
    private Double levelFreezingGround;
    private Double temperatureWater;
    private Double heightIceOnWater;
    private double levelWater;
    private int warningFlood;
    private  String dataSource;
    private double changeLevelWater;
    private double speedChangeLevelWater;
    private double changeLevelSnow;
    private double speedChangeLevelSnow;



    public FloodView() {
    }

    public FloodView(int idPost,Long idFlood, String geographKoordsPost, String nameLocality, Date date, Time time, Double longitudeDay, Double snow, Double rain, Double snowRain, Double airHumidity, Double temperatureDay, Double temperatureNight, Double temperature, Double temperatureMax, Double temperatureMin, Double solaractivity, Double levelSnow, Double hardnessSnow, Double levelFreezingGround, Double temperatureWater, Double heightIceOnWater, double levelWater, int warningFlood,String dataSource) {
        this.idPost=idPost;
        this.idFlood = idFlood;
        this.geographKoordsPost = geographKoordsPost;
        this.nameLocality = nameLocality;
        this.date = date;
        this.time = time;
        this.longitudeDay = longitudeDay;
        this.snow = snow;
        this.rain = rain;
        this.snowRain = snowRain;
        this.airHumidity = airHumidity;
        this.temperatureDay = temperatureDay;
        this.temperatureNight = temperatureNight;
        this.temperature = temperature;
        this.temperatureMax = temperatureMax;
        this.temperatureMin = temperatureMin;
        this.solaractivity = solaractivity;
        this.levelSnow = levelSnow;
        this.hardnessSnow = hardnessSnow;
        this.levelFreezingGround = levelFreezingGround;
        this.temperatureWater = temperatureWater;
        this.heightIceOnWater = heightIceOnWater;
        this.levelWater = levelWater;
        this.warningFlood = warningFlood;
        this.dataSource=dataSource;
    }

    public Long getIdFlood() {
        return idFlood;
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

    public Double getAirHumidity() {
        return airHumidity;
    }

    public Double getTemperatureDay() {
        return temperatureDay;
    }

    public Double getTemperatureNight() {
        return temperatureNight;
    }

    public Double getTemperature() {
        return temperature;
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

    public Double getHardnessSnow() {
        return hardnessSnow;
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

    public double getLevelWater() {
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

    public double getChangeLevelWater() {
        return changeLevelWater;
    }

    public double getSpeedChangeLevelWater() {
        return speedChangeLevelWater;
    }

    public double getChangeLevelSnow() {
        return changeLevelSnow;
    }

    public double getSpeedChangeLevelSnow() {
        return speedChangeLevelSnow;
    }

    public void setChangeLevelWater(FloodView obj) {
        this.changeLevelWater = this.levelWater-obj.levelWater;
    }

    public void setSpeedChangeLevelWater(FloodView obj) {

        if (this.getTime()!=null & obj.getTime()!=null) {
            int changeHour = (int) ((this.date.getTime() - obj.getDate().getTime()) / (60 * 60 * 100)) +
                    (int) ((this.time.getTime() - obj.getTime().getTime()) / (60 * 60 * 100));
            int changeHour2 = changeHour != 0 ? changeHour : 1;

            this.speedChangeLevelWater = (this.levelWater - obj.levelWater) / changeHour2;
        }
    }

    public void setChangeLevelSnow(FloodView obj) {
        this.changeLevelSnow = this.levelSnow-obj.levelSnow;
    }

    public void setSpeedChangeLevelSnow(FloodView obj) {
        if (this.getTime()!=null & obj.getTime()!=null) {
            int changeHour = (int) ((this.date.getTime() - obj.getDate().getTime()) / (60 * 60 * 100)) +
                    (int) ((this.time.getTime() - obj.getTime().getTime()) / (60 * 60 * 100));
            int changeHour2 = changeHour != 0 ? changeHour : 1;
            this.speedChangeLevelSnow = (this.levelSnow - obj.levelSnow) / changeHour2;
        }
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
