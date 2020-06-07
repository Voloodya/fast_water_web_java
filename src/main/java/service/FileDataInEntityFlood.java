package service;

import dao.PostDao;
import entity.Flood;
import entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import view.HydrologyFile;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class FileDataInEntityFlood {

    @Qualifier("PostDao")
    @Autowired
    private PostDao postDao;

    private List<Flood> floodList;

    public FileDataInEntityFlood() {

    }

    public List<Flood> translateDataInEntityFlood (List<HydrologyFile> hydrologyFileList){

        this.floodList=new ArrayList<Flood>();
        for(HydrologyFile file : hydrologyFileList){

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date data=null;

            try {
                data=format.parse(file.getLocalDate().getYear()+"-"+file.getLocalDate().getMonthValue()+"-"+file.getLocalDate().getDayOfMonth());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Post post=postDao.searсh(file.getNamePost());
            floodList.add(new Flood(data,file.getCountDay(),file.getDownfall(),file.getRelativeHumidityAir(),
                    file.getDeficitHumidityAir(),file.getTemperatureMax(),file.getTemperatureMin(),
                    file.getTemperatureMax(),file.getTemperatureMin(),file.getSunShine(),file.getLevelSnow(),
                    file.getWaterReserveInSnow(),file.getLevelFreezingGround(),file.getHeightIceOnWater(),
                    file.getLewelWater(),"File",post));

        }
        return floodList;
    }
}
