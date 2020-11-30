package com.stambulo.githubclient.mvp.presenter;

import android.util.Log;

import com.stambulo.githubclient.mvp.model.entity.GithubUser;
import com.stambulo.githubclient.mvp.model.repo.IGithubUsersRepo;
import com.stambulo.githubclient.mvp.presenter.list.IUserListPresenter;
import com.stambulo.githubclient.mvp.view.UsersView;
import com.stambulo.githubclient.mvp.view.list.UserItemView;
import com.stambulo.githubclient.navigation.Screens;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Scheduler;
import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class UsersPresenter extends MvpPresenter<UsersView> {
    private static final String TAG = UsersPresenter.class.getSimpleName();
    private final Router router;
    private IGithubUsersRepo usersRepo;
    private final Scheduler scheduler;

    public UsersPresenter(Scheduler scheduler, IGithubUsersRepo usersRepo, Router router) {
        this.scheduler = scheduler;
        this.usersRepo = usersRepo;
        this.router = router;
    }

    private class UsersListPresenter implements IUserListPresenter {
        private final List<GithubUser> users = new ArrayList<>();

        @Override
        public void onItemClick(UserItemView view) {
            GithubUser user = users.get(view.getPos());
            router.navigateTo(new Screens.UserScreen(user));
        }

        @Override
        public void bindView(UserItemView view) {
            GithubUser user = users.get(view.getPos());
            view.setLogin(user.getLogin());
            view.loadAvatar(user.getAvatarUrl());
        }

        @Override
        public int getCount() {
            return users.size();
        }
    }


    private final UsersListPresenter usersListPresenter = new UsersListPresenter();

    public UsersListPresenter getUsersListPresenter() {
        return usersListPresenter;
    }


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
        loadData();
    }


    private void loadData() {
        Log.i(TAG, "UsersPresenter getRepositories");
        usersRepo.getUsers().observeOn(scheduler).subscribe(repos -> {
            usersListPresenter.users.clear();
            usersListPresenter.users.addAll(repos);
            getViewState().updateList();
        }, (e) -> Log.w(TAG, "Error" + e.getMessage()));
    }


    public boolean backPressed() {
        return true;

    }
}
