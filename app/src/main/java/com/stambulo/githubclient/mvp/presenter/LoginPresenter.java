package com.stambulo.githubclient.mvp.presenter;

import com.stambulo.githubclient.mvp.model.entity.GithubUser;
import com.stambulo.githubclient.mvp.model.entity.GithubUserRepo;
import com.stambulo.githubclient.mvp.view.LoginView;

import moxy.MvpPresenter;

public class LoginPresenter extends MvpPresenter<LoginView> {
    private static int selectedUser;

    private GithubUserRepo githubUserRepo = new GithubUserRepo();
    private GithubUser githubUser = new GithubUser();

    public void setSelectedUser(int newSelectedUser){
        selectedUser = newSelectedUser;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().init();
        loadData();
    }


    private void loadData() {
        githubUser = githubUserRepo.getLogin(selectedUser);
        getViewState().showLogin(githubUser.getLogin());
    }
}
