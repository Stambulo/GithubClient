package com.stambulo.githubclient.mvp.view.image;

public interface IImageLoader<T> {
    void loadImage(String url, T container);
}
