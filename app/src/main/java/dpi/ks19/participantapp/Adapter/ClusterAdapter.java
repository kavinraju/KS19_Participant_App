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

import java.util.ArrayList;

import dpi.ks19.participantapp.CallbackInterface.ScheduleInterface;
import dpi.ks19.participantapp.Fragments.DayOneFrag;
import dpi.ks19.participantapp.Fragments.DayThreeFrag;
import dpi.ks19.participantapp.Fragments.DayTwoFrag;
import dpi.ks19.participantapp.Model.EventClass;
import dpi.ks19.participantapp.R;

public class ClusterAdapter extends RecyclerView.Adapter<ClusterAdapter.ClusterHolder> implements ScheduleInterface {
    //Cluster Adapter for Cluster RecyclerView.
    ArrayList<Drawable> clusterIcon = new ArrayList<>();
    Context mContext;
    ProgressDialog progressDialog;
    int day;
    private ClusterAdapter callback;
    private String cluster[];

    public ClusterAdapter(Context mContext, int d) {        //Constructor initializes mContext, callback and data for clusters as it is Static.
        this.mContext = mContext;
        this.callback = this;
        cluster = mContext.getResources().getStringArray(R.array.cluster_list);
        day = d;

        progressDialog = new ProgressDialog(mContext);      //Initializes progress Dialog When loading for data.
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Downloading Data.....Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);

        clusterIcon.add(new ColorDrawable(Color.parseColor("#ffffff")));
        clusterIcon.add(new ColorDrawable(Color.parseColor("#ffff00")));
        clusterIcon.add(new ColorDrawable(Color.parseColor("#ff00ff")));
        clusterIcon.add(new ColorDrawable(Color.parseColor("#00ffff")));
        clusterIcon.add(new ColorDrawable(Color.parseColor("#ff0000")));
        clusterIcon.add(new ColorDrawable(Color.parseColor("#00ff00")));
        clusterIcon.add(new ColorDrawable(Color.parseColor("#0000ff")));
        clusterIcon.add(new ColorDrawable(Color.parseColor("#ccccff")));
        clusterIcon.add(new ColorDrawable(Color.parseColor("#000000")));
        clusterIcon.add(new ColorDrawable(Color.parseColor("#2cd84a")));
        clusterIcon.add(new ColorDrawable(Color.parseColor("#c34af7")));
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
        holder.clusterIcon.setImageDrawable(clusterIcon.get(listPosition));
        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                if (day == 22)
                    DayOneFrag.getInstance().getSchedule(getQueryWord(clusterName), callback, holder);
                else if (day == 23)
                    DayTwoFrag.getInstance().getSchedule(getQueryWord(clusterName), callback, holder);
                else
                    DayThreeFrag.getInstance().getSchedule(getQueryWord(clusterName), callback, holder);
            }
        });
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ClusterHolder holder) {
        super.onViewDetachedFromWindow(holder);     //Hides the Schedule if the item is detached.
        holder.hideRecyclerView();
    }

    private String getQueryWord(String clusterName) {
        //Returns the query word.
        if (clusterName.equals(cluster[0])) {
            return cluster[0];
        } else if (clusterName.equals(cluster[1])) {
            return cluster[1];
        } else if (clusterName.equals(cluster[2])) {
            return cluster[2];
        } else if (clusterName.equals(cluster[3])) {
            return cluster[3];
        } else if (clusterName.equals(cluster[4])) {
            return cluster[4];
        } else if (clusterName.equals(cluster[5])) {
            return cluster[5];
        } else if (clusterName.equals(cluster[6])) {
            return cluster[6];
        } else if (clusterName.equals(cluster[7])) {
            return cluster[7];
        } else if (clusterName.equals(cluster[8])) {
            return cluster[8];
        } else if (clusterName.equals(cluster[9])) {
            return cluster[9];
        } else if (clusterName.equals(cluster[10])) {
            return cluster[10];
        } else if (clusterName.equals(cluster[11])) {
            return cluster[11];
        } else {
            return clusterName;
        }
    }


    @Override
    public int getItemCount() {
        return clusterIcon.size();
    }       //Gets the total number of items.

    @Override
    public void callback(String querySchedule) {

    }

    @Override
    public void setScheduleData(ArrayList<EventClass> data, ClusterHolder clusterHolder, boolean isEmpty) {   //To set Schedule data
        if (isEmpty) {
            progressDialog.dismiss();
            Toast.makeText(mContext, "Sorry! Unfortunate Error occurred.", Toast.LENGTH_SHORT).show();
        } else {
            clusterHolder.setRecyclerView(data);        //To set recycler view for schedule.
        }
    }

    public class ClusterHolder extends RecyclerView.ViewHolder {
        ConstraintLayout rootLayout;
        TextView clusterName;
        ImageView clusterIcon;
        RecyclerView scheduleRecyclerView;
        ScheduleAdapter adapter;

        public ClusterHolder(@NonNull View itemView) {      //Holder for cluster item which holds the current recycler view in use.
            super(itemView);
            rootLayout = itemView.findViewById(R.id.root_layout);
            clusterName = itemView.findViewById(R.id.clusterName);
            clusterIcon = itemView.findViewById(R.id.clusterIcon);
            scheduleRecyclerView = itemView.findViewById(R.id.ScheduleRecyclerView);
            adapter = new ScheduleAdapter(mContext);
            scheduleRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            scheduleRecyclerView.setAdapter(adapter);
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
}

