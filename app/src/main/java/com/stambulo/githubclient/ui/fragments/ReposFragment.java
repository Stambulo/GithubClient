package com.stambulo.githubclient.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stambulo.githubclient.R;
import com.stambulo.githubclient.mvp.presenter.ReposPresenter;
import com.stambulo.githubclient.mvp.view.ReposView;
import com.stambulo.githubclient.ui.BackButtonListener;
import com.stambulo.githubclient.ui.adapter.ReposRVAdapter;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;


public class ReposFragment extends MvpAppCompatFragment implements ReposView, BackButtonListener {
    private View view;
    private RecyclerView recyclerView;
    private ReposRVAdapter adapter;

    @InjectPresenter
    ReposPresenter reposPresenter;

    @ProvidePresenter
    ReposPresenter provideReposPresenter(){
        return new ReposPresenter(AndroidSchedulers.mainThread());
    }

    public static ReposFragment getInstance(int data){
        return new ReposFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_repos, container, false);
        recyclerView = view.findViewById(R.id.rv_repos);
        return view;
    }

    @Override
    public void init() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new ReposRVAdapter(reposPresenter.getReposListPresenter());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean backPressed() {
        return reposPresenter.backPressed();
    }
}
