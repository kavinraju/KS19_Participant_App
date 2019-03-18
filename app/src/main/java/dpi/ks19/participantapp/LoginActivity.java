package dpi.ks19.participantapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dpi.ks19.participantapp.Fragments.LoginPagerFragment;
import dpi.ks19.participantapp.Fragments.RegisterPagerFragment;

public class LoginActivity extends AppCompatActivity {

    private int currentPage = 0;
    private int totalNumOfPages = 2;

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.iv_logo)
    ImageView iv_logo;
    @BindView(R.id.btn_login_head)
    Button btn_login_head;
    @BindView(R.id.btn_register_head)
    Button btn_register_head;

    SharedPreferences sharedPreferences;

    @Override
    protected void onStart() {
        super.onStart();
        //check whether already logged in
        //if yes direct to MainScreen else stay in current screen
        sharedPreferences = getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean(getString(R.string.login_or_not),false)){
            Intent intent = new Intent(this, MainScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        viewPager.setAdapter(new AuthenticationPagerAdapter(getSupportFragmentManager()));
        viewPager.setPageTransformer(true,new ZoomOutPageTransformer());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currentPage = position;
                setButtonBackground();
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        
    }

    @OnClick(R.id.btn_login_head)
    public void onClickLoginHead(View view) {
        setButtonBackground();
        currentPage = 0;
        viewPager.setCurrentItem(currentPage);
    }

    @OnClick(R.id.btn_register_head)
    public void onClickRegisterHead(View view) {
        setButtonBackground();
        currentPage = 1;
        viewPager.setCurrentItem(currentPage);
    }

    private void setButtonBackground() {
        if(currentPage == 0){
            btn_login_head.setBackground(getDrawable(R.drawable.btn_rect_round_login_selected));
            btn_register_head.setBackground(getDrawable(R.drawable.btn_rect_round_register));
            Log.d("setButtonBackground", "0");
        }else{
            if(currentPage == 1){
                btn_login_head.setBackground(getDrawable(R.drawable.btn_rect_round_login));
                btn_register_head.setBackground(getDrawable(R.drawable.btn_rect_round_register_selected));
                Log.d("setButtonBackground", "1");
            }
        }
        Log.d("setButtonBackground", "");
    }

    public class AuthenticationPagerAdapter extends FragmentStatePagerAdapter{

        AuthenticationPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i){
                case 0:
                    currentPage = 0;
                    setButtonBackground();
                    return LoginPagerFragment.newInstance();
                case 1:
                    currentPage = 1;
                    setButtonBackground();
                    return RegisterPagerFragment.newInstance();
                default:
                    currentPage = 0;
                    setButtonBackground();
                    return LoginPagerFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return totalNumOfPages;
        }
    }
    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) {
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else {
                view.setAlpha(0);
            }
        }
    }
}
