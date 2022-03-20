package com.example.coronavirus;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class fetch2 extends AsyncTask<Void,Void,Void> {
    @Override
    protected Void doInBackground(Void... voids) {
        StringBuilder jsonresponse=new StringBuilder();
        try {
            URL url=new URL("https://corona.lmao.ninja/countries?sort=cases");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
            String result=br.readLine();
            while (result!=null){
                jsonresponse.append(result);
                result=br.readLine();
            }
            JSONArray jsonArray=new JSONArray(jsonresponse.toString());
            Fragmentlist.arrayList.clear();
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                JSONObject jsonObject1=jsonObject.getJSONObject("countryInfo");
                double latitude=jsonObject1.getDouble("lat");
                double longitude=jsonObject1.getDouble("long");
                String country=jsonObject.getString("country");
                int cases=jsonObject.getInt("cases");
                int deaths=jsonObject.getInt("deaths");
                int recovered=jsonObject.getInt("recovered");
                int todaycases=jsonObject.getInt("todayCases");
                int todaydeaths=jsonObject.getInt("todayDeaths");
                int active=jsonObject.getInt("active");
                int critical=jsonObject.getInt("critical");
                Fragmentlist.arrayList.add(new Item(country,cases,deaths,recovered,latitude,longitude,todaycases,todaydeaths,active,critical));
            }
            httpURLConnection.disconnect();


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        fetch.fm.beginTransaction().replace(R.id.framelayout,new Fragmentlist()).commit();
    }
}
