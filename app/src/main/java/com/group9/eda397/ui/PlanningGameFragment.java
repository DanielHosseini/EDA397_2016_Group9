package com.group9.eda397.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.group9.eda397.R;
import com.group9.eda397.ui.activities.CardPresenter;
import com.group9.eda397.ui.fragments.BaseFragment;

import butterknife.ButterKnife;


/**
 * Created by ivar on 2016-04-21.
 */
public class PlanningGameFragment extends BaseFragment {

    private static final String ARG_TEXT = PlanningGameFragment.class.getCanonicalName() + ".arg_text";
    //@Bind(R.id.text) TextView textView;

    public static PlanningGameFragment newInstance(final String text) {
        PlanningGameFragment fragment = new PlanningGameFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TEXT, text);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_planning_game, container, false);
        ButterKnife.bind(this, view);
        //textView.setText(getArguments().getString(ARG_TEXT));

        Button btn_fib = (Button) view.findViewById(R.id.button_fibonacci);
        Button btn_powertwo = (Button) view.findViewById(R.id.button_power_of_two);
        Button btn_standard = (Button) view.findViewById(R.id.button_standard);

        btn_fib.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Fib Button");
                Intent intent = new Intent(getActivity(), CardPresenter.class  );
                startActivity(intent);
            }
        });

        btn_powertwo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Power Button");
            }
        });

        btn_standard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Standard Button");
            }
        });

        return view;
    }

    protected int getLayout() {
        return R.layout.fragment_planning_game;
    }
}