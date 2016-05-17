package com.group9.eda397.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.group9.eda397.R;

import butterknife.Bind;

/**
 * Fragment to display on app start.
 * <p/>
 * Welcomes the user
 *
 * @author palmithor
 * @since 28/04/16.
 */
public class WelcomeFragment extends BaseFragment {
    private Fragment fragment = null;
    @Bind(R.id.PokerButton) RelativeLayout PlanningButton;
    @Bind(R.id.TimerButton) RelativeLayout TimerButton;
    @Bind(R.id.travisButton) RelativeLayout TravisButton;
    @Bind(R.id.githubButton) RelativeLayout GitHubButton;



    public static Fragment newInstance() {
        return new WelcomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        getAppCompatActivity().getSupportActionBar().setTitle(R.string.title_welcome);
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

        GitHubButton = (RelativeLayout) view.findViewById(R.id.githubButton);
        GitHubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new GithubCommitsFragment();
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
