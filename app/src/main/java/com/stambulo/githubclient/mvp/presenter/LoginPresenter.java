package com.stambulo.githubclient.mvp.presenter;

import com.stambulo.githubclient.GithubApplication;
import com.stambulo.githubclient.mvp.model.entity.GithubUserRepo;
import com.stambulo.githubclient.mvp.view.LoginView;

import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class LoginPresenter extends MvpPresenter<LoginView> {
    private final Router router = GithubApplication.getApplication().getRouter();
    private final GithubUserRepo userRepo = new GithubUserRepo();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
        loadData();
    }

    private void loadData() {
        getViewState().showLogin(userRepo.getCurrentLogin());
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }
}
