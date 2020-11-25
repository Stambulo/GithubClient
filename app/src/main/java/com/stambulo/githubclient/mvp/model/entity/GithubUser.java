package com.stambulo.githubclient.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class GithubUser implements Parcelable {
    @Expose private final String id;
    @Expose private final String login;
    @Expose private final String avatarUrl;
    @Expose private final String reposUrl;

    public GithubUser(String id, String login, String avatar, String repoUrl) {
        this.id = id;
        this.login = login;
        this.avatarUrl = avatar;
        this.reposUrl = repoUrl;
    }

    protected GithubUser(Parcel in) {
        id = in.readString();
        login = in.readString();
        avatarUrl = in.readString();
        reposUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(login);
        dest.writeString(avatarUrl);
        dest.writeString(reposUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GithubUser> CREATOR = new Creator<GithubUser>() {
        @Override
        public GithubUser createFromParcel(Parcel in) {
            return new GithubUser(in);
        }

        @Override
        public GithubUser[] newArray(int size) {
            return new GithubUser[size];
        }
    };

    public String getLogin() {
        return login;
    }

    public String getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }
}
