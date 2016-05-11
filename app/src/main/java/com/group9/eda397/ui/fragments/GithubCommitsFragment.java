package com.group9.eda397.ui.fragments;


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
import com.group9.eda397.data.TravisServiceFactory;
import com.group9.eda397.data.github.GitHubService;
import com.group9.eda397.model.GitHubCommit;
import com.group9.eda397.model.GitHubCommitItem;
import com.group9.eda397.model.TravisBuild;
import com.group9.eda397.ui.activities.TravisBuildDetailsActivity;
import com.group9.eda397.ui.adapters.GithubCommitAdapter;
import com.group9.eda397.ui.adapters.TravisBuildsAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import icepick.State;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class GithubCommitsFragment extends BaseFragment implements GithubCommitAdapter.ItemClickListener, SwipeRefreshLayout.OnRefreshListener {
    private GitHubService gitHubServcie;
    private GithubCommitAdapter adapter;
    private LinearLayoutManager layoutManager;
    @State protected String owner;
    @State protected String repository;

    @Bind(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    @Bind(R.id.fl_loading) FrameLayout loadingFrameLayout;
    @Bind(R.id.tv_no_results) TextView noResultsTextView;
    @Bind(R.id.rl_error) RelativeLayout errorView;

    public static Fragment newInstance() {
        return new GithubCommitsFragment();
    }


    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.owner = TravisBuildsFragment.DEFAULT_OWNER;
            this.repository = TravisBuildsFragment.DEFAULT_REPOSITORY;
        }
        gitHubServcie = GitHubServiceFactory.getService(getActivity().getApplication());
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_travis_builds;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAppCompatActivity().getSupportActionBar().setTitle(R.string.title_github_commits);
        setupAdapter();
        setupRecyclerView();
        setupRefreshLayout();
        hideFab();
        if (adapter.getList().isEmpty()) {
            load(false);
        }
    }

    // TODO change to GithubCommitDetailsActivity when it exists
    @Override
    public void onClickItem(final View view, final int position, final GitHubCommitItem item) {
        //startActivity(TravisBuildDetailsActivity.getStartingIntent(getActivity(), owner, repository, item.getId(), item.getBuildNumber()));
    }

    @Override
    public void onRefresh() {
        load(true);
    }

    private void load(final boolean pullToRefresh) {
        if (!pullToRefresh) {
            showLoading();
        }
        Call<List<GitHubCommitItem>> call = gitHubServcie.getCommits(owner, repository);
        call.enqueue(new Callback<List<GitHubCommitItem>>() {
            @Override
            public void onResponse(Call<List<GitHubCommitItem>> call, Response<List<GitHubCommitItem>> response) {
                if (getView() != null) {
                    if (response.isSuccessful()) {
                        adapter.clear();
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

    private void showErrorView() {
        swipeRefreshLayout.setRefreshing(false);
        loadingFrameLayout.setVisibility(View.GONE);
        noResultsTextView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }
}
