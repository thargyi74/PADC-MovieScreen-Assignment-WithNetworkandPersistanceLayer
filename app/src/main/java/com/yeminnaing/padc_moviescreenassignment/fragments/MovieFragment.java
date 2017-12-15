package com.yeminnaing.padc_moviescreenassignment.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yeminnaing.padc_moviescreenassignment.R;
import com.yeminnaing.padc_moviescreenassignment.adapters.MovieListAdapter;
import com.yeminnaing.padc_moviescreenassignment.conponents.EmptyViewPod;
import com.yeminnaing.padc_moviescreenassignment.conponents.SmartRecyclerView;
import com.yeminnaing.padc_moviescreenassignment.conponents.SmartScrollListener;
import com.yeminnaing.padc_moviescreenassignment.data.model.MovieModel;
import com.yeminnaing.padc_moviescreenassignment.data.vo.MoviesVO;
import com.yeminnaing.padc_moviescreenassignment.delegates.MovieItemDelegate;
import com.yeminnaing.padc_moviescreenassignment.event.RestApiEvents;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yeminnaing on 11/10/17.
 */

public class MovieFragment extends BaseFragment implements MovieItemDelegate{


    @BindView(R.id.rv_movies)
    SmartRecyclerView rvMovies;

    @BindView(R.id.vp_empty_view)
    EmptyViewPod vpEmptyMovies;

    private SmartScrollListener mSmartScrollListener;
    private MovieListAdapter movieAdapter;

    public static MovieFragment newInstance() {
        MovieFragment movieFragment = new MovieFragment();
        return movieFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        ButterKnife.bind(this, view);
        rvMovies.setEmptyView(vpEmptyMovies);
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        movieAdapter = new MovieListAdapter();
        rvMovies.setAdapter(movieAdapter);
        MovieModel.getInstance().startLoadingPopularMovies();


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onStop();
    }

    @Override
    public void onTapMovieOverview(MoviesVO movie) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPopularMoviesDataLoaded(RestApiEvents.PopularMoviesDataLoadedEvent event) {
        movieAdapter.appendNewData(event.getLoadedMovies());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvents.ErrorInvokingAPIEvent event) {
        //Snackbar.make(rvMovies, event.getErrorMsg(), Snackbar.LENGTH_INDEFINITE).show();
    }
}