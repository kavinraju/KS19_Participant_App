package dpi.ks19.participantapp.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import dpi.ks19.participantapp.CallbackInterface.QrResponse;
import dpi.ks19.participantapp.LoginActivity;
import dpi.ks19.participantapp.Networking.ApiHelper;
import dpi.ks19.participantapp.R;

public class ProfileActivity extends AppCompatActivity implements QrResponse {

    ImageView qrCode;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    Button logOutBtn;
    TextView nameText, phoneText, hostelText, emailText, clgText;

    String fileName = "qr.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sharedPreferences = getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);

        nameText = findViewById(R.id.name_value);
        phoneText = findViewById(R.id.phone_value);
        hostelText = findViewById(R.id.hostel_value);
        emailText = findViewById(R.id.email_value);
        clgText = findViewById(R.id.college_value);
        showUserDetails();

        qrCode = findViewById(R.id.qr_code);
        logOutBtn = findViewById(R.id.log_out_btn);
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

        Log.d("FILE_PATH", getFilesDir().toString());

        if (!sharedPreferences.getBoolean(getString(R.string.is_qr_saved), false)) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            ApiHelper.getInstance(this).getQrCode(this);
        }else{
            readQRCode();
        }

    }

    @Override
    public void getQRCode(boolean success, Bitmap qr) {
        if (success) {
            progressDialog.cancel();
            saveQrCode(qr);
            qrCode.setImageBitmap(qr);
        } else {
            progressDialog.cancel();
            Toast.makeText(this, "Please try again", Toast.LENGTH_SHORT).show();
        }
    }

    private void showUserDetails(){
        nameText.setText(sharedPreferences.getString(getString(R.string.name),""));
        phoneText.setText(sharedPreferences.getString(getString(R.string.phone_number),""));
        if(sharedPreferences.getString(getString(R.string.hostel_accomodation),"").equals("1")){
            hostelText.setText(getString(R.string.yes));
        }else{
            hostelText.setText(getString(R.string.no));
        }

        emailText.setText(sharedPreferences.getString(getString(R.string.email),""));
        clgText.setText(sharedPreferences.getString(getString(R.string.college_name),""));
    }

    private void saveQrCode(Bitmap bitmap) {
        try {
            //convert bitmap to byte array
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100,byteArrayOutputStream);
            byte[] bitmapInArray = byteArrayOutputStream.toByteArray();

            //writing file to disk
            FileOutputStream fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            fileOutputStream.write(bitmapInArray);
            fileOutputStream.close();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(getString(R.string.is_qr_saved), true);
            editor.apply();
            Log.d("SAVING_BITMAP", "SUCCESS");
        } catch (IOException e) {
            Toast.makeText(this, "QR not saved please try again later", Toast.LENGTH_SHORT).show();
            Log.d("SAVING_BITMAP", e.toString());
        }
    }

    private void readQRCode(){
        try{
            //read from storage
            FileInputStream inputStream = openFileInput(fileName);
            //decode and display onto imageview
            qrCode.setImageBitmap(BitmapFactory.decodeStream(inputStream));
            Log.d("READ_BITMAP","SUCCESS");
        }catch (Exception e){
            Toast.makeText(this, "Error occured please try again later", Toast.LENGTH_SHORT).show();
            Log.d("READ_BITMAP","FAILED");
        }
    }

    private void logOut() {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(getString(R.string.login_or_not), false);
        editor.putBoolean(getString(R.string.is_qr_saved), false);
        deleteFile(fileName);
        editor.putString(getString(R.string.name),null);
        editor.putString(getString(R.string.phone_number),null);
        editor.putString(getString(R.string.hostel_accomodation),null);
        editor.putString(getString(R.string.email),null);
        editor.putString(getString(R.string.college_name),null);
        editor.clear();
        editor.apply();

        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
