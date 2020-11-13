package com.stambulo.githubclient.mvp.presenter;

import com.stambulo.githubclient.mvp.view.LoginView;

import moxy.MvpPresenter;

public class LoginPresenter extends MvpPresenter<LoginView> {
    private static String login;

    public void setLogin(String login){
        this.login = login;
    }

    public String getLogin(){
        return login;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().init();
        loadData();
    }


    private void loadData() {
        getViewState().showLogin(getLogin());
    }
}
