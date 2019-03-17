package dpi.ks19.participantapp;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class AboutKS extends AppCompatActivity {

    private String heading,content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        heading=getIntent().getStringExtra("heading");


        setContentView(R.layout.activity_about_ks);

        ImageView ks = findViewById(R.id.imageView);
        TextView heading = findViewById(R.id.heading);
        TextView content = findViewById(R.id.content);

        heading.setText(this.heading);

        if(this.heading.equals("SASTRA")) {
            ks.setImageDrawable(getDrawable(R.drawable.sastra_circle));
            content.setText(getText(R.string.about_sastra));
        }
        else if(this.heading.equals("KURUKSASTRA")){
            //ks.setImageDrawable(getDrawable(R.drawable.ks_circle));
            //added this to decrease load time
            Picasso.get().load(R.drawable.ks_circle).into(ks);
            content.setText(getText(R.string.about_ks));
        }
        else{
            ks.setImageDrawable(getDrawable(R.drawable.uphar_circle));
            content.setText(getText(R.string.about_uphar));
        }
    }
}
