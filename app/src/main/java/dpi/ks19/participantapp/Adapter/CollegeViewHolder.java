package dpi.ks19.participantapp.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import dpi.ks19.participantapp.R;

public class CollegeViewHolder extends RecyclerView.ViewHolder {

    TextView collegeName;
    public CollegeViewHolder(@NonNull View itemView) {
        super(itemView);

        collegeName = itemView.findViewById(R.id.college_name);
    }
}
