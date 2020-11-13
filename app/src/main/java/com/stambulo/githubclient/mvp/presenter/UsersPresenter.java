package com.stambulo.githubclient.mvp.presenter;

import com.stambulo.githubclient.GithubApplication;
import com.stambulo.githubclient.mvp.model.entity.GithubUser;
import com.stambulo.githubclient.mvp.model.entity.GithubUserRepo;
import com.stambulo.githubclient.mvp.presenter.list.IUserListPresenter;
import com.stambulo.githubclient.mvp.view.UserItemView;
import com.stambulo.githubclient.mvp.view.UsersView;
import com.stambulo.githubclient.navigation.Screens;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class UsersPresenter extends MvpPresenter<UsersView> {
    private static final String TAG = UsersPresenter.class.getSimpleName();
    private static final boolean VERBOSE = true;

    private GithubUserRepo userRepo = new GithubUserRepo();
    private Router router = GithubApplication.getApplication().getRouter();

    private class UsersListPresenter implements IUserListPresenter {

        private List<GithubUser> users = new ArrayList<>();

        @Override
        public void onItemClick(UserItemView view) {
            if (VERBOSE) {
                router.navigateTo(new Screens.LoginScreen());

                LoginPresenter loginPresenter = new LoginPresenter();
                GithubUser selectedUser = users.get(view.getPos());
                loginPresenter.setLogin(selectedUser.getLogin());
            }
        }

        @Override
        public void bindView(UserItemView view) {
            GithubUser user = users.get(view.getPos());
            view.setLogin(user.getLogin());
        }

        @Override
        public int getCount() {
            return users.size();
        }
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
        GithubUserRepo producer = new GithubUserRepo();
        Consumer consumer = new Consumer(producer);

        consumer.execFromIterable();
    }

    class Consumer {
        GithubUserRepo producer;

        public Consumer(GithubUserRepo producer){
            this.producer = producer;
        }

        final Observer<List<GithubUser>> stringObserver = new Observer<List<GithubUser>>() {
            Disposable disposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(@NonNull List<GithubUser> githubUsers) {
                usersListPresenter.users = githubUsers;
                getViewState().updateList();
            }

            @Override
            public void onError(@NonNull Throwable e) {}

            @Override
            public void onComplete() {}
        };

        public void execFromIterable(){
            producer.fromIterable().subscribe(stringObserver);
        }
    }

    public boolean backPressed() {
        return true;
    }
}
