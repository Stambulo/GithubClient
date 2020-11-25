package com.stambulo.githubclient.mvp.presenter.list;

import com.stambulo.githubclient.mvp.view.list.IItemView;

public interface IListPresenter<V extends IItemView> {
    void onItemClick(V view);
    void bindView(V view);
    int getCount();
}
