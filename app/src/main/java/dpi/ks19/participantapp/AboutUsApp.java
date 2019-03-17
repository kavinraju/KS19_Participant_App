package dpi.ks19.participantapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dpi.ks19.participantapp.Adapter.GreenAdapter;

public class AboutUsApp extends AppCompatActivity {

    private String heading,content;
    private static RecyclerView NameList ;
    private GreenAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        heading=getIntent().getStringExtra("title");
//        content=getIntent().getStringExtra("content");

        setContentView(R.layout.activity_about_us_app);

//        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        ImageView ks = findViewById(R.id.imageView);
        TextView heading = findViewById(R.id.heading);
        //commented because 'content' is not use and has duplicate id
        //TextView content = findViewById(R.id.content);
        heading.setText(this.heading);

        NameList = (RecyclerView) findViewById(R.id.content);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        NameList.setLayoutManager(layoutManager);
        NameList.setHasFixedSize(true);

//        content.setText(this.content);
        if(this.heading.equals("APP DEVELOPER")) {
//            ks.setImageDrawable(getDrawable(R.drawable.cover_app_dev));
            mAdapter = new GreenAdapter(getBaseContext(),"android");
        }
        else if(this.heading.equals("WEB DEVELOPER")){
//            ks.setImageDrawable(getDrawable(R.drawable.cover_web_dev));
            mAdapter = new GreenAdapter(getBaseContext(),"web");
        }
        else{
//            ks.setImageDrawable(getDrawable(R.drawable.cover_graphic));
            mAdapter = new GreenAdapter(getBaseContext(),"graphic");
        }

        NameList.setAdapter(mAdapter);
    }
}