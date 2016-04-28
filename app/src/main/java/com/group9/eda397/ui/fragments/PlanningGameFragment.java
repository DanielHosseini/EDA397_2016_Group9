package com.group9.eda397.ui.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import com.group9.eda397.R;
import com.group9.eda397.ui.activities.CardPresenter;

import butterknife.ButterKnife;


/**
 * Created by ivar on 2016-04-21.
 */
public class PlanningGameFragment extends BaseFragment {

    public static Fragment newInstance() {
        return new PlanningGameFragment();
    }
    
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_planning_game, container, false);
        ButterKnife.bind(this, view);
        getAppCompatActivity().getSupportActionBar().setTitle(R.string.title_planning_game);
        Button btn_fib = (Button) view.findViewById(R.id.button_fibonacci);
        Button btn_powertwo = (Button) view.findViewById(R.id.button_power_of_two);
        Button btn_standard = (Button) view.findViewById(R.id.button_standard);

        btn_fib.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Resources res = getResources();
                final String [] deck = res.getStringArray(R.array.string_array_fibonacci_deck);

                openCardChooserDialog(deck);
            }
        });

        btn_powertwo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Resources res = getResources();
                final String [] deck = res.getStringArray(R.array.string_array_power_of_two_deck);

                openCardChooserDialog(deck);
            }
        });

        btn_standard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Resources res = getResources();
                final String [] deck = res.getStringArray(R.array.string_array_standard_deck);

                openCardChooserDialog(deck);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hideFab();
    }

    public void openCardChooserDialog (final String[] cards) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        final NumberPicker picker = new NumberPicker(getActivity());
        picker.setMinValue(0);
        picker.setMaxValue(cards.length-1);
        picker.setDisplayedValues(cards);

        alertDialogBuilder.setView(picker);

        alertDialogBuilder.setPositiveButton(R.string.label_select, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                int index = picker.getValue();
                System.out.println(cards[index]);

                Intent intent = new Intent(getActivity(), CardPresenter.class  );
                intent.putExtra("numberToShow", cards[index]);
                startActivity(intent);
            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    protected int getLayout() {
        return R.layout.fragment_planning_game;
    }
}