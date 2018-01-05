package com.yeminnaing.padc_moviescreenassignment.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.yeminnaing.padc_moviescreenassignment.data.vo.MoviesVO;
import com.yeminnaing.padc_moviescreenassignment.viewholders.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeminnaing on 12/13/17.
 */

public class BaseRecyclerAdapter<T extends BaseViewHolder, W> extends RecyclerView.Adapter<T> {

    protected List<W> mData;
    private List<MoviesVO> newData;

    BaseRecyclerAdapter() {
        mData = new ArrayList<>();
    }

    @Override public T onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override public void onBindViewHolder(T holder, int position) {
        holder.setData(mData.get(position));
        //holder.bindData();
    }

    @Override public int getItemCount() {
        return mData.size();
    }

    public void appendNewData(List<W> newData) {
        mData.addAll(newData);
        notifyDataSetChanged();
    }

    public W getItemAt(int position) {
        if (position < mData.size() - 1)
            return mData.get(position);

        return null;
    }

    public List<W> getItems() {
        if (mData == null)
            return new ArrayList<>();

        return mData;
    }

    public void removeData(W data) {
        mData.remove(data);
        notifyDataSetChanged();
    }

    public void addNewData(W data) {
        mData.add(data);
        notifyDataSetChanged();
    }

    public void clearData() {
        mData = new ArrayList<>();
        notifyDataSetChanged();
    }

    public void setNewData(List<MoviesVO> newData) {
        this.newData = newData;
        notifyDataSetChanged();
    }
}
