package dpi.ks19.participantapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dpi.ks19.participantapp.Activities.ClusterCardsActivity;
import dpi.ks19.participantapp.CallbackInterface.OTPInterface;
import dpi.ks19.participantapp.MainScreen;
import dpi.ks19.participantapp.Networking.ApiHelper;
import dpi.ks19.participantapp.R;

public class LoginPagerFragment extends Fragment implements OTPInterface, OtpCustomDialog.CustomDialogInterface{

    @BindView(R.id.et_login_email)
    EditText et_login_email;
    @BindView(R.id.et_login_password)
    EditText et_login_password;
    @BindView(R.id.btn_login)
    Button btn_login;

    View view;
    public LoginPagerFragment() {
    }

    public static LoginPagerFragment newInstance() {
        LoginPagerFragment fragment = new LoginPagerFragment();
        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_login,container, false);
        ButterKnife.bind(this,view);
        return view;

    }

    @OnClick(R.id.btn_login)
    public void onClickLogin(View  view){

        if (et_login_email.getText().toString().isEmpty()){
            Snackbar.make(view, "Both the fields are required", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }else {
            //Call the end point here
            ApiHelper.getInstance(getActivity()).loginUser(et_login_email.getText().toString());

            //calling dialog to enter OTP
            createOTPDialog();
        }
    }

    private void createOTPDialog(){
        OtpCustomDialog customDialog = new OtpCustomDialog();
        customDialog.setCancelable(false);
        customDialog.setTargetFragment(this,1);
        customDialog.show(getFragmentManager(),"CustomDialog");

    }

    @Override
    public void isOTPVerified(boolean isVerified) {
        if(isVerified){
            startActivity(new Intent(getActivity(), MainScreen.class));
        }else{
            Snackbar.make(view, "Incorrect otp", Snackbar.LENGTH_SHORT);
        }
    }

    @Override
    public void getOTP(String otp) {
        ApiHelper.getInstance(getActivity()).loginVerify(otp,this);
    }

    @OnClick(R.id.guest)
    public void onClickGuest(View  view){

        Boolean value=true;
        Intent i = new Intent(getActivity(), MainScreen.class);
        i.putExtra("key",value);
        startActivity(i);
    }

}
