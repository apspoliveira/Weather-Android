package com.example.weather;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView cityField;
    TextView minTemperatureField1, minTemperatureField2, minTemperatureField3, minTemperatureField4, minTemperatureField5, minTemperatureField6, minTemperatureField7;
    TextView maxTemperatureField1, maxTemperatureField2, maxTemperatureField3, maxTemperatureField4, maxTemperatureField5, maxTemperatureField6, maxTemperatureField7;
    TextView weatherIcon1, weatherIcon2, weatherIcon3, weatherIcon4, weatherIcon5, weatherIcon6, weatherIcon7;
    Typeface weatherFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/weathericons-regular-webfont.ttf");
        System.out.println(weatherFont);

        cityField = (TextView)findViewById(R.id.city_field);
        minTemperatureField1 = (TextView)findViewById(R.id.minTemperature_field1);
        minTemperatureField2 = (TextView)findViewById(R.id.minTemperature_field2);
        minTemperatureField3 = (TextView)findViewById(R.id.minTemperature_field3);
        minTemperatureField4 = (TextView)findViewById(R.id.minTemperature_field4);
        minTemperatureField5 = (TextView)findViewById(R.id.minTemperature_field5);
        minTemperatureField6 = (TextView)findViewById(R.id.minTemperature_field6);
        minTemperatureField7 = (TextView)findViewById(R.id.minTemperature_field7);
        maxTemperatureField1 = (TextView)findViewById(R.id.maxTemperature_field1);
        maxTemperatureField2 = (TextView)findViewById(R.id.maxTemperature_field2);
        maxTemperatureField3 = (TextView)findViewById(R.id.maxTemperature_field3);
        maxTemperatureField4 = (TextView)findViewById(R.id.maxTemperature_field4);
        maxTemperatureField5 = (TextView)findViewById(R.id.maxTemperature_field5);
        maxTemperatureField6 = (TextView)findViewById(R.id.maxTemperature_field6);
        maxTemperatureField7 = (TextView)findViewById(R.id.maxTemperature_field7);
        weatherIcon1 = (TextView)findViewById(R.id.weather_icon1);
        weatherIcon2 = (TextView)findViewById(R.id.weather_icon2);
        weatherIcon3 = (TextView)findViewById(R.id.weather_icon3);
        weatherIcon4 = (TextView)findViewById(R.id.weather_icon4);
        weatherIcon5 = (TextView)findViewById(R.id.weather_icon5);
        weatherIcon6 = (TextView)findViewById(R.id.weather_icon6);
        weatherIcon7 = (TextView)findViewById(R.id.weather_icon7);
        weatherIcon1.setTypeface(weatherFont);
        weatherIcon2.setTypeface(weatherFont);
        weatherIcon3.setTypeface(weatherFont);
        weatherIcon4.setTypeface(weatherFont);
        weatherIcon5.setTypeface(weatherFont);
        weatherIcon6.setTypeface(weatherFont);
        weatherIcon7.setTypeface(weatherFont);

        Weather.placeIdTask asyncTask =new Weather.placeIdTask(new Weather.AsyncResponse() {
            public void processFinish(String weather_city, String weather_minTemperature1, String weather_maxTemperature1,
                                      String weather_minTemperature2, String weather_maxTemperature2,
                                      String weather_minTemperature3, String weather_maxTemperature3,
                                      String weather_minTemperature4, String weather_maxTemperature4,
                                      String weather_minTemperature5, String weather_maxTemperature5,
                                      String weather_minTemperature6, String weather_maxTemperature6,
                                      String weather_minTemperature7, String weather_maxTemperature7,
                                      String weather_iconText1, String weather_iconText2,
                                      String weather_iconText3, String weather_iconText4,
                                      String weather_iconText5, String weather_iconText6,
                                      String weather_iconText7) {

                cityField.setText(weather_city);
                minTemperatureField1.setText(weather_minTemperature1);
                minTemperatureField2.setText(weather_minTemperature2);
                minTemperatureField3.setText(weather_minTemperature3);
                minTemperatureField4.setText(weather_minTemperature4);
                minTemperatureField5.setText(weather_minTemperature5);
                minTemperatureField6.setText(weather_minTemperature6);
                minTemperatureField7.setText(weather_minTemperature7);
                maxTemperatureField1.setText(weather_maxTemperature1);
                maxTemperatureField2.setText(weather_maxTemperature2);
                maxTemperatureField3.setText(weather_maxTemperature3);
                maxTemperatureField4.setText(weather_maxTemperature4);
                maxTemperatureField5.setText(weather_maxTemperature5);
                maxTemperatureField6.setText(weather_maxTemperature6);
                maxTemperatureField7.setText(weather_maxTemperature7);
                weatherIcon1.setText(Html.fromHtml(weather_iconText1));
                weatherIcon2.setText(Html.fromHtml(weather_iconText2));
                weatherIcon3.setText(Html.fromHtml(weather_iconText3));
                weatherIcon4.setText(Html.fromHtml(weather_iconText4));
                weatherIcon5.setText(Html.fromHtml(weather_iconText5));
                weatherIcon6.setText(Html.fromHtml(weather_iconText6));
                weatherIcon7.setText(Html.fromHtml(weather_iconText7));
            }
        });

        System.out.println("Async execute");

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if(extras != null){
            String message = extras.getString("message");
            asyncTask.execute(message);
        }
        else
            asyncTask.execute("Coimbra");
    }
}
