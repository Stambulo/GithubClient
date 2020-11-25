package com.stambulo.githubclient.mvp.model.entity.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RoomGithubUser {

    @PrimaryKey @NonNull
    public String id;
    public String login;
    public String avatarUrl;
    public String reposUrl;

    public RoomGithubUser(){
    }

    public RoomGithubUser(String id, String login, String avatar, String reposUrl){
        this.id = id;
        this.login = login;
        this.avatarUrl = avatar;
        this.reposUrl = reposUrl;
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }
}
