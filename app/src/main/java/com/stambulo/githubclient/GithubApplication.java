package com.stambulo.githubclient;

import android.app.Application;

import com.stambulo.githubclient.di.AppComponent;
import com.stambulo.githubclient.di.DaggerAppComponent;
import com.stambulo.githubclient.di.module.AppModule;
import com.stambulo.githubclient.mvp.model.api.IDataSource;

public class GithubApplication extends Application {
    public static final boolean DEBUG = true;
    public static GithubApplication INSTANCE;

    private ApiHolder apiHolder;
    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        apiHolder = new ApiHolder();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static GithubApplication getApplication(){
        return INSTANCE;
    }

    public IDataSource getApi() {
        return apiHolder.getDataSource();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}
