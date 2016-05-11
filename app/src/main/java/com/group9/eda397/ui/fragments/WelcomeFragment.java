package com.group9.eda397.ui.fragments;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.group9.eda397.R;
import com.group9.eda397.ui.ChooseTimeFragment;

/**
 * Fragment to display on app start.
 * <p/>
 * Welcomes the user
 *
 * @author palmithor
 * @since 28/04/16.
 */
public class WelcomeFragment extends BaseFragment {
    private RelativeLayout PlanningButton, TimerButton, TravisButton;
    private Fragment fragment = null;


    public static Fragment newInstance() {
        return new WelcomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        getAppCompatActivity().getSupportActionBar().setTitle(R.string.title_welcome);
                PlanningButton = (RelativeLayout) view.findViewById(R.id.PokerButton);
        PlanningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new PlanningGameFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        TimerButton = (RelativeLayout) view.findViewById(R.id.TimerButton);
        TimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new ChooseTimeFragment().newInstance("timer");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        TravisButton = (RelativeLayout) view.findViewById(R.id.travisButton);
        TravisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new TravisBuildsFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }


    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_welcome;
    }
}
