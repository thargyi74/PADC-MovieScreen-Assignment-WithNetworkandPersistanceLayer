package com.yeminnaing.padc_moviescreenassignment.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yeminnaing.padc_moviescreenassignment.R;
import com.yeminnaing.padc_moviescreenassignment.viewholders.TrailerMovieViewHolder;

/**
 * Created by yeminnaing on 12/16/17.
 */

public class TrailerMovieAdapter extends RecyclerView.Adapter<TrailerMovieViewHolder> {

    private LayoutInflater mlayoutInflater;

    public TrailerMovieAdapter(Context context){
        mlayoutInflater = LayoutInflater.from(context);

    }

    @Override
    public TrailerMovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mlayoutInflater.inflate(R.layout.view_item_trailer, parent, false);
        return new TrailerMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrailerMovieViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
