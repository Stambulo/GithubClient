package com.stambulo.githubclient.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

@AddToEndSingle
public interface LoginView extends MvpView {
    void init();
    void showLogin(String s);
}
