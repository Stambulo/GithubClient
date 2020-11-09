package com.stambulo.githubclient;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.stambulo.githubclient.mvp.presenter.Presenter;
import com.stambulo.githubclient.mvp.view.MainView;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity implements MainView, View.OnClickListener {

    @InjectPresenter
    Presenter presenter;

    private Button buttonCounter1;
    private Button buttonCounter2;
    private Button buttonCounter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCounter1 = findViewById(R.id.btn_counter1);
        buttonCounter2 = findViewById(R.id.btn_counter2);
        buttonCounter3 = findViewById(R.id.btn_counter3);

        buttonCounter1.setOnClickListener(this);
        buttonCounter2.setOnClickListener(this);
        buttonCounter3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_counter1:
                presenter.counterOneClick();
                break;

            case R.id.btn_counter2:
                presenter.counterTwoClick();
                break;

            case R.id.btn_counter3:
                presenter.counterThreeClick();
                break;
        }
    }

    @Override
    public void setButtonOneText(String text) {
        buttonCounter1.setText(text);
    }

    @Override
    public void setButtonTwoText(String text) {
        buttonCounter2.setText(text);
    }

    @Override
    public void setButtonThreeText(String text) {
        buttonCounter3.setText(text);
    }
}