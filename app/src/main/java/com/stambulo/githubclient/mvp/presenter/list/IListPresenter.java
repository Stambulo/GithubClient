package com.stambulo.githubclient.mvp.presenter.list;

import com.stambulo.githubclient.mvp.view.IItemView;

public interface IListPresenter<V extends IItemView> {
    void onItemClick(V view);
    void bindView(V view);
    int getCount();
}
