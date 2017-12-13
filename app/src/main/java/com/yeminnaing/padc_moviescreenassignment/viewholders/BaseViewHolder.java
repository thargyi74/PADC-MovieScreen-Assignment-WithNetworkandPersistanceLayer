package com.yeminnaing.padc_moviescreenassignment.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yeminnaing on 12/13/17.
 */

public abstract class BaseViewHolder<W> extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    private W mData;

    BaseViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    public abstract void setData(W data);

    public abstract void bindData();
}