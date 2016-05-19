package com.group9.eda397.ui.fragments;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.group9.eda397.R;

import butterknife.Bind;

/**
 * Created by cadogan on 2016-05-19.
 */
public class ViewFragment extends BaseFragment{

    @Bind(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    @Bind(R.id.fl_loading) FrameLayout loadingFrameLayout;
    @Bind(R.id.tv_no_results) TextView noResultsTextView;
    @Bind(R.id.rl_error) RelativeLayout errorView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_github_commits;
    }

    void showList() {
        swipeRefreshLayout.setRefreshing(false);
        loadingFrameLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
        noResultsTextView.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    void showEmptyView() {
        swipeRefreshLayout.setRefreshing(false);
        noResultsTextView.setVisibility(View.VISIBLE);
        loadingFrameLayout.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    void showLoading() {
        loadingFrameLayout.setVisibility(View.VISIBLE);
        noResultsTextView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.GONE);
    }

    RecyclerView getErrorView(){
        return  recyclerView;
    }
}
