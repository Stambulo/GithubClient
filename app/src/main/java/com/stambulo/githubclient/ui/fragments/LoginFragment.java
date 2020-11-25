package com.stambulo.githubclient.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.stambulo.githubclient.R;
import com.stambulo.githubclient.mvp.presenter.LoginPresenter;
import com.stambulo.githubclient.mvp.view.LoginView;
import com.stambulo.githubclient.ui.BackButtonListener;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class LoginFragment extends MvpAppCompatFragment implements LoginView, BackButtonListener {
    private TextView login;

    @InjectPresenter
    LoginPresenter loginPresenter;

    public static LoginFragment getInstance(int data){
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        login = view.findViewById(R.id.tv_login);
        return view;
    }

    @Override
    public void init() {
    }

    @Override
    public void showLogin(String currentLogin) {
        login.setText(currentLogin);
    }

    @Override
    public boolean backPressed() {
        return loginPresenter.backPressed();
    }
}
