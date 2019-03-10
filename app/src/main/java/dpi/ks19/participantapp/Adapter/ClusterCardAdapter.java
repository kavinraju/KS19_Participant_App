package dpi.ks19.participantapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dpi.ks19.participantapp.Activities.EventListActivity;
import dpi.ks19.participantapp.R;

public class ClusterCardAdapter extends BaseAdapter {

    ArrayList<String>data;
    Context ctx;

    public ClusterCardAdapter(ArrayList<String> data, Context ctx){
        this.data = data;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cluster_card, parent, false);
        }
        ((TextView) v.findViewById(R.id.cluster_name)).setText(data.get(position));

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = (String)getItem(position);
                Log.i("MainActivity", item);
                Intent intent = new Intent(ctx,EventListActivity.class);
                intent.setAction(item);
                ctx.startActivity(intent);
            }
        });

        return v;
    }
}
