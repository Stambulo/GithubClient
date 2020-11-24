package com.stambulo.githubclient.mvp.model.entity;

import com.google.gson.annotations.Expose;

public class Repository {
    @Expose private String id;
    @Expose private String name;
    @Expose private String fullName;
    @Expose private String repoUrl;
    @Expose private int forks;

    public Repository(String repoUrl){
        this.repoUrl = repoUrl;
    }

    public String getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public int getForks() {
        return forks;
    }
}
