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
import java.util.*;

@Service
public class WeatherDarkSkyHttpURLConnection implements WeatherDarkSkyService {

    static private	String API_KEY_DARK_SKY = "c74dbff3f42321f93ed75860c203a73e";
    static private String baseURL="https://api.darksky.net/forecast/";
    static private String baseURLforecast="https://api.darksky.net/forecast/";

    public WeatherDarkSkyHttpURLConnection() {
    }

    @Override
    public JSONObject getWeatherJSONObject(String date,String lat, String lon) throws Exception {

        String url = baseURL + API_KEY_DARK_SKY + "/" + lat + "," + lon +
                "," + date;

        final Map<String, String> parameters = new HashMap<>();
        parameters.put("exclude", "[currently,minutely,hourly,alerts,flags]");
        parameters.put("units", "si");

            HttpURLConnection connection = null;

            try {
                connection = (HttpURLConnection) new URL(url+getParamsString(parameters)).openConnection();
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
                String correct_responce="";
                for (int i=0;i<responce.length()-1;i++)
                {
                    char a=responce.charAt(i+1);
                    // проверка на принадлежность к цифрам
                    if (Character.isDigit(responce.charAt(i))&& a==' ')
                    {correct_responce+=responce.charAt(i); i++;}
                    else correct_responce+=responce.charAt(i);
                }
                String view_responce="";
                for (int i=0;i<correct_responce.length();i++)
                {
                    if((','==correct_responce.charAt(i))||(correct_responce.charAt(i)=='{') ||(correct_responce.charAt(i)=='['))
                    {view_responce+=responce.charAt(i); view_responce+="\n";}
                    else view_responce+=responce.charAt(i);
                }
                System.out.println(view_responce);

                JSONObject jsonObject=null;
                if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                    jsonObject = new JSONObject(correct_responce);
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
    public JSONObject getWeatherJSONObjectForecast(String date,String lat, String lon) throws Exception {

        String url = baseURLforecast+ API_KEY_DARK_SKY + "/" + lat + "," + lon+","+date;

        final Map<String, String> parameters = new HashMap<>();
        parameters.put("exclude", "[minutely,hourly,alerts,flags]");
        parameters.put("units", "si");

        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) new URL(url+getParamsString(parameters)).openConnection();
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
            String correct_responce="";
            for (int i=0;i<responce.length()-1;i++)
            {
                char a=responce.charAt(i+1);
                // проверка на принадлежность к цифрам
                if (Character.isDigit(responce.charAt(i))&& a==' ')
                {correct_responce+=responce.charAt(i); i++;}
                else correct_responce+=responce.charAt(i);
            }
            String view_responce="";
            for (int i=0;i<correct_responce.length();i++)
            {
                if((','==correct_responce.charAt(i))||(correct_responce.charAt(i)=='{') ||(correct_responce.charAt(i)=='['))
                {view_responce+=responce.charAt(i); view_responce+="\n";}
                else view_responce+=responce.charAt(i);
            }
            System.out.println(view_responce);

            JSONObject jsonObject=null;
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                jsonObject = new JSONObject(correct_responce);
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
    public WeatherDarkSky getWeather(String date,String lat, String lon) throws Exception {

            JSONObject jsonObject=getWeatherJSONObject(date,lat,lon);

            WeatherDarkSky weather=null;
            JSONObject jsonObjectInner=jsonObject.getJSONObject("daily");
            JSONArray jsonObjectInnerJSONArray=jsonObjectInner.getJSONArray("data");
            JSONObject jsonObjectInner3=jsonObjectInnerJSONArray.getJSONObject(0);

        String precipType="";
        String precipIntensity="";
        String precipIntensityMax="";
        String cloudCover="";
        try{
            precipType=jsonObjectInner3.get("precipType").toString();
        } catch (Exception e){
            precipType="";
        }
        try{
            precipIntensity=jsonObjectInner3.get("precipIntensity").toString();
        } catch (Exception e){
            precipIntensity="0";
        }
        try{
            precipIntensityMax=jsonObjectInner3.get("precipIntensityMax").toString();
        } catch (Exception e){
            precipIntensityMax="0";
        }
        try{
            cloudCover=jsonObjectInner3.get("cloudCover").toString();
        } catch (Exception e){
            cloudCover="";
        }


            if(precipType.equals("")) {
                weather = new WeatherDarkSky(jsonObject.get("latitude").toString(), jsonObject.get("longitude").toString(),
                        jsonObjectInner3.get("time").toString(), jsonObjectInner3.get("icon").toString(), cloudCover,
                        jsonObjectInner3.get("sunriseTime").toString(), jsonObjectInner3.get("sunsetTime").toString(),
                        precipIntensity, jsonObjectInner3.get("temperatureMin").toString(), jsonObjectInner3.get("temperatureMinTime").toString(),
                        jsonObjectInner3.get("temperatureMax").toString(), jsonObjectInner3.get("temperatureMaxTime").toString(),
                        jsonObjectInner3.get("temperatureHigh").toString(), jsonObjectInner3.get("temperatureLow").toString(),
                        jsonObjectInner3.get("dewPoint").toString(), jsonObjectInner3.get("humidity").toString());
            }
        if(!precipType.equals("")) {
            weather = new WeatherDarkSky(jsonObject.get("latitude").toString(), jsonObject.get("longitude").toString(),
                    jsonObjectInner3.get("time").toString(), jsonObjectInner3.get("icon").toString(), cloudCover,
                    jsonObjectInner3.get("sunriseTime").toString(), jsonObjectInner3.get("sunsetTime").toString(),
                    precipIntensity, jsonObjectInner3.get("temperatureMin").toString(), jsonObjectInner3.get("temperatureMinTime").toString(),
                    jsonObjectInner3.get("temperatureMax").toString(), jsonObjectInner3.get("temperatureMaxTime").toString(),
                    jsonObjectInner3.get("temperatureHigh").toString(), jsonObjectInner3.get("temperatureLow").toString(),
                    jsonObjectInner3.get("dewPoint").toString(), jsonObjectInner3.get("humidity").toString(),jsonObjectInner3.get("precipType").toString(),
                    precipIntensityMax);
        }

            return weather;
        }
    @Override
    public  WeatherDarkSky getWeatherForecast(String date,String lat, String lon) throws Exception {

        JSONObject jsonObject=getWeatherJSONObjectForecast(date,lat,lon);

        WeatherDarkSky weather=null;
        JSONObject jsonObjectInner=jsonObject.getJSONObject("currently");
        JSONObject jsonObjectInnerDaily=jsonObject.getJSONObject("daily");
        JSONArray  jsonObjectInnerData=jsonObjectInnerDaily.getJSONArray("data");
        JSONObject jsonObjectInnerInData=jsonObjectInnerData.getJSONObject(0);

        String precipType="";
        String precipIntensity="";
        String cloudCover="";
        try{
            precipType=jsonObjectInner.get("precipType").toString();
        } catch (Exception e){
            precipType="";
        }
        try{
            precipIntensity=jsonObjectInner.get("precipIntensity").toString();
        } catch (Exception e){
            precipIntensity="0";
        }
        try{
            cloudCover=jsonObjectInner.get("cloudCover").toString();
        } catch (Exception e){
            cloudCover="";
        }

        if(precipType.equals("")) {
            weather = new WeatherDarkSky(jsonObject.get("latitude").toString(), jsonObject.get("longitude").toString(),
                    jsonObjectInner.get("time").toString(), jsonObjectInner.get("icon").toString(), cloudCover,
                    precipIntensity,jsonObjectInner.get("temperature").toString(),jsonObjectInner.get("dewPoint").toString(),
                    jsonObjectInner.get("humidity").toString(),jsonObjectInnerInData.get("temperatureHigh").toString(),jsonObjectInnerInData.get("temperatureLow").toString());
        }
        if(!precipType.equals("")) {
            weather = new WeatherDarkSky(jsonObject.get("latitude").toString(), jsonObject.get("longitude").toString(),
                    jsonObjectInner.get("time").toString(), jsonObjectInner.get("icon").toString(), cloudCover,
                    precipIntensity,jsonObjectInner.get("temperature").toString(),jsonObjectInner.get("dewPoint").toString(),
                    jsonObjectInner.get("humidity").toString(),precipType,jsonObjectInnerInData.get("temperatureHigh").toString(),jsonObjectInnerInData.get("temperatureLow").toString());
        }

        return weather;
    }
    @Override
    public  List<WeatherDarkSky> getWeatherForecast(int yearStart, int monthStart,int dayStart, int hourStart,
                                                    int yearFinish, int monthFinish,int dayFinish, int hourFinish,
                                                    String lat, String lon, int step) throws Exception {

        List<WeatherDarkSky> listWeather=new ArrayList<>();


        for(int year=yearStart;year<=yearFinish;year++) {
            String yearStr = String.valueOf(year);

            for (int month = monthStart; month <= monthFinish; month++) {
                String monthStr = "";
                if (month < 10) monthStr = "0" + month;
                else monthStr += month;

                for ( ; dayStart != dayFinish; ) {
                    String dayStr = "";
                    if (dayStart < 10) dayStr ="0"+dayStart;
                    else dayStr += String.valueOf(dayStart);
                    if(dayStart<30) {dayStart++;
                        if (month < 10) monthStr="0"+month;
                        else monthStr += month;}
                    else if(dayStart==30) {dayStart=1; month++;}

                    for (int hour = hourStart; hour <= hourFinish; hour+=step) {
                        String hourStr = "";
                        if (hour < 10) hourStr = "0" + hour;
                        else hourStr += hour;

                        String date = yearStr + "-" + monthStr + "-" + dayStr + "T" + hourStr + ":" + "00" + ":" + "00";


                        JSONObject jsonObject = getWeatherJSONObjectForecast(date, lat, lon);

                        WeatherDarkSky weather = null;
                        JSONObject jsonObjectInner = jsonObject.getJSONObject("currently");
                        JSONObject jsonObjectInnerDaily=jsonObject.getJSONObject("daily");
                        JSONArray  jsonObjectInnerData=jsonObjectInnerDaily.getJSONArray("data");
                        JSONObject jsonObjectInnerInData=jsonObjectInnerData.getJSONObject(0);

                        String precipType="";
                        String precipIntensity="";
                        String cloudCover="";
                        try{
                            precipType=jsonObjectInner.get("precipType").toString();
                        } catch (Exception e){
                            precipType="";
                        }
                        try{
                            precipIntensity=jsonObjectInner.get("precipIntensity").toString();
                        } catch (Exception e){
                            precipIntensity="0";
                        }
                        try{
                            cloudCover=jsonObjectInner.get("cloudCover").toString();
                        } catch (Exception e){
                            cloudCover="";
                        }
                        if (precipType.equals("")) {
                            weather = new WeatherDarkSky(jsonObject.get("latitude").toString(), jsonObject.get("longitude").toString(),
                                    jsonObjectInner.get("time").toString(), jsonObjectInner.get("icon").toString(), cloudCover,
                                    precipIntensity, jsonObjectInner.get("temperature").toString(), jsonObjectInner.get("dewPoint").toString(),
                                    jsonObjectInner.get("humidity").toString(),jsonObjectInnerInData.get("temperatureHigh").toString(),jsonObjectInnerInData.get("temperatureLow").toString());
                        }
                        if (!precipType.equals("")) {
                            weather = new WeatherDarkSky(jsonObject.get("latitude").toString(), jsonObject.get("longitude").toString(),
                                    jsonObjectInner.get("time").toString(), jsonObjectInner.get("icon").toString(), cloudCover,
                                    precipIntensity, jsonObjectInner.get("temperature").toString(), jsonObjectInner.get("dewPoint").toString(),
                                    jsonObjectInner.get("humidity").toString(), precipType,jsonObjectInnerInData.get("temperatureHigh").toString(),jsonObjectInnerInData.get("temperatureLow").toString());
                        }
                        listWeather.add(weather);
                    }
                }
            }
        }
        return listWeather;
    }


    @Override
    public  List<WeatherDarkSky> getWeather(int yearStart, int monthStart,int dayStart, int hourStart,
                                            int yearFinish, int monthFinish,int dayFinish, int hourFinish,
                                            String lat, String lon, int step) throws Exception {

        List<WeatherDarkSky> listWeather=new ArrayList<>();


        for(int year=yearStart;year<=yearFinish;year++) {
            String yearStr=String.valueOf(year);

            for(int month=monthStart;month<=monthFinish;month++) {
                String monthStr = "";
                if (month < 10) monthStr = "0" + month;
                else monthStr += month;

                for (int day = dayStart; day <= dayFinish; day++) {
                    String dayStr = "";
                    if (day < 10) dayStr = "0" + day;
                    else dayStr += day;

//                        String hourStr = "";
//                        if (hour < 10) hourStr = "0" + hour;
//                        else hourStr += hour;

                        String date=year+"-"+monthStr+"-"+dayStr+"T"+"00"+":"+"00"+":"+"00";

                        JSONObject jsonObject = getWeatherJSONObject(date, lat, lon);

                        WeatherDarkSky weather = null;

                        JSONObject jsonObjectInner = jsonObject.getJSONObject("daily");
                        JSONArray jsonObjectInnerJSONArray = jsonObjectInner.getJSONArray("data");
                        JSONObject jsonObjectInner3 = jsonObjectInnerJSONArray.getJSONObject(0);

                        String precipType="";
                        String precipIntensity="";
                        String precipIntensityMax="";
                        String cloudCover="";
                        try{
                            precipType=jsonObjectInner3.get("precipType").toString();
                        } catch (Exception e){
                            precipType="";
                        }
                        try{
                            precipIntensity=jsonObjectInner3.get("precipIntensity").toString();
                        } catch (Exception e){
                            precipIntensity="0";
                        }
                        try{
                            precipIntensityMax=jsonObjectInner3.get("precipIntensityMax").toString();
                        } catch (Exception e){
                            precipIntensityMax="0";
                        }
                        try{
                            cloudCover=jsonObjectInner3.get("cloudCover").toString();
                        } catch (Exception e){
                            cloudCover="0";
                        }

                        if (precipType.equals("")) {
                            weather = new WeatherDarkSky(jsonObject.get("latitude").toString(), jsonObject.get("longitude").toString(),
                                    jsonObjectInner3.get("time").toString(), jsonObjectInner3.get("icon").toString(), cloudCover,
                                    jsonObjectInner3.get("sunriseTime").toString(), jsonObjectInner3.get("sunsetTime").toString(),
                                    precipIntensity, jsonObjectInner3.get("temperatureMin").toString(), jsonObjectInner3.get("temperatureMinTime").toString(),
                                    jsonObjectInner3.get("temperatureMax").toString(), jsonObjectInner3.get("temperatureMaxTime").toString(),
                                    jsonObjectInner3.get("temperatureHigh").toString(), jsonObjectInner3.get("temperatureLow").toString(),
                                    jsonObjectInner3.get("dewPoint").toString(), jsonObjectInner3.get("humidity").toString());
                        }
                        if (!precipType.equals("")) {
                            weather = new WeatherDarkSky(jsonObject.get("latitude").toString(), jsonObject.get("longitude").toString(),
                                    jsonObjectInner3.get("time").toString(), jsonObjectInner3.get("icon").toString(), cloudCover,
                                    jsonObjectInner3.get("sunriseTime").toString(), jsonObjectInner3.get("sunsetTime").toString(),
                                    precipIntensity, jsonObjectInner3.get("temperatureMin").toString(), jsonObjectInner3.get("temperatureMinTime").toString(),
                                    jsonObjectInner3.get("temperatureMax").toString(), jsonObjectInner3.get("temperatureMaxTime").toString(),
                                    jsonObjectInner3.get("temperatureHigh").toString(), jsonObjectInner3.get("temperatureLow").toString(),
                                    jsonObjectInner3.get("dewPoint").toString(), jsonObjectInner3.get("humidity").toString(), jsonObjectInner3.get("precipType").toString(),
                                    precipIntensityMax);

                        }
                        listWeather.add(weather);

                }

            }

        }

        return listWeather;
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


