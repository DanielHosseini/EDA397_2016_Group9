package com.group9.eda397.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.group9.eda397.R;
import com.group9.eda397.data.TravisServiceFactory;
import com.group9.eda397.data.travis.TravisService;
import com.group9.eda397.model.TravisBuildDetails;
import com.group9.eda397.utils.StringUtils;

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
public class TravisBuildDetailsActivity extends ToolbarActivity {

    private static final String EXTRA_BUILD_ID = "travis_build_id_extra";
    private static final String EXTRA_BUILD_NUMBER = "travis_build_number_extra";
    private static final String EXTRA_REPOSITORY = "travis_build_owner_extra";
    private static final String EXTRA_OWNER = "travis_build_repository_extra";
    @State protected Long buildId;
    @State protected String buildNumber;
    @State protected String repository;
    @State protected String owner;
    @State protected TravisBuildDetails travisBuildDetails;
    @Bind(R.id.tv_author_name) TextView authorTextView;
    @Bind(R.id.tv_author_email) TextView authorEmailTextView;
    @Bind(R.id.tv_branch) TextView branchTextView;
    @Bind(R.id.tv_message) TextView messageTextView;
    @Bind(R.id.tv_commit_link) TextView commitLinkTextView;
    @Bind(R.id.travis_build_details) LinearLayout mainView;
    @Bind(R.id.fl_loading) FrameLayout loadingFrameLayout;
    @Bind(R.id.rl_error) RelativeLayout errorView;
    private TravisService travisService;

    public static Intent getStartingIntent(final Activity startingActivity, final String owner, final String repository, final Long buildId, final String buildNumber) {
        Intent intent = new Intent(startingActivity, TravisBuildDetailsActivity.class);
        intent.putExtra(EXTRA_BUILD_ID, buildId);
        intent.putExtra(EXTRA_OWNER, owner);
        intent.putExtra(EXTRA_REPOSITORY, repository);
        intent.putExtra(EXTRA_BUILD_NUMBER, buildNumber);
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
        this.buildNumber = params.getString(EXTRA_BUILD_NUMBER);
    }

    @Override
    protected void setupToolbar() {
        super.setupToolbar();
        String title = getResources().getString(R.string.title_build) + StringUtils.SPACE + StringUtils.HASHTAG + buildNumber;
        toolbar.setTitle(title);
    }

    private void fetchBuild() {
        showLoadingView();
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

    private void showLoadingView() {
        mainView.setVisibility(View.GONE);
        loadingFrameLayout.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    private void showErrorView() {
        mainView.setVisibility(View.GONE);
        loadingFrameLayout.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }

    private void setupViews() {
        String compareUrl = travisBuildDetails.getCompareUrl();
        int repoEndIndex = compareUrl.indexOf("compare");

        mainView.setVisibility(View.VISIBLE);
        loadingFrameLayout.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);

        authorTextView.setText(travisBuildDetails.getAuthorName());
        authorEmailTextView.setText(travisBuildDetails.getAuthorEmail());
        branchTextView.setText(travisBuildDetails.getBranch());
        messageTextView.setText(travisBuildDetails.getMessage());

        if (compareUrl != null && repoEndIndex > 0) {
            String repoUrl = compareUrl // get only first part of compare_url to get the repo url
                    .substring(0, compareUrl.indexOf("compare"));
            final String url = repoUrl + "commit/" + travisBuildDetails.getCommit();

            commitLinkTextView.setText(url);
            commitLinkTextView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), WebActivity.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
                }
            });
        } else {
            commitLinkTextView.setText("Not available.");
        }
    }
}
