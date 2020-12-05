package com.stambulo.githubclient.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.stambulo.githubclient.R;
import com.stambulo.githubclient.mvp.model.entity.GithubRepository;
import com.stambulo.githubclient.mvp.presenter.RepositoryPresenter;
import com.stambulo.githubclient.mvp.view.RepositoryView;
import com.stambulo.githubclient.ui.BackButtonListener;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;


public class RepositoryFragment extends MvpAppCompatFragment implements RepositoryView, BackButtonListener {
    private static final String REPOSITORY_ARG = "repository";
    private View mView;
    private TextView tv_id;
    private TextView tv_title;
    private TextView tv_forksCount;

    @InjectPresenter
    RepositoryPresenter presenter;

    @ProvidePresenter
    RepositoryPresenter provideRepositoryPresenter() {
        final GithubRepository githubRepository = getArguments().getParcelable(REPOSITORY_ARG);
        return new RepositoryPresenter(githubRepository);
    }


    public static RepositoryFragment newInstance(GithubRepository repository) {
        RepositoryFragment fragment = new RepositoryFragment();
        Bundle args = new Bundle();
        args.putParcelable(REPOSITORY_ARG, repository);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_repository, container, false);
        tv_id = (TextView) mView.findViewById(R.id.tv_id);
        tv_title = (TextView) mView.findViewById(R.id.tv_title);
        tv_forksCount = (TextView) mView.findViewById(R.id.tv_forksCount);
        return mView;
    }

    @Override
    public void init() {}

    @Override
    public void setId(String text) {
        tv_id.setText(text);
    }

    @Override
    public void setTitle(String text) {
        tv_title.setText(text);
    }

    @Override
    public void setForksCount(String text) {
        tv_forksCount.setText(text);
    }

    @Override
    public boolean backPressed() {
        return presenter.backPressed();
    }
}
