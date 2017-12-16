package com.yeminnaing.padc_moviescreenassignment.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yeminnaing.padc_moviescreenassignment.R;
import com.yeminnaing.padc_moviescreenassignment.data.vo.MoviesVO;
import com.yeminnaing.padc_moviescreenassignment.viewholders.MovieViewHolder;

/**
 * Created by yeminnaing on 11/10/17.
 */

public class MovieListAdapter extends BaseRecyclerAdapter<MovieViewHolder, MoviesVO> {

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_movie, parent, false));
    }

}