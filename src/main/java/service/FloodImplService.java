package service;

import dao.FloodDao;
import entity.Flood;
import entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import view.FloodView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Repository("FloodService")
public class FloodImplService implements FloodService {

    @Qualifier("FloodDao")
    @Autowired
    private FloodDao floodDao;

    @Override
    public List<FloodView> getAllFloodViev() {
        List<Flood> listFlood=floodDao.getAll();
        List<FloodView> listFloodView=new ArrayList<FloodView>();

        for(Flood x : listFlood){
            listFloodView.add(new FloodView(x.getPostId().getIdPost(),x.getPostId().getNamePost(),x.getIdFlood(),x.getPostId().getGeographkoordsId().getKoordinate(),
                    x.getPostId().getLocalityId().getNameLocality(),x.getDate(),x.getNumberDaysYear(),Double.valueOf(x.getHumidityDeficit()), x.getTime(),x.getLongitudeDay(),x.getSnow(),x.getRain(),x.getSnowRain(),
                    x.getRelativeHumidity(),x.getTemperatureDay(),x.getTemperatureNight(),x.getTemperatureMidle(),
                    x.getTemperatureMax(),x.getTemperatureMin(),
                    x.getSolarActivity(),x.getLevelSnow(),x.getWaterReserveInSnow(),x.getLevelFreezingGround(),
                    x.getTemperatureWater(),x.getHeightIceOnWater(),x.getLevelWater(),x.getWarningFlood(),x.getDataSource()));
        }
        return listFloodView;
    }

    @Override
    public List<FloodView> searhFloodViev(Date date) {
        return null;
    }

    @Override
    public List<FloodView> searhFloodViev(Post post) {
        return null;
    }

    @Override
    public List<FloodView> searhFloodViev(String loclity) {
        List<Flood> listFlood=floodDao.searh(loclity);
        List<FloodView> listFloodView=new ArrayList<FloodView>();

        for(Flood x : listFlood){
            listFloodView.add(new FloodView(x.getPostId().getIdPost(),x.getPostId().getNamePost(),x.getIdFlood(),x.getPostId().getGeographkoordsId().getKoordinate(),
                    x.getPostId().getLocalityId().getNameLocality(),x.getDate(),x.getNumberDaysYear(),Double.valueOf(x.getHumidityDeficit()), x.getTime(),x.getLongitudeDay(),x.getSnow(),x.getRain(),x.getSnowRain(),
                    x.getRelativeHumidity(),x.getTemperatureDay(),x.getTemperatureNight(),x.getTemperatureMidle(),
                    x.getTemperatureMax(),x.getTemperatureMin(),
                    x.getSolarActivity(),x.getLevelSnow(),x.getWaterReserveInSnow(),x.getLevelFreezingGround(),
                    x.getTemperatureWater(),x.getHeightIceOnWater(),x.getLevelWater(),x.getWarningFlood(),x.getDataSource()));
        }
        return listFloodView;
    }

    @Override
    public List<FloodView> searhFloodViev(String namePost, Date dateStart, Date dateFinish) {
        List<Flood> listFlood=new ArrayList<>();

        listFlood=floodDao.searh(namePost,dateStart,dateFinish);

        List<FloodView> listFloodView=new ArrayList<FloodView>();

        for(Flood x : listFlood){
            listFloodView.add(new FloodView(x.getPostId().getIdPost(),x.getPostId().getNamePost(),x.getIdFlood(),x.getPostId().getGeographkoordsId().getKoordinate(),
                    x.getPostId().getLocalityId().getNameLocality(),x.getDate(),x.getNumberDaysYear(),Double.valueOf(x.getHumidityDeficit()), x.getTime(),x.getLongitudeDay(),x.getSnow(),x.getRain(),x.getSnowRain(),
                    x.getRelativeHumidity(),x.getTemperatureDay(),x.getTemperatureNight(),x.getTemperatureMidle(),
                    x.getTemperatureMax(),x.getTemperatureMin(),
                    x.getSolarActivity(),x.getLevelSnow(),x.getWaterReserveInSnow(),x.getLevelFreezingGround(),
                    x.getTemperatureWater(),x.getHeightIceOnWater(),x.getLevelWater(),x.getWarningFlood(),x.getDataSource()));
        }
        return listFloodView;
    }

    @Override
    public void dell(Post namePost, Date dateStart, Date dateFinish) {

    }
}
