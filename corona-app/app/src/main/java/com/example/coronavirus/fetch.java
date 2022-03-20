package com.example.coronavirus;

import android.content.Context;
import android.os.AsyncTask;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class fetch extends AsyncTask<Void,Void,Void> {
    public static FragmentManager fm;
    public static Context mContext;
    fetch(FragmentManager f, Context context){
        fm=f;
        mContext=context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        StringBuilder jsonresponse=new StringBuilder();
        try {
            URL url=new URL("https://corona.lmao.ninja/all");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
            String result=br.readLine();
            while (result!=null){
                jsonresponse.append(result);
                result=br.readLine();
            }
            JSONObject jsonObject=new JSONObject(jsonresponse.toString());
            FragmentData.totalCases=jsonObject.getInt("cases");
            FragmentData.totalDeaths=jsonObject.getInt("deaths");
            FragmentData.totalRecovered=jsonObject.getInt("recovered");
            httpURLConnection.disconnect();


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        fm.beginTransaction().replace(R.id.framelayout,new FragmentData(mContext,fm)).commit();
    }
}
