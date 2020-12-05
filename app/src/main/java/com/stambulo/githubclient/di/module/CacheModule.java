package com.stambulo.githubclient.di.module;

import androidx.room.Room;

import com.stambulo.githubclient.GithubApplication;
import com.stambulo.githubclient.mvp.model.entity.room.Database;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class CacheModule {
    @Singleton
    @Provides
    Database database(){
        return Room.databaseBuilder(GithubApplication.INSTANCE, Database.class, Database.DB_NAME)
                .build();
    }
}
