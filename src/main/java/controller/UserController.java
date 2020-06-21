package controller;

import dao.FloodDao;
import dao.PostDao;
import dao.PredictionLevelWaterDao;
import entity.Flood;
import entity.Post;
import entity.Predictionlevelwater;
import org.apache.poi.ss.usermodel.DateUtil;
import org.primefaces.util.DateUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.*;
import view.*;
import weatherAPI.WeatherDarkSky;
import weatherAPI.WeatherDarkSkyHttpURLConnection;
import weatherAPI.WeatherOpenMap;
import weatherAPI.WeatherOpenMapHttpURLConnection;

import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/water")
public class UserController {

    @Qualifier("GeographKoordsService")
    @Autowired
    private GeographKoordsService geographKoordsService;
    @Qualifier("PostService")
    @Autowired
    private PostService postService;
    @Qualifier("FloodService")
    @Autowired
    private FloodService floodService;
    @Qualifier("FloodDao")
    @Autowired
    private FloodDao floodDao;
    @Qualifier("PostDao")
    @Autowired
    private PostDao postDao;
    @Qualifier("PredictionLevelWaterDao")
    @Autowired
    private PredictionLevelWaterDao predictionWaterDao;
    @Autowired
    private WeatherDarkSkyHttpURLConnection weatherDarkSkyService;
    @Autowired
    private WeatherOpenMapHttpURLConnection weatherOpenMapService;
    @Autowired
    private ReadExcel readExcel;
    @Autowired
    private AggregationData aggregationFileAndRemoute;
    @Autowired
    private FileDataInEntityFlood fileDataInEntityFlood;
    @Autowired
    private NeuralNetwork neuralNetwork;

    List<Flood> listFlood=null;
    List<HydrologyFile> hydrologyListFile=null;
    List<WeatherDarkSky> listWeatherDarkSky= null;
    List<PostView> listPostView=null;
    List<FloodView> listDataBaseFlood=null;
    List<Predictionlevelwater> predictionlevelwaterList=null;
    List<FloodView> floodViewListForForecast=null;

    Calendar date = new GregorianCalendar();

