package com.stambulo.githubclient.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

@AddToEndSingle
public interface ForkView extends MvpView {
    void init();
    void updateInfo();
}
