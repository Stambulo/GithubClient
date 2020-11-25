package com.stambulo.githubclient.mvp.view.list;

public interface UserItemView extends IItemView {
    void setLogin(String text);
    void loadAvatar(String url);
}
