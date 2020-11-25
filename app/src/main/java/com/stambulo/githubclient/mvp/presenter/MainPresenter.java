package com.stambulo.githubclient.mvp.presenter;

import moxy.MvpPresenter;
import com.stambulo.githubclient.GithubApplication;
import com.stambulo.githubclient.mvp.view.MainView;
import com.stambulo.githubclient.navigation.Screens;
import ru.terrakok.cicerone.Router;

public class MainPresenter extends MvpPresenter<MainView> {
    private final Router router = GithubApplication.getApplication().getRouter();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        router.replaceScreen(new Screens.UserScreen());
    }

    public void backClicked(){
        router.exit();
    }
}
