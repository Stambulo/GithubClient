package com.stambulo.githubclient.di.repository.module;

import com.stambulo.githubclient.di.repository.RepositoryScope;
import com.stambulo.githubclient.mvp.model.api.IDataSource;
import com.stambulo.githubclient.mvp.model.cache.IGithubRepositoriesCache;
import com.stambulo.githubclient.mvp.model.cache.room.RoomGithubRepositoriesCache;
import com.stambulo.githubclient.mvp.model.entity.room.Database;
import com.stambulo.githubclient.mvp.model.network.INetworkStatus;
import com.stambulo.githubclient.mvp.model.repo.IGithubRepositoriesRepo;
import com.stambulo.githubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    IGithubRepositoriesCache userRepositoriesCache(Database db){
        return new RoomGithubRepositoriesCache(db);
    }

    @RepositoryScope
    @Provides
    public IGithubRepositoriesRepo userRepositoriesRepo(IDataSource api,
                                                        INetworkStatus networkStatus,
                                                        IGithubRepositoriesCache cache){
        return new RetrofitGithubRepositoriesRepo(api, networkStatus, cache);
    }
}
