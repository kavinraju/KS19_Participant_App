package dpi.ks19.participantapp.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dpi.ks19.participantapp.Adapter.DayAdapter;
import dpi.ks19.participantapp.R;

public class ScheduleActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        viewPager = findViewById(R.id.day_view_pager);
        DayAdapter adapter = new DayAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout = findViewById(R.id.day_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
