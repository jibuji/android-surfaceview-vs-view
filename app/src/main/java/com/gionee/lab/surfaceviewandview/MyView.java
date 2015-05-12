package com.gionee.lab.surfaceviewandview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by jiengfei on 15-5-12.
 */
public class MyView extends View {

    Drawer mDrawer = null;
    public MyView(Context context) {
        super(context);
        init(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        Drawable dr = context.getResources().getDrawable(R.drawable.avatar, null);
        mDrawer = new Drawer(dr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        long start = System.currentTimeMillis();
        mDrawer.draw(canvas, getWidth());
        postInvalidate();
        long end = System.currentTimeMillis();
        Log.d("T", "View onDraw used:" + (end - start));
    }
}
