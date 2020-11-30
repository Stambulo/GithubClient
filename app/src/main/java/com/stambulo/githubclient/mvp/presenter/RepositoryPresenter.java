package com.stambulo.githubclient.mvp.presenter;

import moxy.MvpPresenter;
import com.stambulo.githubclient.GithubApplication;
import com.stambulo.githubclient.mvp.model.entity.GithubRepository;
import com.stambulo.githubclient.mvp.view.RepositoryView;

import ru.terrakok.cicerone.Router;

public class RepositoryPresenter extends MvpPresenter<RepositoryView> {
    private final GithubRepository githubRepository;
    private final Router router = GithubApplication.getApplication().getRouter();


    public RepositoryPresenter(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();

        String id = githubRepository.getId();
        String title = githubRepository.getName();
        int forks = githubRepository.getForksCount();

        getViewState().setId(id != null ? id : "");
        getViewState().setTitle(title != null ? title : "");
        getViewState().setForksCount(String.valueOf(forks));
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }
}
