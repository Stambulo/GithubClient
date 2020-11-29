package com.stambulo.githubclient.mvp.model.repo.retrofit;

import android.util.Log;

import com.stambulo.githubclient.GithubApplication;
import com.stambulo.githubclient.mvp.model.api.IDataSource;
import com.stambulo.githubclient.mvp.model.cache.IGithubRepositoriesCache;
import com.stambulo.githubclient.mvp.model.cache.room.RoomGithubRepositoriesCache;
import com.stambulo.githubclient.mvp.model.entity.GithubRepository;
import com.stambulo.githubclient.mvp.model.entity.GithubUser;
import com.stambulo.githubclient.mvp.model.entity.room.Database;
import com.stambulo.githubclient.mvp.model.network.INetworkStatus;
import com.stambulo.githubclient.mvp.model.repo.IGithubRepositoriesRepo;
import com.stambulo.githubclient.ui.network.AndroidNetworkStatus;

import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RetrofitGithubRepositoriesRepo implements IGithubRepositoriesRepo {
    private static final String TAG = "--->";
    private final IDataSource api;
    private final INetworkStatus networkStatus;
    final IGithubRepositoriesCache cache;
    RetrofitGithubRepositoriesRepo githubRepositoriesRepo;

    public RetrofitGithubRepositoriesRepo(IDataSource api, INetworkStatus status, IGithubRepositoriesCache cache) {
        this.api = api;
        this.networkStatus = status;
        this.cache = cache;
    }

    @Override
    public Single<List<GithubRepository>> getRepositories(GithubUser user) {
        return networkStatus.isOnlineSingle().flatMap((isOline) -> {
            if (isOline) {
                final String url = user.getReposUrl();

                if (url != null) {
                    return api.getRepositories(url).flatMap((repositories) -> {
                        return cache.putUserRepos(user, repositories).toSingleDefault(repositories);
                    });
                } else {
                    return Single.fromCallable(() -> {
                        final List<GithubRepository> emptyList = Collections.emptyList();
                        return emptyList;
                    });
                }
            } else {
                return cache.getUserRepos(user);
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Completable getAllRepositories(List<GithubUser> users) {
        Log.i(TAG, "getAllRepositories: " + users.size());
        this.githubRepositoriesRepo = new RetrofitGithubRepositoriesRepo(GithubApplication.INSTANCE.getApi(),
                new AndroidNetworkStatus(),
                new RoomGithubRepositoriesCache(Database.getInstance()));
        return Completable.create((emitter -> {
            try {


                /*for (GithubUser user : users) {
                    Log.i(TAG, "getAllRepositories: " + user.getLogin());
                    githubRepositoriesRepo.getRepositories(user).observeOn(Schedulers.io()).subscribe(repositories -> {

                    }, (e) -> Log.w(TAG, "Error"));
                }*/

                emitter.onComplete();
            } catch (Throwable t) {
                emitter.onError(new RuntimeException("Catch error - getAllRepositories !" + t));
            }
        }));
    }
}
