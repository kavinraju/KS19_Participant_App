package dpi.ks19.participantapp.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import dpi.ks19.participantapp.R;

public class EventListViewHolder extends RecyclerView.ViewHolder {
    TextView eventName, eventRules, eventDesc;
    CardView rootLayout;

    public EventListViewHolder(@NonNull View itemView) {
        super(itemView);

        rootLayout = itemView.findViewById(R.id.root_layout);
        eventName = itemView.findViewById(R.id.event_name);
        eventRules = itemView.findViewById(R.id.event_rules);
        eventDesc = itemView.findViewById(R.id.event_desc);
    }
}
