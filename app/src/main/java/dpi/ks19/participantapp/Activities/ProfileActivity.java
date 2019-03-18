package dpi.ks19.participantapp.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

import dpi.ks19.participantapp.CallbackInterface.QrResponse;
import dpi.ks19.participantapp.LoginActivity;
import dpi.ks19.participantapp.Networking.ApiHelper;
import dpi.ks19.participantapp.R;

public class ProfileActivity extends AppCompatActivity implements QrResponse {

    ImageView qrCode;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    Button logOutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        qrCode = findViewById(R.id.qr_code);
        logOutBtn = findViewById(R.id.log_out_btn);
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });



        sharedPreferences = getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);

        if(!sharedPreferences.getBoolean(getString(R.string.is_qr_saved),false)){
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            ApiHelper.getInstance(this).getQrCode(this);
        }

    }

    @Override
    public void getQRCode(boolean success, Bitmap qr) {
        if(success){
            progressDialog.cancel();
            saveQrCode(qr);
            qrCode.setImageBitmap(qr);
        }else{
            progressDialog.cancel();
            Toast.makeText(this, "Please try again", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveQrCode(Bitmap bitmap){
        try{
            FileOutputStream  fout = new FileOutputStream("qrcode.png");
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fout);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(getString(R.string.is_qr_saved),true);
            editor.apply();
            Log.d("SAVING_BITMAP","SUCCESS");
        }catch (IOException e){
            Toast.makeText(this, "QR not saved please try again later", Toast.LENGTH_SHORT).show();
            Log.d("SAVING_BITMAP",e.toString());
        }
    }

    private void logOut(){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(getString(R.string.login_or_not), false);
        editor.putBoolean(getString(R.string.is_qr_saved), false);
        editor.apply();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
