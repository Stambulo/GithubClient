package com.stambulo.githubclient.mvp.model.repo.retrofit;

import com.stambulo.githubclient.mvp.model.api.IDataSource;
import com.stambulo.githubclient.mvp.model.entity.GithubRepository;
import com.stambulo.githubclient.mvp.model.entity.GithubUser;
import com.stambulo.githubclient.mvp.model.entity.room.Database;
import com.stambulo.githubclient.mvp.model.network.INetworkStatus;
import com.stambulo.githubclient.mvp.model.repo.IGithubRepositoriesRepo;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RetrofitGithubRepositoriesRepo implements IGithubRepositoriesRepo {
    private final IDataSource api;
    private INetworkStatus networkStatus;
    private Database db;

    public RetrofitGithubRepositoriesRepo(IDataSource api, INetworkStatus status, Database database) {
        this.api = api;
        this.networkStatus = status;
        this.db = database;
    }

    @Override
    public Single<List<GithubRepository>> getRepositories(GithubUser user) {
        final String url = user.getReposUrl();

        return api.getRepositories(url).subscribeOn(Schedulers.io());
    }
}
