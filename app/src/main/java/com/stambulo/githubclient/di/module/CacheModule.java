package com.stambulo.githubclient.di.module;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import com.stambulo.githubclient.GithubApplication;
import com.stambulo.githubclient.mvp.model.cache.IGithubRepositoriesCache;
import com.stambulo.githubclient.mvp.model.cache.IGithubUsersCache;
import com.stambulo.githubclient.mvp.model.cache.room.RoomGithubRepositoriesCache;
import com.stambulo.githubclient.mvp.model.cache.room.RoomGithubUsersCache;
import com.stambulo.githubclient.mvp.model.entity.room.Database;


@Module
public class CacheModule {

    @Singleton
    @Provides
    Database database(){
        return Room.databaseBuilder(GithubApplication.INSTANCE, Database.class, Database.DB_NAME)
                .build();
    }

    @Singleton
    @Provides
    IGithubUsersCache usersCache(Database db){
        return new RoomGithubUsersCache(db);
    }

    @Singleton
    @Provides
    IGithubRepositoriesCache userRepositoriesCache(Database db){
        return new RoomGithubRepositoriesCache(db);
    }
}
