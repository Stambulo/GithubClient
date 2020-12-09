package com.stambulo.githubclient.di;

import com.stambulo.githubclient.MainActivity;
import com.stambulo.githubclient.di.module.ApiModule;
import com.stambulo.githubclient.di.module.AppModule;
import com.stambulo.githubclient.di.module.CacheModule;
import com.stambulo.githubclient.di.module.CiceroneModule;
import com.stambulo.githubclient.di.module.ImageModule;
import com.stambulo.githubclient.di.user.UsersSubcomponent;
import com.stambulo.githubclient.mvp.presenter.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ApiModule.class,
                AppModule.class,
                CacheModule.class,
                CiceroneModule.class,
                ImageModule.class
        }
)

public interface AppComponent {
    UsersSubcomponent userSubComponent();

    void inject(MainActivity mainActivity);
    void inject(MainPresenter mainPresenter);
}
