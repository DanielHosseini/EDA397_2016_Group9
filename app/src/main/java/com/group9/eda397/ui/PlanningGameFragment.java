package com.group9.eda397.ui;

        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentTransaction;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import com.group9.eda397.R;
        import com.group9.eda397.ui.fragments.BaseFragment;

        import java.util.Timer;
        import java.util.TimerTask;

        import butterknife.Bind;
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

        return view;
    }



    protected int getLayout() {
        return R.layout.fragment_planning_game;
    }
}