package com.example.coronavirus;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public ArrayList<Item> fakeArray;

    public MyAdapter(ArrayList<Item> arrayList){
        fakeArray=arrayList;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView country,cases,deaths,recovered;
        public CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cases=itemView.findViewById(R.id.cases);
            country=itemView.findViewById(R.id.country);
            deaths=itemView.findViewById(R.id.deaths);
            recovered=itemView.findViewById(R.id.recovered);
            cardView=itemView.findViewById(R.id.cardvvv);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        MyViewHolder mvh=new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Item item=fakeArray.get(position);
        holder.country.setText(item.getCountry());
        holder.cases.setText(""+item.getCases());
        holder.deaths.setText(""+item.getDeaths());
        holder.recovered.setText(""+item.getRecovered());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(fetch.mContext,details2.class);
                intent.putExtra("Country",item.getCountry());
                intent.putExtra("Cases",item.getCases());
                intent.putExtra("Deaths",item.getDeaths());
                intent.putExtra("Recovered",item.getRecovered());
                intent.putExtra("Latitude",item.getLatitude());
                intent.putExtra("Longitude",item.getLongitude());
                intent.putExtra("TodayDeaths",item.getTodayDeaths());
                intent.putExtra("TodayCases",item.getTodayCases());
                intent.putExtra("Active",item.getActive());
                intent.putExtra("Critical",item.getCritical());

                fetch.mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fakeArray.size();
    }


}
