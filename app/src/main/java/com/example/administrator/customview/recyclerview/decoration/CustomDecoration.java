package com.example.administrator.customview.recyclerview.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * <pre>
 *
 *   @author   :   Alex
 *   @e_mail   :   18238818283@sina.cn
 *   @time     :   2018/01/08
 *   @desc     :
 *   @version  :   V 1.0.9
 */

public class CustomDecoration extends RecyclerView.ItemDecoration {
    private int padding ;

    public CustomDecoration(int padding) {
        this.padding = padding;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = padding;
        outRect.right = padding;
        outRect.top = padding;
        outRect.bottom = padding;
    }
}
