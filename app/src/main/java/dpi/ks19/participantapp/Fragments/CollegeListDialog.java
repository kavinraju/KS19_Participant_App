package dpi.ks19.participantapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dpi.ks19.participantapp.Adapter.CollegeListAdapter;
import dpi.ks19.participantapp.R;

public class CollegeListDialog extends DialogFragment {

    RecyclerView collegeList;
    CollegeListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_college_list,container,false);

        collegeList = v.findViewById(R.id.college_list);
        adapter = new CollegeListAdapter(getActivity());
        collegeList.setLayoutManager(new LinearLayoutManager(getActivity()));
        collegeList.setAdapter(adapter);
        return v;
    }
}