    @RequestMapping(value = "/main", method = RequestMethod.GET)//Mapping web context, на который будет реагировать метод
    public String main(Model uiModel, @RequestParam(value = "latitude2",defaultValue ="51.75641") String latitude2, @RequestParam(value = "longitude2",defaultValue ="55.118132") String longitude2,
                       @RequestParam(value = "yearStart2",defaultValue ="2020") String yearStart2, @RequestParam(value = "monthStart2",defaultValue ="4") String monthStart2,
                       @RequestParam(value = "dayStart2",defaultValue ="27") String dayStart2, @RequestParam(value = "yearFinish2",defaultValue ="2020") String yearFinish2,
                       @RequestParam(value = "monthFinish2",defaultValue ="04") String monthFinish2, @RequestParam(value = "dayFinish2",defaultValue ="28") String dayFinish2,
                       @RequestParam(value = "location",defaultValue ="Unknown") String location,
                       @RequestParam(value = "yearStart1",defaultValue ="2020") String yearStart1, @RequestParam(value = "monthStart1",defaultValue ="4") String monthStart1,
                       @RequestParam(value = "dayStart1",defaultValue ="6") String dayStart1, @RequestParam(value = "yearFinish1",defaultValue ="2020") String yearFinish1,
                       @RequestParam(value = "monthFinish1",defaultValue ="4") String monthFinish1, @RequestParam(value = "dayFinish1",defaultValue ="8") String dayFinish1) {

        yearStart2=String.valueOf(date.get(Calendar.YEAR));
        monthStart2=String.valueOf(date.get(Calendar.MONTH)+1);
        dayStart2=String.valueOf(date.get(Calendar.DAY_OF_MONTH));
        yearFinish2=String.valueOf(Integer.valueOf(yearStart2));
        monthFinish2=String.valueOf(Integer.valueOf(monthStart2));
        int dayFinish=Integer.valueOf(dayStart2)+3;

        if (dayFinish>30){
            monthFinish2=String.valueOf(Integer.valueOf(monthFinish2)+1);
            dayFinish2=String.valueOf(dayFinish-30);
        }
        else dayFinish2=String.valueOf(dayFinish);

        List<WeatherOpenMap> listWeatherOpenMap= null;
        List<FloodView> listFloodViews=null;
        try {
            listWeatherOpenMap = weatherOpenMapService.getWeatherForecast(latitude2,longitude2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        uiModel.addAttribute("listForecastOpenMap",listWeatherOpenMap);
        List<WeatherDarkSky> listForecastDarkSky= null;
        try {
            listForecastDarkSky = weatherDarkSkyService.getWeatherForecast(Integer.valueOf(yearStart2),Integer.valueOf(monthStart2),Integer.valueOf(dayStart2),00,
                    Integer.valueOf(yearFinish2),Integer.valueOf(monthFinish2),Integer.valueOf(dayFinish2),24,latitude2,longitude2,15);
        } catch (Exception e) {
            e.printStackTrace();
        }
        uiModel.addAttribute("listForecastDarkSky",listForecastDarkSky);
        if (location.equals("Unknown") || location.equals("")){
        listFloodViews=floodService.getAllFloodViev();

        uiModel.addAttribute("listFlood",listFloodViews);}
        else {listFloodViews=floodService.searhFloodViev(location);
            uiModel.addAttribute("listFlood",listFloodViews);}
//        List<FloodView> listFloodViewsSort=new ArrayList<>();
//        for (FloodView a : listFloodViews)
//            listFloodViewsSort.add(a);
        Collections.sort(listFloodViews);
        for(int i=0;i<listFloodViews.size()-1;i++) {
            if(listFloodViews.get(i).getIdPost()==listFloodViews.get(i+1).getIdPost()){
            listFloodViews.get(i+1).setChangeLevelWater(listFloodViews.get(i));
            listFloodViews.get(i+1).setSpeedChangeLevelWater(listFloodViews.get(i));
            listFloodViews.get(i+1).setChangeLevelSnow(listFloodViews.get(i));
            listFloodViews.get(i+1).setSpeedChangeLevelSnow(listFloodViews.get(i));}
                }
        uiModel.addAttribute("listChangeFlood",listFloodViews);
        uiModel.addAttribute("listChangeFlood",listFloodViews);

        predictionlevelwaterList=predictionWaterDao.getAll();
        uiModel.addAttribute("listPredictLevelWater",predictionlevelwaterList);
        return "main_page";
    }

    @RequestMapping(value = "/administration", method = RequestMethod.GET)//Mapping web context, на который будет реагировать метод
    public String administration(Model uiModel,@RequestParam(value = "latitude",required = false) String latitude, @RequestParam(value = "longitude",required = false) String longitude,
                                 @RequestParam(value = "post",required = false) String post,
                                 @RequestParam(value = "yearStart",defaultValue ="2020") String yearStart, @RequestParam(value = "monthStart",defaultValue ="4") String monthStart,
                                 @RequestParam(value = "dayStart",defaultValue ="20") String dayStart, @RequestParam(value = "yearFinish",defaultValue ="2020") String yearFinish,
                                 @RequestParam(value = "monthFinish",defaultValue ="4") String monthFinish, @RequestParam(value = "dayFinish",defaultValue ="25") String dayFinish,
                                 @RequestParam(value = "hourStep",defaultValue ="24") String hourStep, @RequestParam(value = "aggregate",defaultValue="") String aggregate) {

        listPostView=postService.getAllPostView();
        uiModel.addAttribute("listPosts",listPostView);

        if((latitude!=null) && (!latitude.equals(""))&&(longitude!=null) && (!longitude.equals(""))) {
            try {
                listWeatherDarkSky = weatherDarkSkyService.getWeather(Integer.valueOf(yearStart), Integer.valueOf(monthStart), Integer.valueOf(dayStart), 00,
                        Integer.valueOf(yearFinish), Integer.valueOf(monthFinish), Integer.valueOf(dayFinish), 24, latitude, longitude, Integer.valueOf(hourStep));
            } catch (Exception e) {
                e.printStackTrace();
            }
            uiModel.addAttribute("listWeatherDarkSky",listWeatherDarkSky);
        }
        if((post!=null) && (!post.equals(""))) {
            hydrologyListFile = readExcel.readExcelAll("Hydrometcentre_data.xls",post,
                   Integer.valueOf(yearStart),Integer.valueOf(monthStart),Integer.valueOf(dayStart),
                    Integer.valueOf(yearFinish),Integer.valueOf(monthFinish),Integer.valueOf(dayFinish));
            uiModel.addAttribute("hydrologyListFile",hydrologyListFile);
        }
        if(listWeatherDarkSky!=null && hydrologyListFile!=null && post!=null) {
            listFlood = aggregationFileAndRemoute.aggregationFileAndRemoute(hydrologyListFile, listWeatherDarkSky, post);
            if (listFlood!=null) uiModel.addAttribute("listFlood", listFlood); }


            listDataBaseFlood=floodService.getAllFloodViev();
            uiModel.addAttribute("listDataBaseFlood",listDataBaseFlood);

        return "administration";
    }

    @RequestMapping(value = "/administrationDell", method = RequestMethod.GET)//Mapping web context, на который будет реагировать метод
    public String administrationDell(Model uiModel,
                                 @RequestParam(value = "post",required = false) String post,
                                 @RequestParam(value = "yearStart",defaultValue ="2020") String yearStart, @RequestParam(value = "monthStart",defaultValue ="4") String monthStart,
                                 @RequestParam(value = "dayStart",defaultValue ="20") String dayStart, @RequestParam(value = "yearFinish",defaultValue ="2020") String yearFinish,
                                 @RequestParam(value = "monthFinish",defaultValue ="4") String monthFinish, @RequestParam(value = "dayFinish",defaultValue ="25") String dayFinish) throws ParseException {

        uiModel.addAttribute("listPosts",listPostView);

        uiModel.addAttribute("listWeatherDarkSky",listWeatherDarkSky);

        uiModel.addAttribute("hydrologyListFile",hydrologyListFile);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date startDate=null;
        Date finishDate=null;
        startDate=format.parse(yearStart+"-"+monthStart+"-"+dayStart);
        finishDate=format.parse(yearFinish+"-"+monthFinish+"-"+dayFinish);
        startDate= new Date(startDate.getTime() - 1 * 24 * 3600 * 1000l);
        finishDate= new Date(finishDate.getTime() + 1 * 24 * 3600 * 1000l);
        for (FloodView floodView : listDataBaseFlood){

            if(floodView.getNamePost().equals(post)&& floodView.getDate().after(startDate)&&
            floodView.getDate().before(finishDate)){
                floodDao.dell(floodView.getIdFlood());
            }
        }

        listDataBaseFlood=floodService.getAllFloodViev();
        uiModel.addAttribute("listDataBaseFlood",listDataBaseFlood);

        return "administration";
    }

    @RequestMapping(value = "/administrationPrognoz", method = RequestMethod.GET)//Mapping web context, на который будет реагировать метод
    public String administrationPrognoz(Model uiModel,
                                     @RequestParam(value = "post",required = false) String post,
                                     @RequestParam(value = "yearStart",defaultValue ="2020") String yearStart, @RequestParam(value = "monthStart",defaultValue ="4") String monthStart,
                                     @RequestParam(value = "dayStart",defaultValue ="20") String dayStart, @RequestParam(value = "yearFinish",defaultValue ="2020") String yearFinish,
                                     @RequestParam(value = "monthFinish",defaultValue ="4") String monthFinish, @RequestParam(value = "dayFinish",defaultValue ="25") String dayFinish) throws ParseException {

        uiModel.addAttribute("listPosts",listPostView);

        uiModel.addAttribute("listWeatherDarkSky",listWeatherDarkSky);

        uiModel.addAttribute("hydrologyListFile",hydrologyListFile);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date startDate=null;
        Date finishDate=null;
        startDate=format.parse(yearStart+"-"+monthStart+"-"+dayStart);
        finishDate=format.parse(yearFinish+"-"+monthFinish+"-"+dayFinish);

        Date startDateForecast=new Date(startDate.getTime() - 1 * 24 * 3600 * 1000l);
        Date dateFinishCalculat=new Date(startDate.getTime() + 1 * 24 * 3600 * 1000l);
        startDate= new Date(startDate.getTime() - 10 * 24 * 3600 * 1000l);

        floodViewListForForecast=floodService.searhFloodViev(post,startDate,finishDate);

        calculationBeforeIndicators(dateFinishCalculat);

        Double forecastChandgeLevelWater;

        for (int i=0;i<floodViewListForForecast.size();i++){

            if(floodViewListForForecast.get(i).getDate().after(startDateForecast)){
                forecastChandgeLevelWater=neuralNetwork.getForecast(floodViewListForForecast.get(i));
                floodViewListForForecast.get(i).setForecastChangeLevelWater(forecastChandgeLevelWater);
                floodViewListForForecast.get(i).setChangeLevelWater(forecastChandgeLevelWater);
                if(i>0){
                floodViewListForForecast.get(i).setLevelWater(floodViewListForForecast.get(i-1).getLevelWater()+
                        forecastChandgeLevelWater);}
                calculationBeforeIndicators(new Date(dateFinishCalculat.getTime() +(i+1) * 24 * 3600 * 1000l));
            }
        }

        Post postPredict=postDao.searсh(floodViewListForForecast.get(0).getNamePost());
        List <Predictionlevelwater> predictLevelWaterDellList=predictionWaterDao.search(postPredict.getIdPost());

        for (Predictionlevelwater predict : predictLevelWaterDellList){
            predictionWaterDao.dell(predict);
        }

        for(int i=0;i<floodViewListForForecast.size();i++){
            if(floodViewListForForecast.get(i).getDate().after(startDateForecast)){
                Post post_=postDao.searсh(floodViewListForForecast.get(i).getIdPost());
                if(post_!=null){
                predictionWaterDao.add(new Predictionlevelwater(floodViewListForForecast.get(i).getDate(),
                        floodViewListForForecast.get(i).getTime(),floodViewListForForecast.get(i).getForecastChangeLevelWater(),
                        floodViewListForForecast.get(i).getLevelWater(),post_));}
            }

        }

        uiModel.addAttribute("listDataBaseFlood",floodViewListForForecast);

        return "administration";
    }


    @RequestMapping(value = "/downloadAgregate", method = RequestMethod.POST)//Mapping web context, на который будет реагировать метод
    public @ResponseBody String downloadAgregate(@RequestParam(value = "downloadAgregate",defaultValue ="something") String objectList) {
        //Запись в БД
        for(Flood x : listFlood)
        floodDao.add(x);
        return "Запись в базу данных завершена";
    }

    @RequestMapping(value = "/downloadFile", method = RequestMethod.POST)//Mapping web context, на который будет реагировать метод
    public @ResponseBody String downloadFile(@RequestParam(value = "downloadFile",defaultValue ="something") String objectList) {

        if(hydrologyListFile!=null) {
            listFlood = fileDataInEntityFlood.translateDataInEntityFlood(hydrologyListFile);
        }
        //Запись в БД
        for(Flood x : listFlood)
            floodDao.add(x);
        return "Запись в базу данных завершена";
    }

    //Model объекта на стороне Фронта для сопоставления с объектом на стороне Бэка
    //(в методе addKoords). В jsp файле создаётся соответствующая данной модели форма
    @RequestMapping(value = "/idpost", method = RequestMethod.GET)
    public ModelAndView idPost() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("idPostFromServer", new PostView());
        modelAndView.setViewName("search_post");
        return modelAndView; //Вазврат представления search_post.jsp
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)//Mapping web context, на который будет реагировать метод
    public @ResponseBody PostView post(@ModelAttribute("idPostFromServer") PostView post) {
        PostView postView=postService.searсhPostViev(post.getIdPost());
        return postView;
    }


    //Model объекта на стороне Фронта для сопоставления с объектом на стороне Бэка
    //(в методе addKoords). В jsp файле создаётся соответствующая данной модели форма
    @RequestMapping(value = "/new_koords", method = RequestMethod.GET)
    public ModelAndView newKoords() {
        ModelAndView modelAndView = new ModelAndView();
        //Запись модели под названием "koordsFromServer" со значением - пустой объект KoordsView
        modelAndView.addObject("koordsFromServer", new KoordsView());
        //Представление куда хотим перейти. Значение записанные в этом представлении будут записаны в объект KoordsView
        modelAndView.setViewName("add_geographKoords");
        return modelAndView; //Вазврат модели представления add_geographKoords.jsp
    }

    @RequestMapping(value = "/add_koords", method = RequestMethod.PUT)
    public @ResponseBody
    List<KoordsView> addKoords(@ModelAttribute("koordsFromServer") KoordsView koords){
        geographKoordsService.add(koords);
        return null;
    }

    public void calculationBeforeIndicators(Date dateFinish){

        double changeSnowBefore10days=0;

        for (int i=1;i<floodViewListForForecast.size();i++){

            if(floodViewListForForecast.get(i).getDate().before(dateFinish)) {
                floodViewListForForecast.get(i).setChangeLevelWater(floodViewListForForecast.get(i - 1));
                floodViewListForForecast.get(i).setChangeLevelSnow(floodViewListForForecast.get(i - 1));
                if (i >= 10) {
                    for (int j = i - 10; j < 10; j++) {
                        changeSnowBefore10days += floodViewListForForecast.get(j).getChangeLevelSnow();
                    }
                    floodViewListForForecast.get(i).setChangeSnowBefore10days(changeSnowBefore10days);
                    changeSnowBefore10days = 0;
                }
                if (i >= 3)
                    floodViewListForForecast.get(i).setDownfallBefore3days(floodViewListForForecast.get(i - 3).getSnowRain() + floodViewListForForecast.get(i - 2).getSnowRain() + floodViewListForForecast.get(i - 1).getSnowRain());
                if (i >= 3)
                    floodViewListForForecast.get(i).setTemperatureMiddleBefore3days((floodViewListForForecast.get(i - 3).getTemperatureMidle() + floodViewListForForecast.get(i - 2).getTemperatureMidle() + floodViewListForForecast.get(i - 1).getTemperatureMidle()) / 3);
                if (i >= 3)
                    floodViewListForForecast.get(i).setChangeWaterBefore3dayMiddle((floodViewListForForecast.get(i - 3).getChangeLevelWater() + floodViewListForForecast.get(i - 2).getChangeLevelWater() + floodViewListForForecast.get(i - 1).getChangeLevelWater()) / 3);
            }
        }
    }

}
