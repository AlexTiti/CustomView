package com.example.administrator.customview.pull_large;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ScrollView;

/**
 * <pre>
 *
 *   @author   :   Alex
 *   @e_mail   :   18238818283@sina.cn
 *   @time     :   2018/01/09
 *   @desc     :
 *   @version  :   V 1.0.9
 */

public class PullToLargeScroll extends ScrollView {

    private View zoomView;

    private int viewPreWidth, viewPreHeight;
    private int maxViewWidthRate = 3;
    private float speed = 0.5f;

    private float preMoveY;
    private boolean isMove = false;

    private float y = 0f;

    public PullToLargeScroll(Context context) {
        super(context);
    }

    public PullToLargeScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToLargeScroll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PullToLargeScroll(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setOverScrollMode(OVER_SCROLL_NEVER);
        if (getChildAt(0) != null && getChildAt(0) instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) getChildAt(0);
            if (viewGroup.getChildCount() > 0) {
                zoomView = viewGroup.getChildAt(0);

            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {


        if (zoomView == null) {
            return super.onTouchEvent(ev);
        }

        switch (ev.getAction()) {

            case MotionEvent.ACTION_MOVE:

                if (!isMove) {
                    if (getScrollY() == 0) {
                        y = ev.getY();
                    } else {
                        break;
                    }
                }
                float dis = ev.getY() - y;
                isMove = true;
                setZoomView(dis);
                break;

                case MotionEvent.ACTION_UP:
                    replay();
                    isMove = false;
                    break;
        }
        return super.onTouchEvent(ev);
    }

    private void setZoomView(float y) {
        if (y <0){
            return;
        }

        float rate = (viewPreWidth + y * speed )/ viewPreWidth;
        Log.e("======",y+"==="+viewPreWidth);
        if (rate > maxViewWidthRate) {
            return;
        }
        ViewGroup.LayoutParams params = zoomView.getLayoutParams();
        params.width = (int) (viewPreWidth + y * speed);
        params.height = (int) (rate * viewPreHeight);
        Log.e("======",params.width+"==="+params.height);
      ((MarginLayoutParams)   params).setMargins( -(params.width - viewPreWidth)/2,0,-(params.width - viewPreWidth)/2,0);
       zoomView.setLayoutParams(params);
    }

    private void replay(){
        final float distance = zoomView.getMeasuredWidth() - viewPreWidth;
        // 设置动画
        ValueAnimator anim = ObjectAnimator.ofFloat(distance, 0.0F).setDuration((long) (distance * 0.6));
        anim.setInterpolator(new DecelerateInterpolator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setZoomView((Float) animation.getAnimatedValue());
            }
        });
        anim.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (viewPreHeight == 0 && viewPreWidth ==0){
            viewPreHeight = zoomView.getMeasuredHeight();
            viewPreWidth = zoomView.getMeasuredWidth();
        }
    }
}
