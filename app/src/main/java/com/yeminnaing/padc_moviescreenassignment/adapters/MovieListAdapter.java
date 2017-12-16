package com.yeminnaing.padc_moviescreenassignment.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.yeminnaing.padc_moviescreenassignment.R;
import com.yeminnaing.padc_moviescreenassignment.data.vo.MoviesVO;
import com.yeminnaing.padc_moviescreenassignment.delegates.MovieItemDelegate;
import com.yeminnaing.padc_moviescreenassignment.viewholders.MovieViewHolder;

/**
 * Created by yeminnaing on 11/10/17.
 */

public class MovieListAdapter extends BaseRecyclerAdapter<MovieViewHolder, MoviesVO> {

    private MovieItemDelegate movieItemDelegate;

    public MovieListAdapter(MovieItemDelegate movieItemDelegate) {
        this.movieItemDelegate = movieItemDelegate;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_movie, parent, false), movieItemDelegate);
    }

}