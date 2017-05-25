package com.example.weather;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.io.InputStream;

/**
 * Created by antoniopedro on 07/05/17.
 */

public class Weather {

    private static final String URL =
            " http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&q=%s&units=metric&appid=ea132beb7225d8f39b6516bfa0ce4ab3";

    private static String forecastDaysNum = "7";

    public static String setWeatherIcon(int actualId){
        System.out.println("set weather icon");
        int id = actualId / 100;
        String icon = "";
            switch(id) {
                case 2 : icon = "&#xf01e;";
                    break;
                case 3 : icon = "&#xf01c;";
                    break;
                case 7 : icon = "&#xf014;";
                    break;
                case 8 : icon = "&#xf013;";
                    break;
                case 6 : icon = "&#xf01b;";
                    break;
                case 5 : icon = "&#xf019;";
                    break;
        }
        return icon;
    }

    public interface AsyncResponse {
        void processFinish(String output1, String output2, String output3, String output4, String output5,
                String output6, String output7, String output8, String output9, String output10,
                           String output11, String output12, String output13, String output14,
                           String output15, String output16, String output17, String output18,
                           String output19, String output20, String output21, String output22);
    }

    public static class placeIdTask extends AsyncTask<String, Void, JSONObject> {

        public AsyncResponse delegate = null;

        public placeIdTask(AsyncResponse asyncResponse) {
            delegate = asyncResponse;
        }

        @Override
        protected JSONObject doInBackground(String... params) {

            JSONObject jsonWeather = null;
            try {
                jsonWeather = getWeatherJSON(params[0],forecastDaysNum);
            } catch (Exception e) {
                Log.d("Error", "Cannot process JSON results", e);
            }

            return jsonWeather;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            try {

                if(json != null){
                    JSONArray jlist = (JSONArray) json.get("list");
                    int length = jlist.length();
                    String city = "";

                    String minTemperature[] = new String[length];
                    String maxTemperature[] = new String[length];
                    String iconText[] = new String[length];

                    for (int i = 0; i < length; i++){

                        JSONObject jsonObject = jlist.getJSONObject(i);

                        JSONObject weather = jsonObject.getJSONArray("weather").getJSONObject(0);

                        DateFormat df = DateFormat.getDateTimeInstance();

                        city = json.getJSONObject("city").getString("name");

                        minTemperature[i] = String.format("%.2f", jsonObject.getJSONObject("temp").getDouble("min"))+ "°";
                        maxTemperature[i] = String.format("%.2f", jsonObject.getJSONObject("temp").getDouble("max"))+ "°";

                        iconText[i] = setWeatherIcon(weather.getInt("id"));


                    }
                    System.out.println(city);
                    for(int j=0; j<length;j++){
                        System.out.println(minTemperature[j]);
                        System.out.println(maxTemperature[j]);
                        System.out.println(iconText[j]);
                    }

                    delegate.processFinish(city, minTemperature[0], maxTemperature[0],
                            minTemperature[1], maxTemperature[1],
                            minTemperature[2], maxTemperature[2],
                            minTemperature[3], maxTemperature[3],
                            minTemperature[4], maxTemperature[4],
                            minTemperature[5], maxTemperature[5],
                            minTemperature[6], maxTemperature[6],
                            iconText[0], iconText[1], iconText[2], iconText[3],
                            iconText[4], iconText[5], iconText[6]);
                }

                else
                    System.out.println("JSON is null");

            } catch (JSONException e) {
               System.out.println("Cannot process JSON results");
            }
        }
    }

    public static JSONObject getWeatherJSON(String city, String ForecastDayNum){
        try {
            URL url = new URL(String.format(URL, city));

            HttpURLConnection connection = null;

            try {
               connection = (HttpURLConnection)url.openConnection();
                System.out.println("connection not null");
            }catch (Exception e){
                System.out.println("connection null");
            }

            int responseCode = connection.getResponseCode();
            String responseMessage = connection.getResponseMessage();

            String resp = responseCode + "\n" + responseMessage + "\n>";
            System.out.println(resp);

            System.out.println("connection "+connection);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp="";
            while((tmp=reader.readLine())!=null) {
                json.append(tmp).append("\n");
                System.out.println(tmp);
            }
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            return data;
        }catch(Exception e){
            return null;
        }
    }
}
