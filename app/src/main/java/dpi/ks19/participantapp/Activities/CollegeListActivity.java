package dpi.ks19.participantapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import dpi.ks19.participantapp.Adapter.CollegeListAdapter;
import dpi.ks19.participantapp.CallbackInterface.RecyclerViewCallback;
import dpi.ks19.participantapp.R;

public class CollegeListActivity extends AppCompatActivity implements RecyclerViewCallback {

    RecyclerView collegeList;
    CollegeListAdapter adapter;

    int RESULT_CODE = 1200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_list);

        collegeList = findViewById(R.id.college_list);
        adapter = new CollegeListAdapter(this);
        collegeList.setLayoutManager(new LinearLayoutManager(this));
        collegeList.setAdapter(adapter);
    }

    @Override
    public void selectedData(String data) {
        Intent intent = new Intent();
        intent.putExtra(getString(R.string.college_name),data);
        setResult(RESULT_CODE, intent);
        finish();
    }
}
