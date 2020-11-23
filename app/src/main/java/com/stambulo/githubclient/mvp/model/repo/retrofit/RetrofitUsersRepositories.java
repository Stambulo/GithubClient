package com.stambulo.githubclient.mvp.model.repo.retrofit;

import com.stambulo.githubclient.mvp.model.api.IDataSource;
import com.stambulo.githubclient.mvp.model.entity.Repository;
import com.stambulo.githubclient.mvp.model.repo.IUsersRepositoriesRepo;

import java.util.List;

import io.reactivex.rxjava3.core.Single;


public class RetrofitUsersRepositories implements IUsersRepositoriesRepo {

    private IDataSource api;

    public RetrofitUsersRepositories(IDataSource api){
        this.api = api;
    }

    @Override
    public Single<List<Repository>> getRepositories() {
        //return api.getUsers().subscribeOn(Schedulers.io());
        return null;
    }
}
