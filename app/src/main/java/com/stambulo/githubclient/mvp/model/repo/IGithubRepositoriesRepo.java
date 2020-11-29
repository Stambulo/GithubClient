package com.stambulo.githubclient.mvp.model.repo;

import com.stambulo.githubclient.mvp.model.entity.GithubRepository;
import com.stambulo.githubclient.mvp.model.entity.GithubUser;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface IGithubRepositoriesRepo {
    Single<List<GithubRepository>> getRepositories(GithubUser user);
    Completable getAllRepositories(List<GithubUser> users);
}
