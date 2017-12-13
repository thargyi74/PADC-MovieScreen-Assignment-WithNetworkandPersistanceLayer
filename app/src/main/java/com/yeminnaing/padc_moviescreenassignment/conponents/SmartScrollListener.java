package com.yeminnaing.padc_moviescreenassignment.conponents;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by yeminnaing on 12/13/17.
 */

public class SmartScrollListener extends RecyclerView.OnScrollListener {

    public interface OnSmartScrollListener {
        void onListEndReach();
    }

    private int visibleItemCount, pastVisibleItem, totalItemCount, previousTotalItem;
    private boolean isListEndReach = false;
    private int previousDy, currentDy;
    private boolean firstTime = true;

    private OnSmartScrollListener onSmartScrollListener;

    public SmartScrollListener(OnSmartScrollListener onSmartScrollListener) {
        this.onSmartScrollListener = onSmartScrollListener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        currentDy = dy;
        if (currentDy > previousDy) {
            // from top to bottom
        } else {
            // from bottom to top
            isListEndReach = false;
        }

        visibleItemCount = recyclerView.getLayoutManager().getChildCount();
        totalItemCount = recyclerView.getLayoutManager().getItemCount();
        pastVisibleItem =
                ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

        previousDy = currentDy;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (firstTime) {
                if ((visibleItemCount + pastVisibleItem) >= totalItemCount && !isListEndReach) {
                    isListEndReach = true;
                    onSmartScrollListener.onListEndReach();
                    firstTime = false;
                    previousTotalItem = totalItemCount;
                }
            } else {
                if (totalItemCount > previousTotalItem) {
                    firstTime = true;
                }
            }
        }
    }
}