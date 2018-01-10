package com.example.administrator.customview.touch_pull;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.customview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TouchPullActivity extends AppCompatActivity implements View.OnTouchListener {

    @BindView(R.id.constraintLayout)
    ConstraintLayout mConstraintLayout;
    @BindView(R.id.touchPullView)
    TouchPullView mTouchPullView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private float startheight, moveHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_pull);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       getSupportActionBar().setHomeButtonEnabled(true);
        mConstraintLayout.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startheight = motionEvent.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                moveHeight = motionEvent.getY();
                float dis = moveHeight - startheight;
                if (dis >= 0) {
                    float progress = dis >= TouchPullView.maxPullHeight ? 1 : (dis / TouchPullView.maxPullHeight);
                    mTouchPullView.setProgress(progress);
                }
                break;
            case MotionEvent.ACTION_UP:
                mTouchPullView.autoBack();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.enter_animal,R.anim.exit_animal);
    }
}
