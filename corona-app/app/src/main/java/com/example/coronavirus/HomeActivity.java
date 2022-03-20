package com.example.coronavirus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)!= PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(this, "Internet or Location service denied. Please toggle the permission in settings", Toast.LENGTH_LONG).show();
        }
        else {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
            if (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected()) {


                Toolbar toolbar = findViewById(R.id.toolbarhome);
                setSupportActionBar(toolbar);
                getSupportActionBar().setTitle("Coronavirus Tracker");
                getSupportActionBar().setLogo(R.drawable.ic_track);
                toolbar.setTitleTextColor(getResources().getColor(R.color.red));
                bottomNavigationView = findViewById(R.id.bottomnavigation);
                bottomNavigationView.setSelectedItemId(R.id.home_menu);

                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new FragmentLoading()).commit();
                fetch fet = new fetch(getSupportFragmentManager(), this);
                fet.execute();

                bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.home_menu:
                                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new FragmentData(HomeActivity.this, getSupportFragmentManager())).commit();
                                return true;
                            case R.id.list_menu:
                                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new FragmentLoading2()).commit();
                                return true;
                            default:
                                return false;
                        }

                    }
                });
            }
            else Toast.makeText(this, "Internet not available", Toast.LENGTH_LONG).show();
        }

    }
}
