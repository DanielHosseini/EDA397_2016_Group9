package com.group9.eda397.ui;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.group9.eda397.R;
import com.group9.eda397.ui.fragments.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author palmithor
 * @since 14/04/16.
 */
public class TimerFragment extends BaseFragment {

    private static final String ARG_TEXT = TimerFragment.class.getCanonicalName() + ".arg_text";
    @Bind(R.id.text) TextView textView;

    private int count = 0;

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
        View view = inflater.inflate(R.layout.countdown_timer, container, false);
        ButterKnife.bind(this, view);
        //textView.setText(getArguments().getString(ARG_TEXT));
        count = Integer.parseInt(ARG_TEXT)+1;
        textView.setText((count)+"");
        testTimer();
        return view;
    }

    private void testTimer(){
        CountDownTimer timer = new CountDownTimer(count*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        testStringChange();
                    }
                });

            }

            @Override
            public void onFinish() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("Done");
                    }
                });

            }
        };
        timer.start();
    }

    private void testStringChange() {
        count = count -1;
        textView.setText(count + "");
    }

    protected int getLayout() {
        return R.layout.countdown_timer;
    }
}
