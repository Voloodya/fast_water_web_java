package controller;

import dao.FloodDao;
import entity.Flood;
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

    @Autowired
    private WeatherDarkSkyHttpURLConnection weatherDarkSkyService;
    @Autowired
    private WeatherOpenMapHttpURLConnection weatherOpenMapService;
    @Autowired
    private ReadExcel readExcel;
    @Autowired
    private AggregationData aggregationData;

    List<Flood> listFlood=null;

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
        List<FloodView> listFloodViewsSort=new ArrayList<>();

        for (FloodView a : listFloodViews)
            listFloodViewsSort.add(a);
        Collections.sort(listFloodViewsSort);
        for(int i=0;i<listFloodViewsSort.size()-1;i++) {
            if(listFloodViewsSort.get(i).getIdPost()==listFloodViewsSort.get(i+1).getIdPost()){
            listFloodViewsSort.get(i+1).setChangeLevelWater(listFloodViewsSort.get(i));
            listFloodViewsSort.get(i+1).setSpeedChangeLevelWater(listFloodViewsSort.get(i));
            listFloodViewsSort.get(i+1).setChangeLevelSnow(listFloodViewsSort.get(i));
            listFloodViewsSort.get(i+1).setSpeedChangeLevelSnow(listFloodViewsSort.get(i));}
                }
        uiModel.addAttribute("listChangeFlood",listFloodViewsSort);
        uiModel.addAttribute("listChangeFlood",listFloodViewsSort);
        return "main_page";
    }

    @RequestMapping(value = "/administration", method = RequestMethod.GET)//Mapping web context, на который будет реагировать метод
    public String administration(Model uiModel,@RequestParam(value = "latitude",required = false) String latitude, @RequestParam(value = "longitude",required = false) String longitude,
                                 @RequestParam(value = "post",required = false) String post,
                                 @RequestParam(value = "yearStart",defaultValue ="2020") String yearStart, @RequestParam(value = "monthStart",defaultValue ="4") String monthStart,
                                 @RequestParam(value = "dayStart",defaultValue ="20") String dayStart, @RequestParam(value = "yearFinish",defaultValue ="2020") String yearFinish,
                                 @RequestParam(value = "monthFinish",defaultValue ="4") String monthFinish, @RequestParam(value = "dayFinish",defaultValue ="25") String dayFinish,
                                 @RequestParam(value = "hourStep",defaultValue ="24") String hourStep, @RequestParam(value = "aggregate",defaultValue="") String aggregate) {
        List<PostView> listPostView=postService.getAllPostView();
        uiModel.addAttribute("listPosts",listPostView);
        List<WeatherDarkSky> listWeatherDarkSky= null;
        List<Hydrology> hydrologyList=null;
        if((latitude!=null) && (!latitude.equals(""))&&(longitude!=null) && (!longitude.equals(""))) {
            try {
                listWeatherDarkSky = weatherDarkSkyService.getWeather(Integer.valueOf(yearStart), Integer.valueOf(monthStart), Integer.valueOf(dayStart), 00,
                        Integer.valueOf(yearFinish), Integer.valueOf(monthFinish), Integer.valueOf(dayFinish), 24, latitude, longitude, Integer.valueOf(hourStep));
            } catch (Exception e) {
                e.printStackTrace();
            }
            uiModel.addAttribute("listWeatherDarkSky",listWeatherDarkSky);
        }
//        if((post!=null) && (!post.equals(""))) {
//            hydrologyList = readExcel.readExcelHydrology("E:/Fast_Water_project/FastWaterWeb/Hydrometcentre.xls",post,
//                   Integer.valueOf(yearStart),Integer.valueOf(monthStart),Integer.valueOf(dayStart),
//                    Integer.valueOf(yearFinish),Integer.valueOf(monthFinish),Integer.valueOf(dayFinish));
//            uiModel.addAttribute("hydrologyList",hydrologyList);
//        }
//        if(listWeatherDarkSky!=null && hydrologyList!=null) {
//            listFlood = aggregationData.aggregation(hydrologyList, listWeatherDarkSky, post);
//            uiModel.addAttribute("listFlood", listFlood);
//        }
        return "administration";
    }


    @RequestMapping(value = "/download", method = RequestMethod.POST)//Mapping web context, на который будет реагировать метод
    public @ResponseBody String download(@RequestParam(value = "aggregate",defaultValue ="something") String objectList) {
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

}
