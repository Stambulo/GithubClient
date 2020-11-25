package com.stambulo.githubclient.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stambulo.githubclient.GithubApplication;
import com.stambulo.githubclient.R;
import com.stambulo.githubclient.mvp.model.entity.room.Database;
import com.stambulo.githubclient.mvp.model.repo.IGithubUsersRepo;
import com.stambulo.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo;
import com.stambulo.githubclient.mvp.presenter.UsersPresenter;
import com.stambulo.githubclient.mvp.view.UsersView;
import com.stambulo.githubclient.ui.BackButtonListener;
import com.stambulo.githubclient.ui.adapter.UserRVAdapter;
import com.stambulo.githubclient.ui.network.AndroidNetworkStatus;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.terrakok.cicerone.Router;

public class UsersFragment extends MvpAppCompatFragment implements UsersView, BackButtonListener {
    private RecyclerView recyclerView;
    private UserRVAdapter adapter;
    private View view;

    @InjectPresenter
    UsersPresenter usersPresenter;

    @ProvidePresenter
    UsersPresenter provideUsersPresenter() {
        IGithubUsersRepo usersRepo = new RetrofitGithubUsersRepo(GithubApplication.INSTANCE.getApi(),
                new AndroidNetworkStatus(),
                Database.getInstance());
        Router router = GithubApplication.getApplication().getRouter();
        return new UsersPresenter(AndroidSchedulers.mainThread(), usersRepo, router);
    }

    public static UsersFragment getInstance() {
        return new UsersFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_users, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.rv_users);
        return view;
    }


    @Override
    public void init() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new UserRVAdapter(usersPresenter.getUsersListPresenter());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean backPressed() {
        return usersPresenter.backPressed();
    }
}
