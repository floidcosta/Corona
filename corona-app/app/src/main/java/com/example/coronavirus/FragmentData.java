package com.example.coronavirus;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class FragmentData extends Fragment {
    public static final String TAG="FragmentdATA";
    public static int totalCases;
    public static int totalDeaths;
    public static int totalRecovered;


    FragmentManager fm;
    Context mcontext;
    TextView cases,deaths,recovered;

    FragmentData(Context c,FragmentManager f){
        mcontext=c;
        fm=f;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragmentdata,container,false);


        cases=v.findViewById(R.id.changecases);
        cases.setText(""+totalCases);

        deaths=v.findViewById(R.id.changedeaths);
        deaths.setText(""+totalDeaths);

        recovered=v.findViewById(R.id.changerecovered);
        recovered.setText(""+totalRecovered);

        if (totalCases==0){
            Toast.makeText(mcontext, "Error connecting to Server\nTry Reloading App", Toast.LENGTH_LONG).show();
        }

        ImageView imageView=v.findViewById(R.id.refresh);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.framelayout,new FragmentLoading()).commit();
                Toast.makeText(mcontext, "refreshed", Toast.LENGTH_SHORT).show();
                fetch fet=new fetch(fm,mcontext);
                fet.execute();
            }
        });
        return v;
    }
}
