package com.group9.eda397.ui.fragments;

import android.support.v4.app.Fragment;

import com.group9.eda397.R;

/**
 * Fragment to display on app start.
 * <p/>
 * Welcomes the user
 *
 * @author palmithor
 * @since 28/04/16.
 */
public class WelcomeFragment extends BaseFragment {
    public static Fragment newInstance() {
        return new WelcomeFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_welcome;
    }
}
