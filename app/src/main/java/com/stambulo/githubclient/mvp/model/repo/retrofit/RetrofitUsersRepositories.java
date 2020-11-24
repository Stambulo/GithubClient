package com.stambulo.githubclient.mvp.model.repo.retrofit;

import android.util.Log;

import com.stambulo.githubclient.mvp.model.api.IDataSource;
import com.stambulo.githubclient.mvp.model.entity.Repository;
import com.stambulo.githubclient.mvp.model.repo.IUsersRepositoriesRepo;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RetrofitUsersRepositories implements IUsersRepositoriesRepo {
    private IDataSource api;

    public RetrofitUsersRepositories(IDataSource api){
        this.api = api;
    }

    @Override
    public Single<List<Repository>> getRepositories(String login) {
        Log.i("--->", login);
        return api.getRepositories(login).subscribeOn(Schedulers.io());
    }
}
