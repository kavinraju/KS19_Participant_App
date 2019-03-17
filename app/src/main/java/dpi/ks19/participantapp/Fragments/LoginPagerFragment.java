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
import dpi.ks19.participantapp.CallbackInterface.LoginCallback;
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
        //startActivity(new Intent(getActivity(), MainScreen.class));

        if (et_login_email.getText().toString().isEmpty()){
            Snackbar.make(view, "Both the fields are required", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
        }else {
            ApiHelper.getInstance(getActivity()).loginUser(et_login_email.getText().toString());
            showOTPDialog();
        }
    }

    private void showOTPDialog(){
        OtpCustomDialog customDialog = new OtpCustomDialog();
        customDialog.setCancelable(false);
        customDialog.setTargetFragment(this,0);
        customDialog.show(getFragmentManager(),"CustomDialog");
    }

    @Override
    public void isOTPVerified(boolean isVerified) {
        if(isVerified){
            Intent intent = new Intent(getActivity(), MainScreen.class);
            startActivity(intent);
        }
    }

    @Override
    public void getOTP(String otp) {
        if(otp != null){
            ApiHelper.getInstance(getActivity()).loginVerify(otp,this);
        }
    }
}
