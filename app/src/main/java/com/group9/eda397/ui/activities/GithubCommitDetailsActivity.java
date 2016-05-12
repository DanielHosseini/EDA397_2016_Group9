package com.group9.eda397.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.group9.eda397.R;

public class GithubCommitDetailsActivity extends ToolbarActivity {

    // Setting up variables
    private static final String EXTRA_OWNER = "github_commit_owner_extra";
    private static final String EXTRA_EMAIL = "github_commit_author_email";
    private static final String EXTRA_COMMIT = "github_commit";
    private static final String EXTRA_SHA = "github_committ_sha";

    public static Intent getStartingIntent(final Activity startingActivity,
                                           final String owner, final String email,
                                           final String commit, final String sha) {
        Intent intent = new Intent(startingActivity, GithubCommitDetailsActivity.class);
        intent.putExtra(EXTRA_OWNER, owner);
        intent.putExtra(EXTRA_EMAIL, email);
        intent.putExtra(EXTRA_COMMIT, commit);
        intent.putExtra(EXTRA_SHA, sha);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showInfo();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_github_commit_details;
    }

    @Override
    protected void setupToolbar() {
        super.setupToolbar();
        toolbar.setTitle(R.string.title_github_commit_details);
    }

    private void showInfo() {
        Intent intent = getIntent();
        TextView name = (TextView) findViewById(R.id.github_author_name);
        name.setText(intent.getStringExtra(EXTRA_OWNER));
        TextView email_name = (TextView) findViewById(R.id.github_author_email);
        email_name.setText(intent.getStringExtra(EXTRA_EMAIL));
        TextView commit = (TextView) findViewById(R.id.github_commit_comments);
        commit.setText(intent.getStringExtra(EXTRA_COMMIT));
        TextView sha = (TextView) findViewById(R.id.github_commit_sha);
        sha.setText(intent.getStringExtra(EXTRA_SHA));
    }

}
