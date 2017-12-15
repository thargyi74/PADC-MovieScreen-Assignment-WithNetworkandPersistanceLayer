package com.yeminnaing.padc_moviescreenassignment.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yeminnaing.padc_moviescreenassignment.R;
import com.yeminnaing.padc_moviescreenassignment.data.vo.MoviesVO;
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

    public MovieViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(MoviesVO data) {
        mPopularMovie = data;
    }

    @Override
    public void bindData() {

        tvRating.setText(String.valueOf(mPopularMovie.getVoteAverage()));
        tvTitle.setText(mPopularMovie.getTitle());

        Glide.with(itemView.getContext())
                .load(AppConstants.IMAGE_BASE_URL + mPopularMovie.getPosterPath())
                .into(ivPoster);
    }

    @Override
    public void onClick(View v) {

    }
}
