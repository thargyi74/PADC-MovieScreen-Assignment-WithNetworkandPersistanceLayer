package com.yeminnaing.padc_moviescreenassignment.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yeminnaing.padc_moviescreenassignment.R;

/**
 * Created by yeminnaing on 11/10/17.
 */

public class ViewPodMoviePopularity extends LinearLayout {

    private static final int MAX_POPULARITY_COUNT = 10;

    public ViewPodMoviePopularity(Context context) {
        super(context);
    }

    public ViewPodMoviePopularity(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewPodMoviePopularity(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        for (int i = 0; i < MAX_POPULARITY_COUNT; i++) {
            ImageView iv = new ImageView(getContext());
            iv.setImageResource(R.drawable.movie_popularity_icon_star);
            if (i != 0) {
                iv.setPadding((int) getContext().getResources().getDimension(R.dimen.margin_small), 0, 0, 0);
            }
            iv.setVisibility(View.INVISIBLE);
            addView(iv);
        }
    }

    public void drawPopularityIcons(float popularity) {
        hideAllViews();
        int popularityCount = (int) (popularity / 10);
        popularityCount += 2; //by default, there will be 3 stars.
        if(popularityCount > 0) {
            setVisibility(View.VISIBLE);
        } else {
            setVisibility(View.GONE);
        }
        for (int i = 0; i < popularityCount; i++) {
            View view = getChildAt(i);
            if (view != null) {
                view.setVisibility(View.VISIBLE);
            }
        }
    }

    private void hideAllViews() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (view != null) {
                view.setVisibility(View.INVISIBLE);
            }
        }
    }
}
