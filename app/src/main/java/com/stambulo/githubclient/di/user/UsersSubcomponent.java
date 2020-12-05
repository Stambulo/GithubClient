package com.stambulo.githubclient.di.user;

import com.stambulo.githubclient.di.repository.RepositorySubcomponent;
import com.stambulo.githubclient.di.user.module.UsersModule;
import com.stambulo.githubclient.mvp.presenter.UsersPresenter;
import com.stambulo.githubclient.ui.adapter.UserRVAdapter;

import dagger.Subcomponent;

@UsersScope
@Subcomponent(
        modules = {
                UsersModule.class
        }
)
public interface UsersSubcomponent {
    RepositorySubcomponent repositorySubcomponent();

    void inject(UsersPresenter usersPresenter);
    void inject(UserRVAdapter adapter);
}
