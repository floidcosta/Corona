package com.example.coronavirus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;

public class details2 extends AppCompatActivity {
    private static DecimalFormat df = new DecimalFormat("0.00");

    TextView country,cases,deaths,recovered,todaydeaths,todaycases,active,critical,cpm,dpm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);
        SupportMapFragment supportMapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                LatLng latLng=new LatLng(getIntent().getDoubleExtra("Latitude",0),getIntent().getDoubleExtra("Longitude",0));
                googleMap.addMarker(new MarkerOptions().position(latLng).title(getIntent().getStringExtra("Country")).snippet("Cases: "+getIntent().getIntExtra("Cases",0)));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        });

        country=findViewById(R.id.changecountrydetail);
        country.setText(getIntent().getStringExtra("Country"));


        cases=findViewById(R.id.changeCasesDetail);
        cases.setText(""+getIntent().getIntExtra("Cases",0));

        deaths=findViewById(R.id.changeDeathsDetail);
        deaths.setText(""+getIntent().getIntExtra("Deaths",0));

        recovered=findViewById(R.id.changeRecoveredDetail);
        recovered.setText(""+getIntent().getIntExtra("Recovered",0));

        todaydeaths=findViewById(R.id.changeTodayDeathsDetail);
        todaydeaths.setText(""+getIntent().getIntExtra("TodayDeaths",0));

        todaycases=findViewById(R.id.changeTodayCasesDetail);
        todaycases.setText(""+getIntent().getIntExtra("TodayCases",0));

        active=findViewById(R.id.changeActiveCasesDetail);
        active.setText(""+getIntent().getIntExtra("Active",0));

        critical=findViewById(R.id.changeCriticalDetail);
        critical.setText(""+getIntent().getIntExtra("Critical",0));

        double dpercentage=((double)getIntent().getIntExtra("Deaths",0)/(double)getIntent().getIntExtra("Cases",0))*100;
        double rpercentage=((double)getIntent().getIntExtra("Recovered",0)/(double)getIntent().getIntExtra("Cases",0))*100;
        cpm=findViewById(R.id.changeCasespmDetail);
        cpm.setText(df.format(rpercentage)+"%");

        dpm=findViewById(R.id.changeDeathspmDetail);
        dpm.setText(df.format(dpercentage)+"%");

    }
}
