package com.stambulo.githubclient.mvp.model.repo.retrofit;

import com.stambulo.githubclient.mvp.model.api.IDataSource;
import com.stambulo.githubclient.mvp.model.cache.IGithubUsersCache;
import com.stambulo.githubclient.mvp.model.entity.GithubUser;
import com.stambulo.githubclient.mvp.model.network.INetworkStatus;
import com.stambulo.githubclient.mvp.model.repo.IGithubUsersRepo;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RetrofitGithubUsersRepo implements IGithubUsersRepo {
    private final IDataSource api;
    private final INetworkStatus networkStatus;
    private final IGithubUsersCache cache;

    public RetrofitGithubUsersRepo(IDataSource api, INetworkStatus status, IGithubUsersCache cache){
        this.api = api;
        this.networkStatus = status;
        this.cache = cache;
    }

    @Override
    public Single<List<GithubUser>> getUsers() {
        return networkStatus.isOnlineSingle().flatMap((isOnline)-> {

            if (isOnline) {
                return api.getUsers().flatMap((users) -> cache.saveUsers(users).toSingleDefault(users));

            } else {
                return cache.getUsers();

            }
        }).subscribeOn(Schedulers.io());
    }
}
