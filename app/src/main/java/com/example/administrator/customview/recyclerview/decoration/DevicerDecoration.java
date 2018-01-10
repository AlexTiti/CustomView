package com.example.administrator.customview.recyclerview.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.customview.R;

/**
 * <pre>
 *
 *   @author   :   Alex
 *   @e_mail   :   18238818283@sina.cn
 *   @time     :   2018/01/08
 *   @desc     :
 *   @version  :   V 1.0.9
 */

public class DevicerDecoration extends RecyclerView.ItemDecoration {

    private Paint mDevicerPaint;
    //下划线宽度
    private int width;

    /**
     * 初始化数据
     * @param context
     * @param width
     * @param color
     */
    public DevicerDecoration(Context context,int width ,int color) {
        this.width = width;
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(width);
        paint.setColor(context.getResources().getColor(color));
        mDevicerPaint = paint;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = width;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        //列表数量
        int count = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        for (int i=0;i<count;i++){
            View view = parent.getChildAt(i);
            int top = view.getBottom();
            int bottom = top + width;
            c.drawRect(left,top,right,bottom,mDevicerPaint);
        }


    }
}
