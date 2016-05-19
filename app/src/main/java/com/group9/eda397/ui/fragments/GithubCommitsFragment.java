package com.group9.eda397.ui.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.group9.eda397.R;
import com.group9.eda397.data.GitHubServiceFactory;
import com.group9.eda397.data.github.GitHubService;
import com.group9.eda397.data.github.pagination.Pagination;
import com.group9.eda397.data.github.pagination.PaginationHeaderParser;
import com.group9.eda397.model.GitHubCommitItem;
import com.group9.eda397.ui.activities.GithubCommitDetailsActivity;
import com.group9.eda397.ui.activities.SettingsActivity;
import com.group9.eda397.ui.adapters.GithubCommitAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import icepick.State;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class GithubCommitsFragment extends ViewFragment implements GithubCommitAdapter.ItemClickListener, SwipeRefreshLayout.OnRefreshListener {
    // Variables used for pagination
    private static final int visibleThreshold = 2;
    @State protected String owner;
    @State protected String repository;
    @Bind(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    @Bind(R.id.fl_loading) FrameLayout loadingFrameLayout;
    @Bind(R.id.tv_no_results) TextView noResultsTextView;
    @Bind(R.id.rl_error) RelativeLayout errorView;
    private int firstVisibleItem, visibleItemCount, totalItemCount;
    private int lastFirstVisibleItem;
    private boolean isLoading = false;

    private GitHubService gitHubServcie;
    private GithubCommitAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Pagination pagination;

    public static Fragment newInstance() {
        return new GithubCommitsFragment();
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
        gitHubServcie = GitHubServiceFactory.getService(getActivity().getApplication());
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_github_commits;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAppCompatActivity().getSupportActionBar().setTitle(R.string.title_github_commits);
        setupAdapter();
        setupRecyclerView();
        setupRefreshLayout();
        if (adapter.getList().isEmpty()) {
            load(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SettingsActivity.SHARED_PREF_NAME_DEFAULT, Context.MODE_PRIVATE);
        String owner = sharedPreferences.getString(SettingsActivity.SHARED_PREF_KEY_USERNAME, SettingsActivity.DEFAULT_USERNAME);
        String repository = sharedPreferences.getString(SettingsActivity.SHARED_PREF_KEY_REPOSITORY, SettingsActivity.DEFAULT_REPOSITORY);
        if(this.owner != owner || this.repository != repository){
            this.owner = owner;
            this.repository = repository;
            load(false);
        }
    }

    // TODO change to GithubCommitDetailsActivity when it exists
    @Override
    public void onClickItem(final View view, final int position, final GitHubCommitItem item) {
        startActivity(GithubCommitDetailsActivity.getStartingIntent(getActivity(),item.getAuthor().getUsername(),
                item.getCommit().getAuthor().getEmail(),item.getCommit().getMessage(),item.getSha()));
    }

    @Override
    public void onRefresh() {
        load(true);
    }

    private void load(final boolean pullToRefresh) {
        if (!pullToRefresh) {
            showLoading();
        } else {
            pagination = null;
        }
        getCommits(pullToRefresh);
    }

    private void getCommits(final boolean pullToRefresh) {
        isLoading = true;
        String page = null;
        if (!pullToRefresh && pagination != null) {
            if (pagination.hasNext()) {
                page = String.valueOf(pagination.getNextLink().getPage());
            }
        }
        Call<List<GitHubCommitItem>> call = gitHubServcie.getCommits(owner, repository, page);
        final String finalPage = page;
        call.enqueue(new Callback<List<GitHubCommitItem>>() {
            @Override
            public void onResponse(Call<List<GitHubCommitItem>> call, Response<List<GitHubCommitItem>> response) {
                if (getView() != null) {
                    if (response.isSuccessful()) {
                        if (pullToRefresh) {
                            adapter.clear();
                        }
                        pagination = PaginationHeaderParser.parse(response);
                        List<GitHubCommitItem> gitHubCommits = response.body();
                        if (gitHubCommits.isEmpty()) {
                            showEmptyView();
                        } else {
                            showList();
                            adapter.addAll(gitHubCommits, false);
                        }
                    } else {
                        if (getView() != null) {
                            showErrorView();
                        }
                    }
                    isLoading = false;
                }
            }

            @Override
            public void onFailure(Call<List<GitHubCommitItem>> call, Throwable t) {
                if (getView() != null) {
                    showErrorView();
                }
                // something went completely south (like no internet connection)
                Timber.d(t, "Error fetching Github Commits");
            }
        });
    }

    private void setupAdapter() {
        if (adapter == null) {
            adapter = new GithubCommitAdapter(getContext(), Picasso.with(getActivity()), this);
        }
    }

    private void setupRefreshLayout() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setOnRefreshListener(this);
        }
    }

    private void setupRecyclerView() {
        this.layoutManager = new LinearLayoutManager(getActivity());
        getRecyclerView().setLayoutManager(layoutManager);
        getRecyclerView().setAdapter(adapter);
        getRecyclerView().setItemAnimator(new DefaultItemAnimator());
        getRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //if (dy > 0) { //check for scroll down
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

                lastFirstVisibleItem = firstVisibleItem;
                visibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();

                if (!isLoading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + visibleThreshold)) {
                    if (pagination != null && pagination.hasNext()) {
                        getCommits(false);
                    }
                }
                //}
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

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public TextView getNoResultsTextView() {
        return noResultsTextView;
    }



    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    public FrameLayout getLoadingFrameLayout() {
        return loadingFrameLayout;
    }

}
