package dpi.ks19.participantapp.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dpi.ks19.participantapp.R;

public class EventListAdapter extends RecyclerView.Adapter<EventListViewHolder> {

    ArrayList<String> eventNames, eventRules, eventDesc;

    public EventListAdapter(ArrayList<String>eventNames, ArrayList<String>eventDesc, ArrayList<String> eventRules){
        this.eventNames = eventNames;
        this.eventRules = eventRules;
        this.eventDesc = eventDesc;
    }

    @NonNull
    @Override
    public EventListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_row_item,viewGroup,false);
        return new EventListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final EventListViewHolder eventListViewHolder, int i) {

        eventListViewHolder.eventName.setText(eventNames.get(i));
        eventListViewHolder.eventRules.setText(eventRules.get(i));
        eventListViewHolder.eventDesc.setText(eventDesc.get(i));
        eventListViewHolder.rootLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!eventListViewHolder.isExpanded){
                    eventListViewHolder.eventDesc.setVisibility(View.VISIBLE);
                    eventListViewHolder.eventRules.setVisibility(View.VISIBLE);
                    eventListViewHolder.isExpanded = true;
                }else{
                    eventListViewHolder.eventDesc.setVisibility(View.GONE);
                    eventListViewHolder.eventRules.setVisibility(View.GONE);
                    eventListViewHolder.isExpanded = false;
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return eventNames.size();
    }
}
