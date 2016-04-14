package com.group9.eda397.ui;

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
 * @author palmithor
 * @since 14/04/16.
 */
public class TimerFragment extends Fragment {

    private static final String ARG_TEXT = TimerFragment.class.getCanonicalName() + ".arg_text";
    @Bind(R.id.text) TextView textView;

    public static TimerFragment newInstance(final String text) {
        TimerFragment fragment = new TimerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TEXT, text);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        ButterKnife.bind(this, view);
        textView.setText(getArguments().getString(ARG_TEXT));
        return view;
    }
}
