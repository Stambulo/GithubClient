package com.stambulo.githubclient.mvp.presenter;

import android.util.Log;

import com.stambulo.githubclient.GithubApplication;
import com.stambulo.githubclient.mvp.model.entity.ForkData;
import com.stambulo.githubclient.mvp.model.entity.GithubUserRepo;
import com.stambulo.githubclient.mvp.model.entity.Repository;
import com.stambulo.githubclient.mvp.model.entity.UsersRepositoriesRepo;
import com.stambulo.githubclient.mvp.model.repo.IUsersRepositoriesRepo;
import com.stambulo.githubclient.mvp.model.repo.retrofit.RetrofitUsersRepositories;
import com.stambulo.githubclient.mvp.presenter.list.IReposListPresenter;
import com.stambulo.githubclient.mvp.view.ReposItemView;
import com.stambulo.githubclient.mvp.view.ReposView;
import com.stambulo.githubclient.navigation.Screens;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Scheduler;
import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;


public class ReposPresenter extends MvpPresenter<ReposView> {
    private final Router router = GithubApplication.getApplication().getRouter();
    private final IUsersRepositoriesRepo repoRepo;
    private final Scheduler scheduler;
    private final GithubUserRepo currentLogin = new GithubUserRepo();

    public ReposPresenter(Scheduler scheduler) {
        this.scheduler = scheduler;
        repoRepo = new RetrofitUsersRepositories(GithubApplication.INSTANCE.getApi());
    }

    private final ReposListPresenter reposListPresenter = new ReposListPresenter();
    public ReposListPresenter getReposListPresenter() {
        return reposListPresenter;
    }

    @Override
    protected void onFirstViewAttach() {
        getViewState().init();
        loadData();
    }

    private void loadData() {
        repoRepo.getRepositories(currentLogin.getCurrentLogin())
                .observeOn(scheduler).subscribe(repos -> {
            reposListPresenter.repos.clear();
            reposListPresenter.repos.addAll(repos);
            getViewState().updateList();
        }, (e) -> Log.w("--->", "Error: " + e.getMessage()));
    }

    private class ReposListPresenter implements IReposListPresenter {
        private final List<Repository> repos = new ArrayList<>();

        @Override
        public void onItemClick(ReposItemView view) {
            UsersRepositoriesRepo usersRepositoriesRepo = new UsersRepositoriesRepo();
            usersRepositoriesRepo.setCurrentRepository(reposListPresenter.repos.get(view.getPos()).getName());
            ForkData.setForkName(reposListPresenter.repos.get(view.getPos()).getName());
            ForkData.setForksSum(reposListPresenter.repos.get(view.getPos()).getForks());
            router.navigateTo(new Screens.ForkScreen());
        }

        @Override
        public void bindView(ReposItemView view) {
            Repository repository = repos.get(view.getPos());
            view.setRepository(repository.getName());
        }

        @Override
        public int getCount() {
            return repos.size();
        }
    }

    public boolean backPressed() {
        return true;
    }
}
