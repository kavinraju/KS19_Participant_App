package dpi.ks19.participantapp.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dpi.ks19.participantapp.CallbackInterface.OTPSent;
import dpi.ks19.participantapp.CallbackInterface.OTPInterface;
import dpi.ks19.participantapp.MainScreen;
import dpi.ks19.participantapp.Networking.ApiHelper;
import dpi.ks19.participantapp.R;

public class LoginPagerFragment extends Fragment implements OTPInterface, OtpCustomDialog.CustomDialogInterface, OTPSent {

    @BindView(R.id.et_login_email)
    EditText et_login_email;
    @BindView(R.id.btn_login)
    Button btn_login;

    OtpCustomDialog customDialog;
    ProgressDialog progressDialog;
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

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        return view;

    }

    @OnClick(R.id.btn_login)
    public void onClickLogin(View  view){

        if (et_login_email.getText().toString().isEmpty()){
            Snackbar.make(view, "Both the fields are required", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }else {
            //Call the end point here
            ApiHelper.getInstance(getActivity()).loginUser(et_login_email.getText().toString(), this);
            progressDialog.setMessage("Waiting for OTP...");
            progressDialog.show();
        }
    }

    @Override
    public void otpSent(boolean isSuccess, String msg) {
        Log.d("REACHED","OTP_SENT");
        progressDialog.cancel();
        if(isSuccess){
           createOTPDialog();
        }else{
            Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
        }
    }

    private void createOTPDialog(){
        customDialog = new OtpCustomDialog();
        customDialog.setCancelable(false);
        customDialog.setTargetFragment(this,1);
        customDialog.show(getFragmentManager(),"CustomDialog");
    }

    @Override
    public void isOTPVerified(boolean isVerified) {
        progressDialog.cancel();
        if(isVerified){
            //customDialog.getDialog().cancel();
            Intent intent = new Intent(getActivity(), MainScreen.class);
            intent.putExtra(getString(R.string.login_register_action),true);
            startActivity(intent);
            getActivity().finish();
        }else{
            Toast.makeText(getActivity(), "Incorrect otp", Toast.LENGTH_SHORT).show();
            //Snackbar.make(view, "Incorrect otp", Snackbar.LENGTH_SHORT);
        }
    }

    @Override
    public void getOTP(String otp) {
        progressDialog.setMessage("Verifying...");
        progressDialog.show();
        ApiHelper.getInstance(getActivity()).loginVerify(otp,this);
    }

    @OnClick(R.id.guest)
    public void onClickGuest(View  view){
        Intent intent = new Intent(getActivity(), MainScreen.class);
        intent.putExtra(getString(R.string.login_register_action),false);
        startActivity(intent);
    }

    @OnClick(R.id.privacy_text)
    public void privacyPolicyClicked(View v){
        String url = "https://github.com/droidLight/ks19_privacypolicy/blob/master/privacy_policy.md";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
