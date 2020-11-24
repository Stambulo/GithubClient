package com.stambulo.githubclient.mvp.presenter;

import android.util.Log;

import com.stambulo.githubclient.GithubApplication;
import com.stambulo.githubclient.mvp.model.entity.GithubUser;
import com.stambulo.githubclient.mvp.model.entity.GithubUserRepo;
import com.stambulo.githubclient.mvp.model.repo.IGithubUsersRepo;
import com.stambulo.githubclient.mvp.model.repo.IUsersRepositoriesRepo;
import com.stambulo.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo;
import com.stambulo.githubclient.mvp.model.repo.retrofit.RetrofitUsersRepositories;
import com.stambulo.githubclient.mvp.presenter.list.IUserListPresenter;
import com.stambulo.githubclient.mvp.view.UserItemView;
import com.stambulo.githubclient.mvp.view.UsersView;
import com.stambulo.githubclient.navigation.Screens;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class UsersPresenter extends MvpPresenter<UsersView> {
    private static final boolean VERBOSE = true;
    private Router router = GithubApplication.getApplication().getRouter();

    private final IGithubUsersRepo usersRepo;
    private final Scheduler scheduler;

    public UsersPresenter(Scheduler scheduler) {
        this.scheduler = scheduler;
        usersRepo = new RetrofitGithubUsersRepo(GithubApplication.INSTANCE.getApi());
    }

    private UsersListPresenter usersListPresenter = new UsersListPresenter();

    public UsersListPresenter getUserListPresenter() {
        return usersListPresenter;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
        loadData();
    }

    private void loadData() {
        usersRepo.getUsers().observeOn(scheduler).subscribe(repos -> {
            usersListPresenter.users.clear();
            usersListPresenter.users.addAll(repos);
            getViewState().updateList();
        }, (e) -> {
            Log.w("--->", "Error" + e.getMessage());
        });
    }


    private class UsersListPresenter implements IUserListPresenter {
        private List<GithubUser> users = new ArrayList<>();

        @Override
        public void onItemClick(UserItemView view) {
            if (VERBOSE) {
                GithubUserRepo githubUserRepo = new GithubUserRepo();
                githubUserRepo.setCurrentLogin(usersListPresenter.users.get(view.getPos()).getLogin());
            }
            router.navigateTo(new Screens.ReposScreen());
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

    public boolean backPressed() {
        //router.exit();
        return true;
    }
}
