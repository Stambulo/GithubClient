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


public class ReposRVAdapter extends RecyclerView.Adapter<ReposRVAdapter.ReposViewHolder>{
    private String[] listData;
    private Context context;

    public ReposRVAdapter(String[] list){
        this.listData = list;
    }

    @NonNull
    @Override
    public ReposViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View userView = inflater.inflate(R.layout.item_repository, parent, false);
        return new ReposViewHolder(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReposViewHolder holder, int position) {
        holder.textView.setText(listData[position]);
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.length;
    }

    class ReposViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public ReposViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.tv_repository);
        }
    }
}
