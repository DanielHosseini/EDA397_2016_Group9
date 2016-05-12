package com.group9.eda397.ui.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.group9.eda397.R;
import com.group9.eda397.data.GitHubServiceFactory;
import com.group9.eda397.data.TravisServiceFactory;
import com.group9.eda397.data.github.GitHubService;
import com.group9.eda397.data.travis.TravisService;
import com.group9.eda397.model.TravisBuild;
import com.group9.eda397.ui.activities.SettingsActivity;
import com.group9.eda397.ui.activities.TravisBuildDetailsActivity;
import com.group9.eda397.ui.adapters.TravisBuildsAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import icepick.State;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;


/**
 * Fragment for displaying the newest Travis builds
 *
 * @author palmithor
 * @since 16/04/16.
 */
public class TravisBuildsFragment extends BaseFragment implements TravisBuildsAdapter.ItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @State protected String owner;
    @State protected String repository;
    @Bind(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    @Bind(R.id.fl_loading) FrameLayout loadingFrameLayout;
    @Bind(R.id.tv_no_results) TextView noResultsTextView;
    @Bind(R.id.rl_error) RelativeLayout errorView;
    @Bind(R.id.set_travis_fab) FloatingActionButton travisFab;
    private TravisBuildsAdapter adapter;
    private LinearLayoutManager layoutManager;
    private TravisService travisService;
    private GitHubService gitHubServcie;

    public static Fragment newInstance() {
        return new TravisBuildsFragment();
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SettingsActivity.SHARED_PREF_NAME_DEFAULT, Context.MODE_PRIVATE);
            String owner = sharedPreferences.getString(SettingsActivity.SHARED_PREF_KEY_USERNAME, SettingsActivity.DEFAULT_USERNAME);
            String repository = sharedPreferences.getString(SettingsActivity.SHARED_PREF_KEY_REPOSITORY, SettingsActivity.DEFAULT_REPOSITORY);
            this.owner = owner;
            this.repository = repository;
        }
        travisService = TravisServiceFactory.getService(getActivity().getApplication());
        gitHubServcie = GitHubServiceFactory.getService(getActivity().getApplication());
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAppCompatActivity().getSupportActionBar().setTitle(R.string.title_travis_builds);
        setupAdapter();
        setupRecyclerView();
        setupRefreshLayout();
        setupTravisFab();
        if (adapter.getList().isEmpty()) {
            load(false);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_travis_builds;
    }

    @Override
    public void onClickItem(final View view, final int position, final TravisBuild item) {
        startActivity(TravisBuildDetailsActivity.getStartingIntent(getActivity(), owner, repository, item.getId(), item.getBuildNumber()));
    }

    @Override
    public void onRefresh() {
        load(true);
    }

    private void load(final boolean pullToRefresh) {
        if (!pullToRefresh) {
            showLoading();
        }
        Call<List<TravisBuild>> call = travisService.getBuilds(owner, repository);
        call.enqueue(new Callback<List<TravisBuild>>() {
            @Override
            public void onResponse(Call<List<TravisBuild>> call, Response<List<TravisBuild>> response) {
                if (getView() != null) {
                    if (response.isSuccessful()) {
                        adapter.clear();
                        List<TravisBuild> travisBuilds = response.body();
                        if (travisBuilds.isEmpty()) {
                            showEmptyView();
                        } else {
                            showList();
                            adapter.addAll(travisBuilds, false);
                        }
                    } else {
                        if (getView() != null) {
                            showErrorView();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<TravisBuild>> call, Throwable t) {
                if (getView() != null) {
                    showErrorView();
                }
                // something went completely south (like no internet connection)
                Timber.d(t, "Error fetching Travis Builds");
            }
        });
    }

    private void showErrorView() {
        swipeRefreshLayout.setRefreshing(false);
        loadingFrameLayout.setVisibility(View.GONE);
        noResultsTextView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    private void showList() {
        swipeRefreshLayout.setRefreshing(false);
        loadingFrameLayout.setVisibility(View.GONE);
        noResultsTextView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    private void showEmptyView() {
        swipeRefreshLayout.setRefreshing(false);
        noResultsTextView.setVisibility(View.VISIBLE);
        loadingFrameLayout.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    private void showLoading() {
        loadingFrameLayout.setVisibility(View.VISIBLE);
        noResultsTextView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.GONE);
    }

    private void setupRefreshLayout() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setOnRefreshListener(this);
        }
    }

    private void setupRecyclerView() {
        this.layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void setupAdapter() {
        if (adapter == null) {
            adapter = new TravisBuildsAdapter(getContext(), Picasso.with(getActivity()), this);
        }
    }

    private void changeTravisRepo(String owner, String repository){
        this.owner = owner;
        this.repository = repository;
        load(false);
    }

    private void setupTravisFab() {
        travisFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTravisRepoConfiguration();
            }
        });
    }

    private void showTravisRepoConfiguration() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.dialog_travis_configuration, null);
        builder.setView(dialogView);
        builder.setTitle("Change Travis Configuration");

        final AlertDialog dialog = builder.create();
        dialog.show();

        Button applyButton = ButterKnife.findById(dialog,R.id.travis_apply_button);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ownerText = ButterKnife.findById(dialogView, R.id.travis_owner);
                EditText repoText = ButterKnife.findById(dialogView, R.id.travis_repository);
                changeTravisRepo(ownerText.getText().toString(),repoText.getText().toString());
                dialog.dismiss();
            }
        });

        Button cancelButton = ButterKnife.findById(dialog, R.id.travis_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dialog.cancel();
            }
        });
    }
}
