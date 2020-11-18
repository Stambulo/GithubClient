package com.stambulo.githubclient.mvp.presenter;

import com.stambulo.githubclient.mvp.model.entity.GithubUserRepo;
import com.stambulo.githubclient.mvp.view.LoginView;

import moxy.MvpPresenter;

public class LoginPresenter extends MvpPresenter<LoginView> {
    private GithubUserRepo userRepo = new GithubUserRepo();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
        loadData();
    }

    private void loadData() {
        getViewState().showLogin(userRepo.getCurrentLogin());
    }
}
