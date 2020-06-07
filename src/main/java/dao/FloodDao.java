package dao;

import entity.Flood;
import entity.Post;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface FloodDao {
    List<Flood> getAll();
    List<Flood> searh(Date date);
    List<Flood> searh(Post post);
    Flood searh(int idFlood);
    List<Flood> searh(String locality);
    List<Flood> searh(String namePost,Date dateStart,Date dateFinish);
    void add (Date date, Time time, Double longitudeDay, Double snow, Double rain, Double snowRain, Double airHumidity, Double temperatureDay, Double temperatureNight, Double levelSnow, Double hardnessSnow, Double levelFreezingGround, Double temperatureWater, Double heightIceOnWater, double levelWater, int warningFlood, Post postByPostId, String solaractivity);
    void addOrApdate (Flood flood);
    void add (Flood flood);
    void dell (Flood flood);
    void dell (int idflood);
    void update (Date date, Time time, Double longitudeDay, Double snow, Double rain, Double snowRain, Double airHumidity, Double temperatureDay, Double temperatureNight, Double levelSnow, Double hardnessSnow, Double levelFreezingGround, Double temperatureWater, Double heightIceOnWater, double levelWater, int warningFlood, Post postByPostId, String solaractivity);
}
