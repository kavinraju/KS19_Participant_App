package dpi.ks19.participantapp;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutUs extends AppCompatActivity {


    ImageView imageView_graphic_designer, imageView_web_dev, imageView_app_dev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
//        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        imageView_graphic_designer = findViewById(R.id.imageView_graphic_designer);
        imageView_app_dev = findViewById(R.id.imageView_app_dev);
        imageView_web_dev = findViewById(R.id.imageView_web_dev);

//        imageView_graphic_designer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext() , AboutUs.class);
//                i.putExtra("title" ,"GRAPHIC DESIGNER");
//                startActivity(i);
//            }
//        });
    }

}

