package com.stambulo.githubclient.mvp.model.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class GithubUserRepo {
    private static String currentLogin;
    public void setCurrentLogin(String login){currentLogin = login;}
    public String getCurrentLogin(){return currentLogin;}

        public @NonNull Observable<List<GithubUser>> fromIterable() {
            return Observable.fromIterable(Arrays.asList(repositories));
        }

    private List<GithubUser> repositories = new ArrayList<>(Arrays.asList(
            new GithubUser("login1"),
            new GithubUser("login2"),
            new GithubUser("login3"),
            new GithubUser("login4"),
            new GithubUser("login5")));
}
