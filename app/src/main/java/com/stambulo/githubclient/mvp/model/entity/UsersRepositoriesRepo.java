package com.stambulo.githubclient.mvp.model.entity;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsersRepositoriesRepo{

    private static String currentRepository;

    public void setCurrentRepository(String repository){
        currentRepository = repository;
    }

    public String getCurrentRepository() {
        return currentRepository;
    }

    private final List<Repository> usersRepositories = new ArrayList<>(Arrays.asList(
            new Repository("Repository 1"),
            new Repository("Repository 2"),
            new Repository("Repository 3"),
            new Repository("Repository 4"),
            new Repository("Repository 5")
    ));
}
