package weatherAPI;

import org.json.JSONObject;

import java.util.List;

public interface WeatherOpenMapService {

    JSONObject getWeatherJSONObjectForecast(String lat, String lon) throws Exception;
    JSONObject getWeatherJSONObjectCurrent(String lat, String lon) throws Exception;
    List<WeatherOpenMap> getWeatherForecast(String lat, String lon) throws Exception;
    WeatherOpenMap getWeatherCurrent(String lat, String lon) throws Exception;
}
