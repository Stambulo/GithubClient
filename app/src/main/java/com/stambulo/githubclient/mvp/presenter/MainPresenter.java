package com.stambulo.githubclient.mvp.presenter;

import moxy.MvpPresenter;
import com.stambulo.githubclient.GithubApplication;
import com.stambulo.githubclient.mvp.view.MainView;
import com.stambulo.githubclient.navigation.Screens;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    Router router;

    public MainPresenter(){
        super();
        GithubApplication.INSTANCE.getAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        router.replaceScreen(new Screens.UsersScreen());
    }

    public void backClicked(){
        router.exit();
    }
}
