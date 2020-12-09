package com.stambulo.githubclient.di.user.module;

import com.stambulo.githubclient.di.user.UsersScope;
import com.stambulo.githubclient.mvp.model.api.IDataSource;
import com.stambulo.githubclient.mvp.model.cache.IGithubUsersCache;
import com.stambulo.githubclient.mvp.model.cache.room.RoomGithubUsersCache;
import com.stambulo.githubclient.mvp.model.entity.room.Database;
import com.stambulo.githubclient.mvp.model.network.INetworkStatus;
import com.stambulo.githubclient.mvp.model.repo.IGithubUsersRepo;
import com.stambulo.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo;

import dagger.Module;
import dagger.Provides;

@Module
public class UsersModule {

    @Provides
    IGithubUsersCache usersCache(Database db){
        return new RoomGithubUsersCache(db);
    }

    @UsersScope
    @Provides
    public IGithubUsersRepo usersRepo(IDataSource api, INetworkStatus status,
                                      IGithubUsersCache cache){
        return new RetrofitGithubUsersRepo(api, status, cache);
    }
}
