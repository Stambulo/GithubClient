package com.stambulo.githubclient;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.stambulo.githubclient.mvp.presenter.MainPresenter;
import com.stambulo.githubclient.mvp.view.MainView;
import com.stambulo.githubclient.ui.BackButtonListener;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;

public class MainActivity extends MvpAppCompatActivity implements MainView {
    private final NavigatorHolder navigatorHolder = GithubApplication.getApplication().getNavigatorHolder();
    private final Navigator navigator = new SupportAppNavigator(this, getSupportFragmentManager(), R.id.container);

    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigatorHolder.removeNavigator();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        for (Fragment fragment : getSupportFragmentManager().getFragments()){
            if (fragment instanceof BackButtonListener && ((BackButtonListener) fragment).backPressed()){
                return;
            }
        }
        presenter.backClicked();
    }
}