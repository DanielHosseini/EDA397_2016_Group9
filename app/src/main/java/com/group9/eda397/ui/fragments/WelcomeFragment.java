package com.group9.eda397.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        getAppCompatActivity().getSupportActionBar().setTitle(R.string.title_welcome);
        return view;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_welcome;
    }
}
