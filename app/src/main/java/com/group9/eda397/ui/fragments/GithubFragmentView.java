package com.group9.eda397.ui.fragments;


import android.view.View;

public class GithubFragmentView {
    private final GithubCommitsFragment githubCommitsFragment;


    public GithubFragmentView(GithubCommitsFragment githubCommitsFragment) {
        this.githubCommitsFragment = githubCommitsFragment;
    }



    void showList() {
        githubCommitsFragment.getSwipeRefreshLayout().setRefreshing(false);
        githubCommitsFragment.getLoadingFrameLayout().setVisibility(View.GONE);
        githubCommitsFragment.getNoResultsTextView().setVisibility(View.GONE);
        githubCommitsFragment.getErrorView().setVisibility(View.GONE);
        githubCommitsFragment.getRecyclerView().setVisibility(View.VISIBLE);
        githubCommitsFragment.getSwipeRefreshLayout().setVisibility(View.VISIBLE);
    }

    void showEmptyView() {
        githubCommitsFragment.getSwipeRefreshLayout().setRefreshing(false);
        githubCommitsFragment.getNoResultsTextView().setVisibility(View.VISIBLE);
        githubCommitsFragment.getLoadingFrameLayout().setVisibility(View.GONE);
        githubCommitsFragment.getErrorView().setVisibility(View.GONE);
        githubCommitsFragment.getRecyclerView().setVisibility(View.VISIBLE);
        githubCommitsFragment.getSwipeRefreshLayout().setVisibility(View.VISIBLE);
    }

    void showLoading() {
        githubCommitsFragment.getLoadingFrameLayout().setVisibility(View.VISIBLE);
        githubCommitsFragment.getNoResultsTextView().setVisibility(View.GONE);
        githubCommitsFragment.getErrorView().setVisibility(View.GONE);
        githubCommitsFragment.getRecyclerView().setVisibility(View.GONE);
        githubCommitsFragment.getSwipeRefreshLayout().setVisibility(View.GONE);
    }
}