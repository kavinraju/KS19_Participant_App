package dpi.ks19.participantapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dpi.ks19.participantapp.CallbackInterface.CustomDialogInterface;
import dpi.ks19.participantapp.CallbackInterface.OTPInterface;
import dpi.ks19.participantapp.Networking.ApiHelper;
import dpi.ks19.participantapp.R;

public class RegisterPagerFragment extends Fragment implements OTPInterface, CustomDialogInterface {

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

    public RegisterPagerFragment() {
    }

    public static RegisterPagerFragment newInstance(){
        return new RegisterPagerFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_register, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.btn_register)
    public void onClickRegister(View  view){

        if (et_register_name.getText().toString().isEmpty() || et__register_phoneNumber.getText().toString().isEmpty() ||
        et_register_email.getText().toString().isEmpty() || et_register_password.getText().toString().isEmpty() ||
        et_register_college_name.getText().toString().isEmpty()){

            Snackbar.make(view, "All the fields are required", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }else {
            //calling the endpoints for sending OTP to email
            ApiHelper.getInstance(getActivity()).generateOTP(et_register_email.getText().toString().trim());

            //create a OTP dialog, send the details to the OtpCustomDialog
            createOTPDialog();
        }
    }

    private void createOTPDialog(){
        OtpCustomDialog customDialog = new OtpCustomDialog();
        customDialog.setCancelable(false);
        customDialog.show(getFragmentManager(),"CustomDialog");
    }

    @Override
    public void isOTPVerified(boolean isVerified) {
        if(isVerified){
            //OTP is verified proceed to user registration
            ApiHelper.getInstance(getActivity()).registerUser(et_register_name.getText().toString().trim(),
                    et__register_phoneNumber.getText().toString().trim(),
                    et_register_college_name.getText().toString().trim(),
                    et_register_ambassador_id.getText().toString().trim(),
                    "TEST_VALUE");
        }
    }

    //callback from the otp dialog
    @Override
    public void getOtp(String otp) {
        ApiHelper.getInstance(getActivity()).verifyOTP(otp,this);
    }
}
