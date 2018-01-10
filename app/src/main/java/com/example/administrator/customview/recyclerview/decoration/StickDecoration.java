package com.example.administrator.customview.recyclerview.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.administrator.customview.R;

import java.util.List;

/**
 * <pre>
 *
 *   @author   :   Alex
 *   @e_mail   :   18238818283@sina.cn
 *   @time     :   2018/01/08
 *   @desc     :
 *   @version  :   V 1.0.9
 */

public class StickDecoration extends RecyclerView.ItemDecoration {

    private Paint mBackPaint;
    private Paint mTextpaint;
    private GroupSelect mGroupSelect;

    private Context mContext;
    private int width = 80;

    public StickDecoration(GroupSelect mGroupSelect, Context mContext) {
        this.mGroupSelect = mGroupSelect;
        this.mContext = mContext;
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setDither(true);
        paint.setColor(mContext.getResources().getColor(R.color.devider));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        mBackPaint = paint;

        Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setDither(true);
        paint1.setStyle(Paint.Style.FILL_AND_STROKE);
        paint1.setStrokeWidth(2);
        paint1.setTextSize(40);
        paint1.setColor(Color.WHITE);
        mTextpaint = paint1;

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (position == 0 || mGroupSelect.isNextGroup(position)) {
            outRect.top = width;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int count = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < count; i++) {
            View view = parent.getChildAt(i);
            int pos = parent.getChildAdapterPosition(view);
            if (pos == 0 || mGroupSelect.isNextGroup(pos)) {
                String text = mGroupSelect.getGroupTitle(pos);
                int bottom = view.getTop();
                int top = bottom - width;
                c.drawRect(left, top, right, bottom, mBackPaint);
                Rect rect = new Rect();
                mTextpaint.getTextBounds(text, 0, text.length(), rect);
                c.drawText(text, left + 20, bottom - width / 2 + rect.height() / 2, mTextpaint);
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int itemCount = state.getItemCount();
        int count = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();


        String preGroupId = "";
        String groupId = "-1";
        for (int i = 0; i < count; i++) {
            View view = parent.getChildAt(i);
            int pos = parent.getChildAdapterPosition(view);

            preGroupId = groupId;
            groupId = mGroupSelect.getGroupTitle(pos);

            if (groupId.equals(preGroupId)){
                Log.e("===",groupId+"=="+preGroupId);
                continue;
            }

           String text = mGroupSelect.getGroupTitle(pos);
            Log.e("text===text","================="+text);
            int bottom = view.getTop();
           int top  = Math.max(bottom,width);

            if (pos +1 < itemCount) {

                if (mGroupSelect.isLastGroup(pos) && view.getBottom() < top) {
                    top = view.getBottom();
                }
            }
            c.drawRect(left, top - width, right, top, mBackPaint);
            Rect rect = new Rect();
                mTextpaint.getTextBounds(text, 0, text.length(), rect);
                c.drawText(text, left + 20, top - width / 2 + rect.height() / 2, mTextpaint);

        }

    }

  public   interface GroupSelect {
        boolean isNextGroup(int position);

        boolean isLastGroup(int position);

        String getGroupTitle(int position);
    }
}
