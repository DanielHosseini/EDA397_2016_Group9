package com.group9.eda397.ui.activities;

import android.app.Activity;
import android.content.Intent;

import com.group9.eda397.R;

/**
 * Activity for showing detailed information about a Travis Build
 *
 * @author palmithor
 * @since 19/04/16.
 */
public class TravisBuildDetailsActivity extends BaseActivity {

    private static final String EXTRA_BUILD_ID = "travis_build_id_extra";

    public static Intent getStartingIntent(final Activity startingActivity, final Long buildId) {
        Intent intent = new Intent(startingActivity, TravisBuildDetailsActivity.class);
        intent.putExtra(EXTRA_BUILD_ID, buildId);
        return intent;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_travis_build_details;
    }
}
