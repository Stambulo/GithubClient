package com.stambulo.githubclient.mvp.model.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GithubUserRepo {
    private List<GithubUser> repositories = new ArrayList<>(Arrays.asList(
            new GithubUser("login1"),
            new GithubUser("login2"),
            new GithubUser("login3"),
            new GithubUser("login4"),
            new GithubUser("login5")));

    public List<GithubUser> getUsers(){
        return Collections.unmodifiableList(repositories);
    }

    public GithubUser getLogin(int position){
        return repositories.get(position);
    }
}
