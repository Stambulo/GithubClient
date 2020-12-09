package com.stambulo.githubclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stambulo.githubclient.GithubApplication;
import com.stambulo.githubclient.R;
import com.stambulo.githubclient.mvp.presenter.list.IUserListPresenter;
import com.stambulo.githubclient.mvp.view.image.IImageLoader;
import com.stambulo.githubclient.mvp.view.list.UserItemView;

import javax.inject.Inject;

public class UserRVAdapter extends RecyclerView.Adapter<UserRVAdapter.ViewHolder> {
    private IUserListPresenter presenter;

    @Inject
    IImageLoader<ImageView> imageLoader;

    public UserRVAdapter(IUserListPresenter presenter) {
        this.presenter = presenter;
        GithubApplication.INSTANCE.initUserSubcomponent().inject(this);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View userView = inflater.inflate(R.layout.item_user, parent, false);
        return new ViewHolder(userView);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.position = position;
        holder.itemView.setOnClickListener(view -> presenter.onItemClick(holder));
        presenter.bindView(holder);
    }


    @Override
    public int getItemCount() {
        return presenter.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements UserItemView {
        TextView textView;
        ImageView avatarView;
        int position;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_login);
            avatarView = (ImageView)itemView.findViewById(R.id.iv_avatar);
        }

        @Override
        public void setLogin(String text) {
            textView.setText(text);
        }

        @Override
        public void loadAvatar(String url) {
            imageLoader.loadImage(url, avatarView);
        }

        @Override
        public int getPos() {
            return position;
        }
    }
}
