package com.stambulo.githubclient.di.repository;

import com.stambulo.githubclient.di.repository.module.RepositoryModule;
import com.stambulo.githubclient.mvp.presenter.RepositoryPresenter;
import com.stambulo.githubclient.mvp.presenter.UserPresenter;

import dagger.Subcomponent;

@RepositoryScope
@Subcomponent(
        modules = {
                RepositoryModule.class
        }
)
public interface RepositorySubcomponent {
    void inject(UserPresenter userPresenter);
    void inject(RepositoryPresenter repoPresenter);
}
