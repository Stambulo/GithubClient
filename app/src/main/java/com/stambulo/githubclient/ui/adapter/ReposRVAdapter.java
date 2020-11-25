package com.stambulo.githubclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stambulo.githubclient.R;
import com.stambulo.githubclient.mvp.presenter.list.IReposListPresenter;
import com.stambulo.githubclient.mvp.view.ReposItemView;


public class ReposRVAdapter extends RecyclerView.Adapter<ReposRVAdapter.ReposViewHolder> {
    IReposListPresenter presenter;

    public ReposRVAdapter(IReposListPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ReposViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View userView = inflater.inflate(R.layout.item_repository, parent, false);
        return new ReposViewHolder(userView);
    }


    @Override
    public void onBindViewHolder(@NonNull ReposViewHolder holder, int position) {
        holder.position = position;
        holder.itemView.setOnClickListener(view -> presenter.onItemClick(holder));
        presenter.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getCount();
    }

    public static class ReposViewHolder extends RecyclerView.ViewHolder implements ReposItemView {
        int position;
        TextView textView;

        public ReposViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_repository);
        }

        @Override
        public void setRepository(String repository) {
            textView.setText(repository);
        }

        @Override
        public int getPos() {
            return position;
        }
    }
}
