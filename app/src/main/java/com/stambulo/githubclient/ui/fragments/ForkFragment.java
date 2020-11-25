package com.stambulo.githubclient.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.stambulo.githubclient.R;
import com.stambulo.githubclient.mvp.model.entity.ForkData;
import com.stambulo.githubclient.mvp.presenter.ForkPresenter;
import com.stambulo.githubclient.mvp.presenter.ReposPresenter;
import com.stambulo.githubclient.mvp.view.ForkView;
import com.stambulo.githubclient.ui.BackButtonListener;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class ForkFragment extends MvpAppCompatFragment implements ForkView, BackButtonListener {
    private TextView forkName;
    private TextView forksNumber;

    @InjectPresenter
    ForkPresenter forkPresenter;

    @ProvidePresenter
    ForkPresenter provideForkPresenter(){
        return new ForkPresenter(AndroidSchedulers.mainThread());
    }

    public static ForkFragment getInstance(int data){
        return new ForkFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forks, container, false);
        forkName = view.findViewById(R.id.tv_repo_label);
        forksNumber = view.findViewById(R.id.tv_forks_number_label);
        return view;
    }

    @Override
    public void init() {

    }

    @Override
    public void updateInfo() {
        forkName.setText(ForkData.getForkName());
        forksNumber.setText(String.valueOf(ForkData.getForksSum()));
    }

    @Override
    public boolean backPressed() {
        return forkPresenter.backPressed();
    }
}
