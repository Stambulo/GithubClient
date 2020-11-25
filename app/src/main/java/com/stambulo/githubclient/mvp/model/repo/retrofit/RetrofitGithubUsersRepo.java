package com.stambulo.githubclient.mvp.model.repo.retrofit;

import com.stambulo.githubclient.mvp.model.api.IDataSource;
import com.stambulo.githubclient.mvp.model.entity.GithubUser;
import com.stambulo.githubclient.mvp.model.repo.IGithubUsersRepo;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RetrofitGithubUsersRepo implements IGithubUsersRepo {
    private final IDataSource api;

    public RetrofitGithubUsersRepo(IDataSource api){
        this.api = api;
    }

    @Override
    public Single<List<GithubUser>> getUsers() {
        return api.getUsers().subscribeOn(Schedulers.io());
    }
}
