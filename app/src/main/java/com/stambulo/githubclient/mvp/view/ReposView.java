package com.stambulo.githubclient.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;


@AddToEndSingle
public interface ReposView extends MvpView {
    void init();
    void showRepos();
}
