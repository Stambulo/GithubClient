package com.stambulo.githubclient.mvp.presenter;

import android.util.Log;

import com.stambulo.githubclient.GithubApplication;
import com.stambulo.githubclient.mvp.model.entity.UsersRepositoriesRepo;
import com.stambulo.githubclient.mvp.model.repo.IUsersRepositoriesRepo;
import com.stambulo.githubclient.mvp.presenter.list.IReposListPresenter;
import com.stambulo.githubclient.mvp.view.ReposView;
import com.stambulo.githubclient.mvp.view.UserItemView;
import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;


public class ReposPresenter extends MvpPresenter<ReposView> {
    private Router router = GithubApplication.getApplication().getRouter();

    public ReposPresenter() {
        //
    }

    @Override
    protected void onFirstViewAttach() {
        getViewState().init();
        loadData();
    }

    private void loadData(){
        Log.i("--->", "ReposPresenter - loadData()");
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }
}
