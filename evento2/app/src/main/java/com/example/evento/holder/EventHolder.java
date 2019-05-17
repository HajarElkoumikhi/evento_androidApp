package com.example.evento.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.evento.R;
import com.example.evento.models.Event;


public class EventHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private TextView description;
    private TextView city;
    private TextView country;


    public EventHolder(View itemView){
        super(itemView);

        this.title = (TextView) itemView.findViewById(R.id.eventTitleID);
        this.description = (TextView) itemView.findViewById(R.id.eventDescriptionID);
        this.city = (TextView) itemView.findViewById(R.id.eventCityID);
        this.country = (TextView) itemView.findViewById(R.id.eventCountryID);
    }

    public void updateUi(Event event){
        title.setText(event.getTitle());
        description.setText(event.getDescription());
        city.setText(event.getCity());
        country.setText(event.getCountry());

    }


}