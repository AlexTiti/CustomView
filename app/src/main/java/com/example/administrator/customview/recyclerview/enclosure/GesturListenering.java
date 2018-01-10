package com.example.administrator.customview.recyclerview.enclosure;

import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * <pre>
 *
 *   @author   :   Alex
 *   @e_mail   :   18238818283@sina.cn
 *   @time     :   2018/01/09
 *   @desc     :
 *   @version  :   V 1.0.9
 */

public class GesturListenering extends GestureDetector.SimpleOnGestureListener {

    private RecyclerView recyclerView;
    private RecycleViewClickListenering clickListenering;

    public GesturListenering(RecyclerView recyclerView, RecycleViewClickListenering clickListenering) {
        this.recyclerView = recyclerView;
        this.clickListenering = clickListenering;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        View  view = recyclerView.findChildViewUnder(e.getX(),e.getY());
        if (view != null){
            RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
            clickListenering.onItemClick(viewHolder);
        }
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        super.onLongPress(e);
        View  view = recyclerView.findChildViewUnder(e.getX(),e.getY());
        if (view != null){
            RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
            clickListenering.onLongClick(viewHolder);
        }
    }
}
