package dpi.ks19.participantapp.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dpi.ks19.participantapp.CallbackInterface.EventsByCluster;
import dpi.ks19.participantapp.CallbackInterface.ScheduleInterface;
import dpi.ks19.participantapp.Fragments.DayOneFrag;
import dpi.ks19.participantapp.Fragments.DayThreeFrag;
import dpi.ks19.participantapp.Fragments.DayTwoFrag;
import dpi.ks19.participantapp.Model.EventClass;
import dpi.ks19.participantapp.Networking.ApiHelper;
import dpi.ks19.participantapp.R;

public class ClusterAdapter extends RecyclerView.Adapter<ClusterAdapter.ClusterHolder> implements EventsByCluster {

    //Cluster Adapter for Cluster RecyclerView.
    ArrayList<Integer> clusterIcon;
    Context mContext;
    ProgressDialog progressDialog;
    int day;
    private String cluster[];

    public ClusterAdapter(Context mContext, int d) {
        this.mContext = mContext;
        cluster = mContext.getResources().getStringArray(R.array.cluster_list);
        this.day = d;

        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);


        clusterIcon = new ArrayList<>();


        clusterIcon.add(R.drawable.logo_ks);
        clusterIcon.add(R.drawable.logo_informals);
        clusterIcon.add(R.drawable.logo_arts);
        clusterIcon.add(R.drawable.logo_studio);
        clusterIcon.add(R.drawable.logo_english_lits);
        clusterIcon.add(R.drawable.logo_hindi_lits);
        clusterIcon.add(R.drawable.logo_thandav);
        clusterIcon.add(R.drawable.logo_insiders);
        clusterIcon.add(R.drawable.logo_telugu_lits);
        clusterIcon.add(R.drawable.logo_sfh);
        clusterIcon.add(R.drawable.logo_tamil_sangam);
        clusterIcon.add(R.drawable.logo_smt);
        clusterIcon.add(R.drawable.logo_dpi);
    }

    @NonNull
    @Override
    public ClusterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cluster_card_schedule, parent, false);
        return new ClusterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ClusterHolder holder, final int listPosition) {
        final String clusterName = cluster[listPosition];

        holder.clusterName.setText(clusterName);
        holder.clusterIcon.setImageDrawable(mContext.getDrawable(clusterIcon.get(listPosition)));
        //Picasso.get().load(clusterIcon.get(listPosition)).into(holder.clusterIcon);
        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!holder.isExpanded){
                    holder.isExpanded = true;
                    progressDialog.show();
                    if(clusterName.equals(mContext.getString(R.string.tamil_sangam_display_text))){
                        ApiHelper.getInstance(mContext).getEventsForCluster(day, mContext.getString(R.string.tamil_sangam_query_text), holder, ClusterAdapter.this);
                    }else{
                        //use clusterName instead of "a"
                        ApiHelper.getInstance(mContext).getEventsForCluster(day, clusterName, holder, ClusterAdapter.this);
                    }

                }else{
                    holder.isExpanded = false;
                    holder.hideRecyclerView();
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return cluster.length;
    }


    public class ClusterHolder extends RecyclerView.ViewHolder {
        ConstraintLayout rootLayout;
        TextView clusterName;
        ImageView clusterIcon;
        RecyclerView scheduleRecyclerView;
        ScheduleAdapter adapter;
        boolean isExpanded;

        public ClusterHolder(@NonNull View itemView) {      //Holder for cluster item which holds the current recycler view in use.
            super(itemView);
            rootLayout = itemView.findViewById(R.id.root_layout);
            clusterName = itemView.findViewById(R.id.clusterName);
            clusterIcon = itemView.findViewById(R.id.clusterIcon);
            scheduleRecyclerView = itemView.findViewById(R.id.ScheduleRecyclerView);
            adapter = new ScheduleAdapter(mContext);
            scheduleRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            scheduleRecyclerView.setAdapter(adapter);
            isExpanded = false;
        }

        public void setRecyclerView(ArrayList<EventClass> data) {     //Sets the data, make the schedules visible and dismisses the AlertDialog.
            scheduleRecyclerView.setVisibility(View.VISIBLE);
            adapter.setData(data);
            progressDialog.dismiss();
        }

        public void hideRecyclerView() {        //Hides the recyclerView.
            scheduleRecyclerView.setVisibility(View.GONE);
        }

    }

    @Override
    public void getEventsByCluster(ArrayList<EventClass> data, ClusterHolder holder, boolean success) {
        if(success) {
            if(data.size() == 0){
                progressDialog.cancel();
                Toast.makeText(mContext, "No Events In This Cluster Today", Toast.LENGTH_SHORT).show();
            }else{
                holder.setRecyclerView(data);
            }

        }else{
            progressDialog.cancel();
            Toast.makeText(mContext, "Try Again", Toast.LENGTH_SHORT).show();
        }

    }
}

