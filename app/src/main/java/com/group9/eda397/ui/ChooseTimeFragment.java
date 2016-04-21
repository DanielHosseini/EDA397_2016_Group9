package com.group9.eda397.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.group9.eda397.R;
import com.group9.eda397.ui.fragments.BaseFragment;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Isabelle on 2016-04-21.
 */
public class ChooseTimeFragment extends BaseFragment {

    private static final String ARG_TEXT = ChooseTimeFragment.class.getCanonicalName() + ".arg_text";
    @Bind(R.id.editText) EditText editText;
    @Bind(R.id.startButton) Button startButton;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.countdown_chooser, container, false);
        ButterKnife.bind(this, view);
        editText.setText("00");
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.setText(editText.getText());
            }
        });
        return view;
    }

    public static ChooseTimeFragment newInstance(final String text) {
        ChooseTimeFragment fragment = new ChooseTimeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TEXT, text);
        fragment.setArguments(bundle);
        return fragment;
    }

    public int getLayout(){
        return R.layout.countdown_chooser;
    }

}
