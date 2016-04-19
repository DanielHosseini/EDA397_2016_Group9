package com.group9.eda397.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
import com.group9.eda397.data.TravisServiceFactory;
import com.group9.eda397.data.github.TravisService;
import com.group9.eda397.model.TravisBuild;
import com.group9.eda397.ui.adapters.TravisBuildsAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
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

    public static final String DEFAULT_OWNER = "DanielHosseini";
    public static final String DEFAULT_REPOSITORY = "EDA397_2016_Group9";
    @State protected String owner;
    @State protected String repository;
    @Bind(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    @Bind(R.id.fl_loading) FrameLayout loadingFrameLayout;
    @Bind(R.id.tv_no_results) TextView noResultsTextView;
    @Bind(R.id.rl_error) RelativeLayout errorView;
    private TravisBuildsAdapter adapter;
    private LinearLayoutManager layoutManager;
    private TravisService travisService;

    public static Fragment newInstance() {
        return new TravisBuildsFragment();
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.owner = DEFAULT_OWNER;
            this.repository = DEFAULT_REPOSITORY;
        }
        travisService = TravisServiceFactory.getService(getActivity().getApplication());
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAppCompatActivity().getSupportActionBar().setTitle(R.string.title_travis_builds);
        setupAdapter();
        setupRecyclerView();
        setupRefreshLayout();
        load(false);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_travis_builds;
    }

    @Override
    public void onClickItem(final View view, final int position, final TravisBuild item) {
        Snackbar.make(getView(), "Not yet implemented, should show details view", Snackbar.LENGTH_SHORT).show();
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
}
