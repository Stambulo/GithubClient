package com.stambulo.githubclient.mvp.presenter;

import android.util.Log;

import com.stambulo.githubclient.mvp.model.Model;
import com.stambulo.githubclient.mvp.view.MainView;

import moxy.MvpPresenter;

public class Presenter extends MvpPresenter<MainView> {
    private Model model = new Model();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Log.d("--->", "Presenter  OnFirstViewAttach");
        // TODO: Nothing to do
    }

    public void counterOneClick() {
        getViewState().setButtonOneText(String.valueOf(model.next(0)));
        Log.d("--->", "Presenter  counterOneClick()");
    }

    public void counterTwoClick() {
        getViewState().setButtonTwoText(String.valueOf(model.next(1)));
        Log.d("--->", "Presenter  counterTwoClick()");
    }

    public void counterThreeClick() {
        getViewState().setButtonThreeText(String.valueOf(model.next(2)));
        Log.d("--->", "Presenter  counterThreeClick()");
    }
}
