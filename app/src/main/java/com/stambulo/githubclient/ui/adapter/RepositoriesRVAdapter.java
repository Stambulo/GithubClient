package com.stambulo.githubclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stambulo.githubclient.R;
import com.stambulo.githubclient.mvp.presenter.list.IRepositoryListPresenter;
import com.stambulo.githubclient.mvp.view.list.RepositoryItemView;

public class RepositoriesRVAdapter extends RecyclerView.Adapter<RepositoriesRVAdapter.ViewHolder> {
    private IRepositoryListPresenter mPresenter;

    public RepositoriesRVAdapter(IRepositoryListPresenter presenter) {
        mPresenter = presenter;
    }

    @NonNull
    @Override
    public RepositoriesRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View repoView = inflater.inflate(R.layout.item_repository, parent, false);
        return new ViewHolder(repoView);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoriesRVAdapter.ViewHolder holder, int position) {
        holder.position = position;
        holder.itemView.setOnClickListener(view -> mPresenter.onItemClick(holder));
        mPresenter.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements RepositoryItemView {
        TextView name;
        int position = -1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
        }

        @Override
        public int getPos() {
            return position;
        }

        @Override
        public void setName(String text) {
            name.setText(text);
        }
    }
}
