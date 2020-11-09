package com.stambulo.githubclient.navigation;

import androidx.fragment.app.Fragment;

import com.stambulo.githubclient.ui.fragments.UsersFragment;
import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {
    public static class UserScreen extends SupportAppScreen{

        @Override
        public Fragment getFragment(){
            return UsersFragment.getInstance(0);
        }
    }

}
