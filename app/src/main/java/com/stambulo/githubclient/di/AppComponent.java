package com.stambulo.githubclient.di;

import com.stambulo.githubclient.MainActivity;
import com.stambulo.githubclient.di.module.ApiModule;
import com.stambulo.githubclient.di.module.AppModule;
import com.stambulo.githubclient.di.module.CacheModule;
import com.stambulo.githubclient.di.module.CiceroneModule;
import com.stambulo.githubclient.di.module.RepoModule;
import com.stambulo.githubclient.mvp.presenter.MainPresenter;
import com.stambulo.githubclient.mvp.presenter.UserPresenter;
import com.stambulo.githubclient.mvp.presenter.UsersPresenter;
import com.stambulo.githubclient.ui.fragments.RepositoryFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ApiModule.class,
                AppModule.class,
                CacheModule.class,
                CiceroneModule.class,
                RepoModule.class
        }
)

public interface AppComponent {
    void inject(MainActivity mainActivity);
    void inject(MainPresenter mainPresenter);
    void inject(UsersPresenter usersPresenter);
    void inject(UserPresenter userPresenter);

    // Домашка. Избавиться от лишней инъекции
    void inject(RepositoryFragment fragment);
}
