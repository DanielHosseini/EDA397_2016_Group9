package com.group9.eda397.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.group9.eda397.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Fragment for displaying the newest Travis builds
 *
 * @author palmithor
 * @since 16/04/16.
 */
public class TravisBuildsFragment extends Fragment {

    @Bind(R.id.text) TextView textView;


    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
