package com.stambulo.githubclient.mvp.presenter;

import com.stambulo.githubclient.GithubApplication;
import com.stambulo.githubclient.mvp.model.entity.GithubRepository;
import com.stambulo.githubclient.mvp.model.entity.GithubUser;
import com.stambulo.githubclient.mvp.model.repo.IGithubRepositoriesRepo;
import com.stambulo.githubclient.mvp.presenter.list.IRepositoryListPresenter;
import com.stambulo.githubclient.mvp.view.UserView;
import com.stambulo.githubclient.mvp.view.list.RepositoryItemView;
import com.stambulo.githubclient.navigation.Screens;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Scheduler;
import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class UserPresenter extends MvpPresenter<UserView> {
    @Inject
    IGithubRepositoriesRepo githubRepositoriesRepo;
    @Inject
    Router router;
    @Inject
    Scheduler scheduler;
    private final GithubUser user;

    public UserPresenter(GithubUser user) {
        this.user = user;
        GithubApplication.INSTANCE.initRepositoriesSubcomponent().inject(this);
    }

    private class RepositoriesListPresenter implements IRepositoryListPresenter {
        private List<GithubRepository> repositories = new ArrayList<>();
        @Override
        public void onItemClick(RepositoryItemView view) {
            final GithubRepository repository = repositories.get(view.getPos());
            router.navigateTo(new Screens.RepositoryScreen(repository));
        }
        @Override
        public void bindView(RepositoryItemView view) {
            GithubRepository repository = repositories.get(view.getPos());
            view.setName(repository.getName());
        }
        @Override
        public int getCount() {
            return repositories.size();
        }
    }


    private UserPresenter.RepositoriesListPresenter repositoriesListPresenter = new UserPresenter.RepositoriesListPresenter();
    public IRepositoryListPresenter getPresenter() {
        return repositoriesListPresenter;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
        loadData();
    }

    private void loadData() {
        githubRepositoriesRepo.getRepositories(user).observeOn(scheduler).subscribe(repositories-> {
            repositoriesListPresenter.repositories.clear();
            repositoriesListPresenter.repositories.addAll(repositories);
            getViewState().updateList();
        }, (e) -> {
            //Log.w(TAG, "Error" + e.getMessage());
        });
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getViewState().release();
    }
}
