package com.stambulo.githubclient.mvp.presenter;

import com.stambulo.githubclient.GithubApplication;
import com.stambulo.githubclient.mvp.view.ForkView;

import io.reactivex.rxjava3.core.Scheduler;
import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class ForkPresenter extends MvpPresenter<ForkView> {
    private final Router router = GithubApplication.getApplication().getRouter();
    private final Scheduler scheduler;

    public ForkPresenter(Scheduler scheduler){
        this.scheduler = scheduler;
    }

    @Override
    protected void onFirstViewAttach() {
        getViewState().init();
        loadData();
    }

    private void loadData(){
        getViewState().updateInfo();
    }

    public boolean backPressed() {
        return true;
    }
}
