package com.stambulo.githubclient.mvp.presenter;

import com.stambulo.githubclient.mvp.model.Model;
import com.stambulo.githubclient.mvp.view.MainView;

import moxy.MvpPresenter;

public class Presenter extends MvpPresenter<MainView> {
    private Model model = new Model();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        // TODO: Nothing to do
    }

    public void counterOneClick() {
        getViewState().setButtonOneText(String.valueOf(model.next(0)));
    }

    public void counterTwoClick() {
        getViewState().setButtonTwoText(String.valueOf(model.next(1)));
    }

    public void counterThreeClick() {
        getViewState().setButtonThreeText(String.valueOf(model.next(2)));
    }
}
