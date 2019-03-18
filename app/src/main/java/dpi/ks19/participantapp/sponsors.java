package dpi.ks19.participantapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class sponsors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);

        WebView browser = (WebView) findViewById(R.id.webview);
        browser.loadUrl("http://www.kuruksastra.in/sponsors.php");
    }
}
