package weatherAPI;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeatherOpenMapHttpURLConnection implements WeatherOpenMapService {

    private static 	String API_KEY_OpenWeatherMap = "f773afe63ddec7ee53a9142459191cdd";
    private	String CITY_ID = "YOUR_CITY_ID";
    private static String UNITS = "metric"; // or imperial
    private	static String url_OpenWeatherMap_Forecast = "https://api.openweathermap.org/data/2.5/forecast?";
    private	static String url_OpenWeatherMap_Current = "https://api.openweathermap.org/data/2.5/weather";

    @Override
    public JSONObject getWeatherJSONObjectForecast(String lat, String lon) throws Exception {

        List<String> parameters = new ArrayList<>();
        parameters.add("lat=" + lat);
        parameters.add("lon=" + lon);
        parameters.add("appid=" + API_KEY_OpenWeatherMap);
        parameters.add("units=" + UNITS);

        StringBuffer parametersList = new StringBuffer();
        for (int i = 0; i < parameters.size(); i++) {
            parametersList.append(((i > 0) ? "&" : "") + parameters.get(i));
        }


        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) new URL(url_OpenWeatherMap_Forecast+parametersList.toString()).openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(500);
            connection.setReadTimeout(500);
            connection.setDoOutput(false); //Говорим о том, что параметров не будет
            connection.connect();

            StringBuilder strBuilder = new StringBuilder();
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

                String line;
                while ((line = reader.readLine()) != null) {
                    strBuilder.append(line);
                    strBuilder.append("\n");
                }
            } else {
                System.out.println("fall:" + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }

            String responce=strBuilder.toString();
//            String view_responce="";
//            for (int i=0;i<responce.length();i++)
//            {
//                if((','==responce.charAt(i))||(responce.charAt(i)=='{') ||(responce.charAt(i)=='['))
//                {view_responce+=responce.charAt(i); view_responce+="\n";}
//                else view_responce+=responce.charAt(i);
//            }
        //    System.out.println(view_responce);

            JSONObject jsonObject=null;
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                jsonObject = new JSONObject(responce);
            }
            return jsonObject;

        } catch (Throwable cause) {
            cause.printStackTrace();
            return null;
        } finally {
            if (connection != null) connection.disconnect();
        }

    }
    @Override
    public JSONObject getWeatherJSONObjectCurrent(String lat, String lon) throws Exception {


        final Map<String, String> parameters = new HashMap<>();
        parameters.put("lat", lat);
        parameters.put("lon", lon);
        parameters.put("appid", API_KEY_OpenWeatherMap);
        parameters.put("units", UNITS);

        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) new URL(url_OpenWeatherMap_Current+getParamsString(parameters)).openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(500);
            connection.setReadTimeout(500);
            connection.setDoOutput(false); //Говорим о том, что параметров не будет
            connection.connect();

            StringBuilder strBuilder = new StringBuilder();
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

                String line;
                while ((line = reader.readLine()) != null) {
                    strBuilder.append(line);
                    strBuilder.append("\n");
                }
            } else {
                System.out.println("fall:" + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }

            String responce=strBuilder.toString();
            String view_responce="";
            for (int i=0;i<responce.length();i++)
            {
                if((','==responce.charAt(i))||(responce.charAt(i)=='{') ||(responce.charAt(i)=='['))
                {view_responce+=responce.charAt(i); view_responce+="\n";}
                else view_responce+=responce.charAt(i);
            }
            System.out.println(view_responce);

            JSONObject jsonObject=null;
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                jsonObject = new JSONObject(responce);
            }
            return jsonObject;

        } catch (Throwable cause) {
            cause.printStackTrace();
            return null;
        } finally {
            if (connection != null) connection.disconnect();
        }

    }
    @Override
    public List<WeatherOpenMap> getWeatherForecast(String lat, String lon) throws Exception {

        JSONObject jsonObject=getWeatherJSONObjectForecast(lat,lon);

        List<WeatherOpenMap> listWeather=new ArrayList<>();
        JSONArray listJsonObjectInner=jsonObject.getJSONArray("list");
        JSONObject sityJsonObjectInner=jsonObject.getJSONObject("city");
        JSONObject koordsJsonObjectInner=sityJsonObjectInner.getJSONObject("coord");

        for(int i=0;i<20;i++) {
            JSONObject jsonObjectInner = listJsonObjectInner.getJSONObject(i);
            String data_seconds = jsonObjectInner.get("dt").toString();
            JSONObject jsonObjectInnerMain = jsonObjectInner.getJSONObject("main");
            JSONObject jsonObjectInnerClouds = jsonObjectInner.getJSONObject("clouds");

            JSONObject jsonObjectInnerRain = null;
            try {
                jsonObjectInnerRain = jsonObjectInner.getJSONObject("rain");
            } catch (Exception e) {
                jsonObjectInnerRain = null;
            }
            try {
                jsonObjectInnerRain = jsonObjectInner.getJSONObject("snow");
            } catch (Exception e) {
                jsonObjectInnerRain = null;
            }
            if (jsonObjectInnerRain == null) {
                WeatherOpenMap weatherOpenMap=new WeatherOpenMap(koordsJsonObjectInner.get("lat").toString(),
                        koordsJsonObjectInner.get("lon").toString(), data_seconds, jsonObjectInnerMain.get("temp").toString(),
                        jsonObjectInnerMain.get("temp_min").toString(), jsonObjectInnerMain.get("temp_max").toString(),
                        jsonObjectInnerMain.get("humidity").toString(), jsonObjectInnerClouds.get("all").toString());
                listWeather.add(weatherOpenMap);
            }
            if (jsonObjectInnerRain != null) {
                listWeather.add(new WeatherOpenMap(koordsJsonObjectInner.get("lat").toString(),
                        koordsJsonObjectInner.get("lon").toString(), data_seconds, jsonObjectInnerMain.get("temp").toString(),
                        jsonObjectInnerMain.get("temp_min").toString(), jsonObjectInnerMain.get("temp_max").toString(),
                        jsonObjectInnerMain.get("humidity").toString(), jsonObjectInnerClouds.get("all").toString(),
                        jsonObjectInnerRain.toString(),jsonObjectInnerRain.get("3h").toString()));
            }

        }
        return listWeather;
    }
    @Override
    public WeatherOpenMap getWeatherCurrent(String lat, String lon) throws Exception {

        JSONObject jsonObject=getWeatherJSONObjectCurrent(lat,lon);

        WeatherOpenMap weather=null;
        String date_seconds=jsonObject.get("dt").toString();
        JSONObject jsonObjectCoordsInner=jsonObject.getJSONObject("coord");
        JSONArray jsonObjectInnerJSONArray=jsonObject.getJSONArray("weather");
        JSONObject jsonObjectInnerArray=jsonObjectInnerJSONArray.getJSONObject(0);
        JSONObject jsonObjectMainInner=jsonObject.getJSONObject("main");
        JSONObject jsonObjectCloudsInner=jsonObject.getJSONObject("clouds");
        JSONObject jsonObjectSunInner=jsonObject.getJSONObject("sys");

        JSONObject jsonObjectInnerRain = null;
        try {
            jsonObjectInnerRain = jsonObject.getJSONObject("rain");
        } catch (Exception e) {
            jsonObjectInnerRain = null;
        }
        try {
            jsonObjectInnerRain = jsonObject.getJSONObject("snow");
        } catch (Exception e) {
            jsonObjectInnerRain = null;
        }

        if(jsonObjectInnerRain==null){

            weather=new WeatherOpenMap(jsonObjectCoordsInner.get("lat").toString(),jsonObjectCoordsInner.get("lon").toString(),
                    jsonObject.get("dt").toString(),jsonObjectSunInner.get("sunrise").toString(),
                    jsonObjectSunInner.get("sunset").toString(),jsonObjectMainInner.get("temp").toString(),
                    jsonObjectMainInner.get("temp_min").toString(),jsonObjectMainInner.get("temp_max").toString(),
                    jsonObjectMainInner.get("humidity").toString(),jsonObjectCloudsInner.get("all").toString(),"without rain/snow");
        }


        if(jsonObjectInnerRain!=null){
            String osadky= jsonObjectInnerRain.toString().equals("rain") ? "rain" : "snow";

            weather=new WeatherOpenMap(jsonObjectCoordsInner.get("lat").toString(),jsonObjectCoordsInner.get("lon").toString(),
                    jsonObject.getJSONObject("dt").toString(),jsonObjectSunInner.get("sunrise").toString(),
                    jsonObjectSunInner.get("sunset").toString(),jsonObjectMainInner.get("temp").toString(),
                    jsonObjectMainInner.get("temp_min").toString(),jsonObjectMainInner.get("temp_max").toString(),
                    jsonObjectMainInner.get("humidity").toString(),jsonObjectCloudsInner.get("all").toString(),"without rain/snow",
                    jsonObjectInnerRain.toString(),jsonObjectInnerRain.get("1h").toString(),jsonObjectInnerRain.get("3h").toString());
        }

        return weather;
    }

    public static String getParamsString(final Map<String, String> params) {
        final StringBuilder result = new StringBuilder("?");

        params.forEach((name, value) -> {
            try {
                result.append(URLEncoder.encode(name, "UTF-8"));
                if(!value.equals("")){
                    result.append('=');
                    result.append(URLEncoder.encode(value, "UTF-8"));}
                result.append('&');
            } catch (final UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });

        final String resultString = result.toString();
        return !resultString.isEmpty()
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
}
