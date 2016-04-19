package com.group9.eda397.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.group9.eda397.R;
import com.group9.eda397.data.TravisServiceFactory;
import com.group9.eda397.data.github.TravisService;
import com.group9.eda397.model.TravisBuildDetails;

import butterknife.Bind;
import icepick.State;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Activity for showing detailed information about a Travis Build
 *
 * @author palmithor
 * @since 19/04/16.
 */
public class TravisBuildDetailsActivity extends BaseActivity {

    private static final String EXTRA_BUILD_ID = "travis_build_id_extra";
    private static final String EXTRA_REPOSITORY = "travis_build_owner_extra";
    private static final String EXTRA_OWNER = "travis_build_repository_extra";
    @State protected Long buildId;
    @State protected String repository;
    @State protected String owner;
    @State protected TravisBuildDetails travisBuildDetails;
    @Bind(R.id.tv_author_name) TextView authorTextView; //TODO Doni uncomment when you've created the layout
    @Bind(R.id.tv_author_email) TextView authorEmailTextView; //TODO Doni uncomment when you've created the layout
    private TravisService travisService;

    public static Intent getStartingIntent(final Activity startingActivity, final String owner, final String repository, final Long buildId) {
        Intent intent = new Intent(startingActivity, TravisBuildDetailsActivity.class);
        intent.putExtra(EXTRA_BUILD_ID, buildId);
        intent.putExtra(EXTRA_OWNER, owner);
        intent.putExtra(EXTRA_REPOSITORY, repository);
        return intent;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        travisService = TravisServiceFactory.getService(getApplication());
        if (travisBuildDetails == null) {
            fetchBuild();
        } else {
            setupViews();
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_travis_build_details;
    }

    @Override
    protected void onExtractParams(@NonNull final Bundle params) {
        super.onExtractParams(params);
        this.buildId = params.getLong(EXTRA_BUILD_ID);
        this.owner = params.getString(EXTRA_OWNER);
        this.repository = params.getString(EXTRA_REPOSITORY);
    }

    private void fetchBuild() {
        Call<TravisBuildDetails> call = travisService.getBuildDetails(owner, repository, buildId);
        call.enqueue(new Callback<TravisBuildDetails>() {
                         @Override
                         public void onResponse(Call<TravisBuildDetails> call, Response<TravisBuildDetails> response) {
                             if (response.isSuccessful()) {
                                 travisBuildDetails = response.body();
                                 setupViews();
                             } else {
                                 showErrorView();
                             }
                         }

                         @Override
                         public void onFailure(Call<TravisBuildDetails> call, Throwable t) {
                             showErrorView();
                             // something went completely south (like no internet connection)
                             Timber.d(t, "Error fetching Travis Builds");
                         }
                     }

        );
    }

    private void showErrorView() {

    }

    private void setupViews() {
        authorTextView.setText(travisBuildDetails.getAuthorName());
        authorEmailTextView.setText(travisBuildDetails.getAuthorEmail());
    }
}
