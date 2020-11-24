package com.stambulo.githubclient.mvp.presenter;

import android.util.Log;

import com.stambulo.githubclient.GithubApplication;
import com.stambulo.githubclient.mvp.model.entity.GithubUserRepo;
import com.stambulo.githubclient.mvp.model.repo.IUsersRepositoriesRepo;
import com.stambulo.githubclient.mvp.model.repo.retrofit.RetrofitUsersRepositories;
import com.stambulo.githubclient.mvp.view.ReposView;

import io.reactivex.rxjava3.core.Scheduler;
import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;


public class ReposPresenter extends MvpPresenter<ReposView> {
    private Router router = GithubApplication.getApplication().getRouter();
    private final IUsersRepositoriesRepo repoRepo;
    private final Scheduler scheduler;
    private GithubUserRepo currentLogin = new GithubUserRepo();

    public ReposPresenter(Scheduler scheduler) {
        this.scheduler = scheduler;
        repoRepo = new RetrofitUsersRepositories(GithubApplication.INSTANCE.getApi());
    }

    @Override
    protected void onFirstViewAttach() {
        getViewState().init();
        loadData();
    }

    private void loadData(){
        repoRepo.getRepositories(currentLogin.getCurrentLogin()).observeOn(scheduler).subscribe(repos ->{
            Log.i("--->", String.valueOf(repos.get(0).getId()));
        },(e)->{
            Log.w("--->", "Error: " + e.getMessage());
        });
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }
}
