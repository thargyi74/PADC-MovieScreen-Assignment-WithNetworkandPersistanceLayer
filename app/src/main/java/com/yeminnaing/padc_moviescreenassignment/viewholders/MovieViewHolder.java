package com.yeminnaing.padc_moviescreenassignment.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yeminnaing.padc_moviescreenassignment.R;
import com.yeminnaing.padc_moviescreenassignment.data.vo.MoviesVO;
import com.yeminnaing.padc_moviescreenassignment.delegates.MovieItemDelegate;
import com.yeminnaing.padc_moviescreenassignment.utils.AppConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yeminnaing on 11/10/17.
 */

public class MovieViewHolder extends BaseViewHolder<MoviesVO> {

    @BindView(R.id.iv_poster)
    ImageView ivPoster;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rating)
    TextView tvRating;


    private MoviesVO mPopularMovie;

    private MovieItemDelegate mDelegate;

    public MovieViewHolder(View itemView, MovieItemDelegate movieItemDelegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mDelegate = movieItemDelegate;
    }

    @Override
    public void setData(MoviesVO data) {
        mPopularMovie = data;


        if(data != null){
            if(data.getTitle() != null){
                tvTitle.setText(data.getTitle());
            }
            if(data.getPosterPath() != null){
                Glide.with(itemView.getContext())
                        .load(AppConstants.IMAGE_BASE_URL + mPopularMovie.getPosterPath())
                        .into(ivPoster);

            }

            tvRating.setText(String.valueOf(mPopularMovie.getVoteAverage()));

        }

    }

    @Override
    public void bindData() {


      /*  tvTitle.setText(mPopularMovie.getTitle());

        Glide.with(itemView.getContext())
                .load(AppConstants.IMAGE_BASE_URL + mPopularMovie.getPosterPath())
                .into(ivPoster);*/
    }

    @Override
    public void onClick(View v) {

       mDelegate.onTapMovieOverview(mPopularMovie);


    }
}
