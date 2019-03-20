package dpi.ks19.participantapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import butterknife.BindView;
import butterknife.OnClick;
import dpi.ks19.participantapp.Adapter.CollegeListAdapter;
import dpi.ks19.participantapp.CallbackInterface.CollegeInterface;
import dpi.ks19.participantapp.CallbackInterface.RecyclerViewCallback;
import dpi.ks19.participantapp.Networking.ApiHelper;
import dpi.ks19.participantapp.R;

public class CollegeListActivity extends AppCompatActivity implements RecyclerViewCallback, CollegeInterface {

    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;

    RecyclerView collegeList;
    CollegeListAdapter adapter;
    ProgressDialog progressDialog;
    Button okBtn;
    AutoCompleteTextView actv;
    ArrayAdapter<String> arrayAdapter;

    int RESULT_CODE = 1200;
    String clgList[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_list);

        collegeList = findViewById(R.id.college_list);
        adapter = new CollegeListAdapter(this);
        collegeList.setLayoutManager(new LinearLayoutManager(this));
        collegeList.setAdapter(adapter);

        okBtn = findViewById(R.id.clg_ok_btn);

        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        ApiHelper.getInstance(this).getColleges(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    @Override
    public void selectedData(String data) {
        Log.d("CLG",data);
        Intent intent = new Intent();
        intent.putExtra(getString(R.string.college_name),data);
        setResult(RESULT_CODE, intent);
        finish();
    }

    @Override
    public void getCollegeList(JSONArray collegelist) {
        progressDialog.cancel();
        clgList = new String[collegelist.length()];
        try{
            for(int i=0; i < collegelist.length(); i++){
                clgList[i] = collegelist.getString(i);
            }
            adapter.setData(clgList);
            arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,clgList);
            actv = autoCompleteTextView;

            actv.setAdapter(arrayAdapter);
            actv.setTextColor(Color.WHITE);
            actv.setThreshold(1);//will start working from first character

        }catch (JSONException e){}
    }

    @OnClick(R.id.clg_ok_btn)
    public void okPressed(View v){
        String data = autoCompleteTextView.getText().toString().trim();
        Log.d("DATA_CLG",""+data);
        if(data.length() != 0){
            Intent intent = new Intent();
            intent.putExtra(getString(R.string.college_name),data);
            setResult(RESULT_CODE, intent);
            finish();
        }else{
            Toast.makeText(this, "Enter College Name", Toast.LENGTH_SHORT).show();
        }

    }
}
