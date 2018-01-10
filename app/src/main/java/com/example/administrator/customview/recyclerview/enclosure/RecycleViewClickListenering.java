package com.example.administrator.customview.recyclerview.enclosure;

import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * <pre>
 *
 *   @author   :   Alex
 *   @e_mail   :   18238818283@sina.cn
 *   @time     :   2018/01/09
 *   @desc     :
 *   @version  :   V 1.0.9
 */

public  abstract class RecycleViewClickListenering implements RecyclerView.OnItemTouchListener {

    private GestureDetector detector;

    public RecycleViewClickListenering( RecyclerView recyclerView) {
        this.detector = new GestureDetector(recyclerView.getContext(),new GesturListenering(recyclerView,this));
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        detector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        detector.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    /**
     * click
     * @param viewHolder
     */
    public abstract void onItemClick(RecyclerView.ViewHolder viewHolder);

    /**
     * on Long Click
     * @param viewHolder
     */
    public abstract void onLongClick(RecyclerView.ViewHolder viewHolder);

}
