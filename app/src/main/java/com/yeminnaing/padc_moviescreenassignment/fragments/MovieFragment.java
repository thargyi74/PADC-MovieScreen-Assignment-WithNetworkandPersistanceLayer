package com.yeminnaing.padc_moviescreenassignment.fragments;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
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
import com.yeminnaing.padc_moviescreenassignment.activities.MovieDetailsActivity;
import com.yeminnaing.padc_moviescreenassignment.adapters.MovieListAdapter;
import com.yeminnaing.padc_moviescreenassignment.conponents.EmptyViewPod;
import com.yeminnaing.padc_moviescreenassignment.conponents.SmartRecyclerView;
import com.yeminnaing.padc_moviescreenassignment.conponents.SmartScrollListener;
import com.yeminnaing.padc_moviescreenassignment.data.model.MovieModel;
import com.yeminnaing.padc_moviescreenassignment.data.vo.MoviesVO;
import com.yeminnaing.padc_moviescreenassignment.delegates.MovieItemDelegate;
import com.yeminnaing.padc_moviescreenassignment.event.RestApiEvents;
import com.yeminnaing.padc_moviescreenassignment.persistance.Contract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yeminnaing on 11/10/17.
 */

public class MovieFragment extends BaseFragment implements MovieItemDelegate, android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor>{


    @BindView(R.id.rv_movies)
    SmartRecyclerView rvMovies;

    @BindView(R.id.vp_empty_view)
    EmptyViewPod vpEmptyMovies;

    private SmartScrollListener mSmartScrollListener;
    private MovieListAdapter movieAdapter;

    private static final int MOVIES_LIST_LOADER_ID =1001;

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
        movieAdapter = new MovieListAdapter(this);
        rvMovies.setAdapter(movieAdapter);
        MovieModel.getInstance().startLoadingPopularMovies(getContext());
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(MOVIES_LIST_LOADER_ID, null, this);
        super.onActivityCreated(savedInstanceState);
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

        Intent intent = MovieDetailsActivity.newIntent(getContext());
        startActivity(intent);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPopularMoviesDataLoaded(RestApiEvents.PopularMoviesDataLoadedEvent event) {
        /*movieAdapter.appendNewData(event.getLoadedMovies());*/
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvents.ErrorInvokingAPIEvent event) {
        //Snackbar.make(rvMovies, event.getErrorMsg(), Snackbar.LENGTH_INDEFINITE).show();
    }


    @Override
    public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new android.support.v4.content.CursorLoader(getActivity(),
                Contract.MovieEntry.CONTENT_URI,
                null,
                null,
                null,
                null
                );
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor data) {
        if(data != null && data.moveToFirst()){
            List<MoviesVO> moviesList = new ArrayList<>();

            do{

                MoviesVO movies = MoviesVO.parseFromCursor(data);
                moviesList.add(movies);
            }while (data.moveToNext());

            movieAdapter.appendNewData(moviesList);
        }
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {

    }
}