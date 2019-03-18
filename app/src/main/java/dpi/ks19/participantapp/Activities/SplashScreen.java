package dpi.ks19.participantapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import dpi.ks19.participantapp.LoginActivity;
import dpi.ks19.participantapp.MainScreen;
import dpi.ks19.participantapp.R;

public class SplashScreen extends AppCompatActivity {

    ImageView splashLogo;
    SharedPreferences sharedPreferences;

    int WAIT_TIME = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPreferences = getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);

        splashLogo = findViewById(R.id.splash_screen_logo);
        Picasso.get().load(R.drawable.kslogo).into(splashLogo);
    }

    @Override
    protected void onStart() {
        super.onStart();

        final boolean isAlreadyLogin = sharedPreferences.getBoolean(getString(R.string.login_or_not), false);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if(isAlreadyLogin){
                    intent = new Intent(SplashScreen.this, MainScreen.class);
                }else{
                    intent = new Intent(SplashScreen.this, LoginActivity.class);
                }
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        },WAIT_TIME);
    }
}
