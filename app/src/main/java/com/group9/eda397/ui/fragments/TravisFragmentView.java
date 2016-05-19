package com.group9.eda397.ui.fragments;

import android.view.View;

public class TravisFragmentView {
    private final TravisBuildsFragment travisBuildsFragment;

    public TravisFragmentView(TravisBuildsFragment travisBuildsFragment) {
        this.travisBuildsFragment = travisBuildsFragment;
    }

    void showList() {
        travisBuildsFragment.getSwipeRefreshLayout().setRefreshing(false);
        travisBuildsFragment.getLoadingFrameLayout().setVisibility(View.GONE);
        travisBuildsFragment.getNoResultsTextView().setVisibility(View.GONE);
        travisBuildsFragment.getErrorView().setVisibility(View.GONE);
        travisBuildsFragment.getRecyclerView().setVisibility(View.VISIBLE);
        travisBuildsFragment.getSwipeRefreshLayout().setVisibility(View.VISIBLE);
    }

    void showEmptyView() {
        travisBuildsFragment.getSwipeRefreshLayout().setRefreshing(false);
        travisBuildsFragment.getNoResultsTextView().setVisibility(View.VISIBLE);
        travisBuildsFragment.getLoadingFrameLayout().setVisibility(View.GONE);
        travisBuildsFragment.getErrorView().setVisibility(View.GONE);
        travisBuildsFragment.getRecyclerView().setVisibility(View.VISIBLE);
        travisBuildsFragment.getSwipeRefreshLayout().setVisibility(View.VISIBLE);
    }

    void showLoading() {
        travisBuildsFragment.getLoadingFrameLayout().setVisibility(View.VISIBLE);
        travisBuildsFragment.getNoResultsTextView().setVisibility(View.GONE);
        travisBuildsFragment.getErrorView().setVisibility(View.GONE);
        travisBuildsFragment.getRecyclerView().setVisibility(View.GONE);
        travisBuildsFragment.getSwipeRefreshLayout().setVisibility(View.GONE);
    }
}