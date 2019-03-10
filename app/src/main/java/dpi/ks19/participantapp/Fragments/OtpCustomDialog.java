package dpi.ks19.participantapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dpi.ks19.participantapp.R;

public class OtpCustomDialog extends DialogFragment {

    @BindView(R.id.et_otp)
    EditText et_otp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_otp_dialog,container, false);
        ButterKnife.bind(this,view);
        getDialog().setTitle("Enter the OTP");

        return view;
    }

    @OnClick(R.id.btn_otp_next)
    public void onClickOtpNext(View view){
        // Check the otp and call the endpoint

    }

    @OnClick(R.id.btn_otp_cancel)
    public void onClickOtpCancel(View view){
        getDialog().cancel();
    }
}
