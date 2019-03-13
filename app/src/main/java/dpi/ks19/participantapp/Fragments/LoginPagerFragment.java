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
import dpi.ks19.participantapp.MainScreen;
import dpi.ks19.participantapp.R;

public class LoginPagerFragment extends Fragment {

    @BindView(R.id.et_login_email)
    EditText et_login_email;
    @BindView(R.id.et_login_password)
    EditText et_login_password;
    @BindView(R.id.btn_login)
    Button btn_login;

    public LoginPagerFragment() {
    }

    public static LoginPagerFragment newInstance() {
        LoginPagerFragment fragment = new LoginPagerFragment();
        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = view = inflater.inflate(R.layout.frag_login,container, false);
        ButterKnife.bind(this,view);
        return view;

    }

    @OnClick(R.id.btn_login)
    public void onClickLogin(View  view){
        startActivity(new Intent(getActivity(), MainScreen.class));
        if (et_login_email.getText().toString().isEmpty() || et_login_password.getText().toString().isEmpty()){
            Snackbar.make(view, "Both the fields are required", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }else {
            //Call the end point here
        }
    }

}
