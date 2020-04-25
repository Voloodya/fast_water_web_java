package service;

import dao.PostDao;
import entity.Flood;
import entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import view.Hydrology;
import weatherAPI.WeatherDarkSky;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class AggregationData {
    @Qualifier("PostDao")
    @Autowired
    private PostDao postDao;

    public List<Flood> aggregation(List<Hydrology> hydrologyList, List<WeatherDarkSky> weatherDarkSkyList,String namePost)
    {
        List<Flood> floodList=new ArrayList<>();
        List<Hydrology> hydrologyNewList=null;
        Hydrology hydrology=null;
        Post post=postDao.searсh(namePost);

        for (WeatherDarkSky w : weatherDarkSkyList) {
            hydrologyNewList = hydrologyList.stream().filter(x -> x.getNamePost().equalsIgnoreCase(namePost)).
                    filter(x -> (x.getLocalDate().compareTo(w.getLocalDate()) == 0)).
                    filter(x -> (x.getLocalTime().compareTo(w.getLocalTime()) == 0)).collect(Collectors.toList());
            if(!hydrologyNewList.isEmpty()) hydrology=hydrologyNewList.get(0);
            if(hydrology!=null){
                int warning=post.getBasinlocalityId().getLimitLevel()<=hydrology.getLewelWater()?1:0;
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date data=null;
                try {
                    data=format.parse(w.getYear()+"-"+w.getMonth()+"-"+w.getDay());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(w.getTemperature()!=null)
                    floodList.add(new Flood(data,
                    new Time(w.getHour(),w.getMinute(),w.getSecond()),
                    Double.valueOf(w.getLonitudeDay_seconds()),Double.valueOf(w.getSnow()),
                    Double.valueOf(w.getRain()),Double.valueOf(w.getSleet()),Double.valueOf(w.getHumidity()),
                    Double.valueOf(w.getTemperatureHigh()),Double.valueOf(w.getTemperatureLow()),
                    Double.valueOf(w.getTemperature()),Double.valueOf(w.getTemperatureMax()),Double.valueOf(w.getTemperatureMin()),
                    hydrology.getLevelSnow(),
                    hydrology.getReserveWater(),hydrology.getLevelFreezingGround(),hydrology.getTemperatureWater(),
                    hydrology.getHeightIceOnWater(),hydrology.getLewelWater(),warning,post,Double.valueOf(w.getCloudCover()),"DarkSky"));
                else floodList.add(new Flood(data,
                        new Time(w.getHour(),w.getMinute(),w.getSecond()),
                        Double.valueOf(w.getLonitudeDay_seconds()),Double.valueOf(w.getSnow()),
                        Double.valueOf(w.getRain()),Double.valueOf(w.getSleet()),Double.valueOf(w.getHumidity()),
                        Double.valueOf(w.getTemperatureHigh()),Double.valueOf(w.getTemperatureLow()),
                        Double.valueOf(w.getTemperatureMax()),Double.valueOf(w.getTemperatureMin()),
                        hydrology.getLevelSnow(),hydrology.getReserveWater(),
                        hydrology.getReserveWater(),hydrology.getLevelFreezingGround(),hydrology.getTemperatureWater(),
                        hydrology.getHeightIceOnWater(),hydrology.getLewelWater(),warning,post,Double.valueOf(w.getCloudCover()),"DarkSky"));
            }
        }
        return floodList;
    }
}
