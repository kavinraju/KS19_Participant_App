package dpi.ks19.participantapp.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import dpi.ks19.participantapp.Adapter.DayAdapter;
import dpi.ks19.participantapp.R;

public class ScheduleActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    AlertDialog alertDialog;
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        builder = new AlertDialog.Builder(this);        //Builder to display AlertDialog.
        builder.setTitle("Please check your network state!");
        builder.setIcon(R.drawable.no_internet_icon);
        builder.setMessage("Please turn on internet connection to continue!");
        builder.setCancelable(false);
        builder.setNegativeButton("SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ScheduleActivity.this.startActivity(new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS));
                dialog.dismiss();
            }
        });

        CheckNetworkConnection();   //To check for internet connection and display an AlertDialog.
        createFragment();           //To create fragments with view pager.
    }

    public void CheckNetworkConnection() {      //Checks for internet connectivity and displays an AlertDialog if not connected.
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnected();
        if (!isConnected) {
            alertDialog = builder.create();
            alertDialog.show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        alertDialog.dismiss();      //To dismiss the previous AlertDialog.
        CheckNetworkConnection();   //To check again for internet connection.
        createFragment();           //To create fragment with view pager on resuming the activity.
    }


    public void createFragment() {      //Creates tabLayout with vew pager for fragment.
       viewPager = findViewById(R.id.day_view_pager);
       DayAdapter adapter = new DayAdapter(getSupportFragmentManager());
       viewPager.setAdapter(adapter);

       tabLayout = findViewById(R.id.day_tab_layout);
       tabLayout.setupWithViewPager(viewPager);
   }
}
