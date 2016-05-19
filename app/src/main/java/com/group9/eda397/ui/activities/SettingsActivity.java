package com.group9.eda397.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.group9.eda397.R;

import butterknife.Bind;
import icepick.State;

/**
 * @author palmithor
 * @since 12/05/16.
 */
public class SettingsActivity extends ToolbarActivity {

    public static final String DEFAULT_USERNAME = "DanielHosseini";
    public static final String DEFAULT_REPOSITORY = "EDA397_2016_Group9";

    public static final String SHARED_PREF_NAME_DEFAULT = "default";
    public static final String SHARED_PREF_KEY_USERNAME = "shared_pref_key_username";
    public static final String SHARED_PREF_KEY_REPOSITORY = "shared_pref_key_repository";

    @Bind(R.id.et_username) EditText usernameEditText;
    @Bind(R.id.et_repository) EditText repositoryEditText;

    @State String username;
    @State String repository;

    private SharedPreferences sharedPref;

    public static Intent getStartingIntent(final Activity startingActivity) {
        return new Intent(startingActivity, SettingsActivity.class);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        sharedPref = this.getSharedPreferences(
                SHARED_PREF_NAME_DEFAULT, Context.MODE_PRIVATE);
        if (savedInstanceState == null) {
            repository = sharedPref.getString(SHARED_PREF_KEY_REPOSITORY, DEFAULT_REPOSITORY);
            username = sharedPref.getString(SHARED_PREF_KEY_USERNAME, DEFAULT_USERNAME);
        }
        usernameEditText.setText(username);
        repositoryEditText.setText(repository);
        setupEditText();

    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            if (usernameEditText.length() <= 0 && repositoryEditText.length() <= 0) {
                Snackbar.make(usernameEditText, R.string.error_username_repository_must_be_set, Snackbar.LENGTH_SHORT).show();
            } else {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(SHARED_PREF_KEY_REPOSITORY, repository);
                editor.putString(SHARED_PREF_KEY_USERNAME, username);
                editor.commit();
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_settings;
    }

    @Override
    protected Integer getMenuResourceId() {
        return R.menu.activity_settings;
    }

    @Override
    protected void setupToolbar() {
        super.setupToolbar();
        toolbar.setTitle(R.string.title_settings);
    }

    private void setupEditText() {
    /* To restrict Space Bar in Keyboard */
        InputFilter filter = new InputFilter() {
            public CharSequence filter(final CharSequence source, final int start, final int end,
                                       final Spanned dest, final int dstart, final int dend) {
                for (int i = start; i < end; i++) {
                    if (Character.isWhitespace(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };
        usernameEditText.setFilters(new InputFilter[]{filter});
        repositoryEditText.setFilters(new InputFilter[]{filter});
        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                username = s.toString();
            }

            @Override
            public void afterTextChanged(final Editable s) {

            }
        });

        repositoryEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                repository = s.toString();
            }

            @Override
            public void afterTextChanged(final Editable s) {

            }
        });
    }
}
