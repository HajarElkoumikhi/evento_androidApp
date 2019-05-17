package com.example.evento.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.evento.R;
import com.example.evento.holder.EventHolder;
import com.example.evento.models.Event;

import java.util.ArrayList;


public class EventAdapter extends RecyclerView.Adapter<EventHolder> {


    private ArrayList<Event> events;
    public EventAdapter(ArrayList<Event> events){
        this.events = events;
    }


    @Override
    public void onBindViewHolder(EventHolder holder, int position){
        final Event event = events.get(position);
        holder.updateUi(event);

    }



    @Override
    public int getItemCount(){
        return events.size();
    }

    @Override
    public EventHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View eventCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_event, parent,false);
        return new EventHolder(eventCard);
    }

}