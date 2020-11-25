package com.stambulo.githubclient.mvp.model.repo;

import com.stambulo.githubclient.mvp.model.entity.Repository;
import java.util.List;
import io.reactivex.rxjava3.core.Single;


public interface IUsersRepositoriesRepo {
    Single<List<Repository>> getRepositories(String login);
}
