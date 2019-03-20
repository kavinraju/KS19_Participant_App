package dpi.ks19.participantapp.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import dpi.ks19.participantapp.Fragments.DayOneFrag;
import dpi.ks19.participantapp.Fragments.DayThreeFrag;
import dpi.ks19.participantapp.Fragments.DayTwoFrag;

public class DayAdapter extends FragmentPagerAdapter {

    public DayAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new DayOneFrag();
            case 1:
                return new DayTwoFrag();
            case 2:
                return new DayThreeFrag();
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Day 1";
            case 1:
                return "Day 2";
            case 2:
                return "Day 3";
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
