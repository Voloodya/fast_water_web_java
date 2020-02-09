package weatherAPI;

import org.json.JSONObject;

import java.util.List;

public interface WeatherDarkSkyService {

    JSONObject getWeatherJSONObject(String date, String lat, String lon) throws Exception;
    JSONObject getWeatherJSONObjectForecast(String date,String lat, String lon) throws Exception;
    WeatherDarkSky getWeather(String date,String lat, String lon) throws Exception;
    WeatherDarkSky getWeatherForecast(String date,String lat, String lon) throws Exception;
    List<WeatherDarkSky> getWeatherForecast(int yearStart, int monthStart, int dayStart, int hourStart,
                                            int yearFinish, int monthFinish, int dayFinish, int hourFinish,
                                            String lat, String lon, int step) throws Exception;
    List<WeatherDarkSky> getWeather(int yearStart, int monthStart,int dayStart, int hourStart,
                                                  int yearFinish, int monthFinish,int dayFinish, int hourFinish,
                                                  String lat, String lon,int step) throws Exception;
}
