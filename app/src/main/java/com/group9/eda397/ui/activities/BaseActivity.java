package com.group9.eda397.ui.activities;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import icepick.Icepick;


/**
 * Base Activity that should be extended by all other activities.
 * <p/>
 * The activity binds Butterknife to the view set as well as forcing the layout to be set
 * in a structured way.
 * <p/>
 * It also introduces a method for extracting parameters from the bundle.
 * <p/>
 * Finally it restores and stores the instance state using Icepick
 *
 * @author palmithor
 * @since 19/04/16.
 */
public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Bundle params = getIntent().getExtras();
        if (params != null) {
            onExtractParams(params);
        }

        super.onCreate(savedInstanceState);

        setContentView(getLayout());

        ButterKnife.bind(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }


    protected void onExtractParams(@NonNull Bundle params) {
        // default no implementation
    }

    @LayoutRes
    protected abstract int getLayout();

}
