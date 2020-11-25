package com.stambulo.githubclient.mvp.model.cache;

import com.stambulo.githubclient.mvp.model.entity.GithubUser;
import java.util.List;
import io.reactivex.rxjava3.core.Single;


public interface IGithubUsersCache {
    Single<List<GithubUser>> getUsers();
}
