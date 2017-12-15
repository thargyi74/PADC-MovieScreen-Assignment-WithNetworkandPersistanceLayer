package com.yeminnaing.padc_moviescreenassignment.conponents;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yeminnaing.padc_moviescreenassignment.R;
import com.yeminnaing.padc_moviescreenassignment.data.vo.GenreVO;

import java.util.List;

/**
 * Created by yeminnaing on 12/15/17.
 */

public class ViewPodGenreListDetail extends LinearLayout {

    public ViewPodGenreListDetail(Context context) {
        super(context);
    }

    public ViewPodGenreListDetail(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewPodGenreListDetail(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void setGenreList(List<GenreVO> genreList, int textColor) {
        if (genreList == null || genreList.size() == 0)
            return;

        if (getChildCount() == 0) {
            for (int position = 0; position < genreList.size(); position++) {
                //TextView tv = new SmallDashUnderlineTextView(getContext(), textColor);
                TextView tv = new TextView(getContext());
                tv.setBackgroundResource(R.drawable.genre_bg);
                tv.setTextColor(getContext().getResources().getColor(R.color.primary_text));

                int padding = (int) getContext().getResources().getDimension(R.dimen.margin_medium);
                tv.setPadding(padding, 0, padding, 0);

                GenreVO genre = genreList.get(position);
                if(genre != null) {
                    tv.setText(genre.getName());
                    addView(tv);

                    if (position < genreList.size() - 1) {
                        TextView tvSeparator = new TextView(getContext());
                        tvSeparator.setTextSize(14);
                        tvSeparator.setTextColor(textColor);
                        tvSeparator.setText(" | ");
                        addView(tvSeparator);
                    }
                }
            }
        }
    }
}