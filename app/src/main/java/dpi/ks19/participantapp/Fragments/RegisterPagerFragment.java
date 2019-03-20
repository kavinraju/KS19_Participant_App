package dpi.ks19.participantapp.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dpi.ks19.participantapp.Activities.CollegeListActivity;
import dpi.ks19.participantapp.CallbackInterface.OTPInterface;
import dpi.ks19.participantapp.MainScreen;
import dpi.ks19.participantapp.Networking.ApiHelper;
import dpi.ks19.participantapp.R;

public class RegisterPagerFragment extends Fragment implements OTPInterface, OtpCustomDialog.CustomDialogInterface {

    @BindView(R.id.et_register_name)
    EditText et_register_name;
    @BindView(R.id.et__register_phoneNumber)
    EditText et__register_phoneNumber;
    @BindView(R.id.et_register_email)
    EditText et_register_email;
    @BindView(R.id.et_register_password)
    EditText et_register_password;
    @BindView(R.id.et_register_college_name)
    EditText et_register_college_name;
    @BindView(R.id.et_register_ambassador_id)
    EditText et_register_ambassador_id;

    View v;
    ProgressDialog progressDialog;
    int COLLEGE_NAME =120;

    public RegisterPagerFragment() {
    }

    public static RegisterPagerFragment newInstance(){
        return new RegisterPagerFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frag_register, container, false);
        ButterKnife.bind(this,v);

        et_register_college_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), CollegeListActivity.class),COLLEGE_NAME);
            }
        });
        return v;
    }


    @OnClick(R.id.btn_register)
    public void onClickRegister(View  view){

        if (et_register_name.getText().toString().isEmpty() || et__register_phoneNumber.getText().toString().isEmpty() ||
        et_register_email.getText().toString().isEmpty() ||
        et_register_college_name.getText().toString().isEmpty()){

            Snackbar.make(view, "All the fields are required", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }else {
            //calling the endpoints for sending OTP to email
            ApiHelper.getInstance(getActivity()).generateOTP(et_register_email.getText().toString().trim());

            //create a OTP dialog to enter the otp
            createOTPDialog();
        }
    }

    private void createOTPDialog(){
        OtpCustomDialog customDialog = new OtpCustomDialog();
        customDialog.setCancelable(false);
        customDialog.setTargetFragment(this,0);
        customDialog.show(getFragmentManager(),"CustomDialog");

    }

    @Override
    public void isOTPVerified(boolean isVerified) {
        progressDialog.cancel();
        if(isVerified){
            //OTP is verified proceed to user registration
            ApiHelper.getInstance(getActivity()).registerUser(et_register_name.getText().toString().trim(),
                    et_register_password.getText().toString().trim(),
                    et__register_phoneNumber.getText().toString().trim(),
                    et_register_college_name.getText().toString().trim(),
                    et_register_ambassador_id.getText().toString().trim(),
                    true);
            Toast.makeText(getActivity(), "Registered Successfully Please Login to proceed.", Toast.LENGTH_SHORT).show();
        }else{
            Snackbar.make(v, "Incorrect Otp", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    //callback from the otp dialog
    @Override
    public void getOTP(String otp) {
        Log.d("GOT OTP",otp);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        //verify the OTP entered
        ApiHelper.getInstance(getActivity()).verifyOTP(otp,this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == COLLEGE_NAME && data != null){
            et_register_college_name.setText(data.getStringExtra(getString(R.string.college_name)));
        }else{
            Snackbar.make(v, "Choose your College", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}
