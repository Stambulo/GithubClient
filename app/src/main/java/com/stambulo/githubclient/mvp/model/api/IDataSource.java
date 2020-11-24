package com.stambulo.githubclient.mvp.model.api;

import com.stambulo.githubclient.mvp.model.entity.GithubUser;
import com.stambulo.githubclient.mvp.model.entity.Repository;

import java.util.List;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface IDataSource {

    @GET("/users")
    Single<List<GithubUser>> getUsers();

    @GET("/users/{login}/repos")
    Single<List<Repository>> getRepositories(@Path("login") String login);
}
