package com.example.lhz.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class weatherActivity extends Activity {
    private EditText editText;
    private Button button;
    private TextView tq_tianqi;
    private TextView tq_wendu;
    private TextView tq_fengli;
    private TextView tq_fengsu;
    private TextView tq_shidu;
    private TextView tq_visable;
    private TextView tq_cloud;
    private String API = "https://free-api.heweather.com/s6/weather/now?key=2ab935ee123b49c0be54bf1fbd8ddebb&location=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        editText =( EditText)findViewById(R.id.main_editView);
        button = (Button)findViewById(R.id.main_button);
        tq_tianqi = (TextView)findViewById(R.id.main_tianqi);
        tq_fengli = (TextView)findViewById(R.id.main_fengli);
        tq_wendu = (TextView)findViewById(R.id.main_wendu);
        tq_fengsu = (TextView)findViewById(R.id.main_fengsu);
        tq_shidu = (TextView)findViewById(R.id.main_shidu);
        tq_visable = (TextView)findViewById(R.id.main_visable);
        tq_cloud = (TextView)findViewById(R.id.main_cloud);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = editText.getText().toString();
                new MyWeather().execute(API + city);

            }
        });
    }


    class MyWeather extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuffer stringBuffer = null;

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = null;
                if (httpURLConnection.getResponseCode() == 200) {
                    inputStream = httpURLConnection.getInputStream();
                    //检测网络异常
                } else {
                    return "11";
                }
                InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(reader);
                stringBuffer = new StringBuffer();
                String timp = null;
                while ((timp = bufferedReader.readLine()) != null) {
                    stringBuffer.append(timp);
                }
                inputStream.close();
                reader.close();
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuffer.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("11")) {

                Toast.makeText(weatherActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
            }
            try {
                JSONObject object = new JSONObject(s);
                JSONObject object1 = object.getJSONArray("HeWeather6").getJSONObject(0);
                JSONObject basic = object1.getJSONObject("basic");
                JSONObject now = object1.getJSONObject("now");
                String city = basic.getString("location");
                String tianqi = now.getString("cond_txt");
                String wendu = now.getString("tmp");
                String fengli = now.getString("wind_dir");
                String qiangdu = now.getString("wind_sc");
                String fengsu= now.getString("wind_spd");
                String shidu= now.getString("hum");
                String visable= now.getString("vis");
                String cloud= now.getString("cloud");
                tq_tianqi.setText(city );
                tq_wendu.setText(wendu + "℃");
                tq_fengli.setText(fengli + qiangdu + "级");
                tq_fengsu.setText(fengsu+"公里/小时");
                tq_shidu.setText(shidu+"%");
                tq_visable.setText(visable+"公里");
                tq_cloud.setText(cloud+"%");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
}