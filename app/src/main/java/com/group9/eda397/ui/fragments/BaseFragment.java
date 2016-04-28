package com.group9.eda397.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.group9.eda397.MainActivity;

import butterknife.ButterKnife;
import icepick.Icepick;

/**
 * Base Fragment that should be extended by all other fragments.
 * <p/>
 * The fragment binds Butterknife to the view set as well as forcing the layout to be set
 * in a structured way.
 * <p/>
 * It also introduces a method for extracting parameters from the bundle.
 * <p/>
 * Finally it restores and stores the instance state using Icepick
 *
 * @author palmithor
 * @since 17/04/16.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        Bundle params = getArguments();
        if (params != null) {
            onExtractArguments(params);
        }
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    protected void hideFab() {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.getFab().setVisibility(View.GONE);
    }

    public AppCompatActivity getAppCompatActivity() {
        return (AppCompatActivity) getActivity();
    }

    protected abstract int getLayout();

    /**
     * Method that should be implemented in all fragments that are expecting arguments
     *
     * @param args
     */
    protected void onExtractArguments(final Bundle args) {
        // Nothing done here
    }


}

