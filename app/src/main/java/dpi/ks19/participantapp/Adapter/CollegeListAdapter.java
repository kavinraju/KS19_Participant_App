package dpi.ks19.participantapp.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;

import dpi.ks19.participantapp.CallbackInterface.CollegeInterface;
import dpi.ks19.participantapp.CallbackInterface.RecyclerViewCallback;
import dpi.ks19.participantapp.Networking.ApiHelper;
import dpi.ks19.participantapp.R;

public class CollegeListAdapter extends RecyclerView.Adapter<CollegeViewHolder> implements CollegeInterface {

    JSONArray data;
    RecyclerViewCallback callback;
    ProgressDialog progressDialog;

    public CollegeListAdapter(Context ctx){
        data = new JSONArray();
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        ApiHelper.getInstance(ctx).getColleges(this);
        callback = (RecyclerViewCallback)ctx;
    }

    @NonNull
    @Override
    public CollegeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.college_row_item, viewGroup,false);

        return new CollegeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollegeViewHolder collegeViewHolder, int i) {
        try{
            final String clgName = data.getString(i);
            collegeViewHolder.collegeName.setText(clgName);
            collegeViewHolder.collegeName.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    callback.selectedData(clgName);
                }
            });
        }catch (Exception e){}
    }

    @Override
    public int getItemCount() {
        return data.length();
    }

    @Override
    public void getCollegeList(JSONArray collegelist) {
        progressDialog.cancel();
        data = collegelist;
        notifyDataSetChanged();
    }
}
