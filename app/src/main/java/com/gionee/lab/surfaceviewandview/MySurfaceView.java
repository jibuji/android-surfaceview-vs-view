package com.gionee.lab.surfaceviewandview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by jiengfei on 15-5-12.
 */
public class MySurfaceView extends SurfaceView {

    private SurfaceHolder mHolder;
    private MyThread mThread;
    private Drawer mDrawer = null;

    public MySurfaceView(Context context) {
        super(context);
        init(context);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        mHolder = this.getHolder();
        mHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Log.d("T", "surfaceCreated ");
                mThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Log.d("T", "surfaceChanged ");
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                mThread.finish();
            }
        });
        Drawable dr = context.getResources().getDrawable(R.drawable.avatar, null);

        mThread = new MyThread(mHolder, dr, this);
    }

    static class MyThread extends Thread {
        private final View mView;
        private Drawer mDrawer;
        private SurfaceHolder holder;
        private boolean mIsRun;

        public MyThread(SurfaceHolder holder, Drawable drawable, View view) {
            this.holder = holder;
            mDrawer = new Drawer(drawable);
            mView = view;
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            drawable.setBounds(0, 0, width, height);
        }

        @Override
        public synchronized void start() {
            Log.d("T", "Thread starting");
            mIsRun = true;
            super.start();
        }

        @Override
        public void run() {
            while (mIsRun) {

                Canvas canvas = null;
                try {
                    canvas = holder.lockCanvas();
                    long start = System.currentTimeMillis();
                    mDrawer.draw(canvas, mView.getWidth());
                    long end = System.currentTimeMillis();
                    Log.d("T", "surface used:" + (end - start));
                } finally {
                    if (canvas != null) {
                        holder.unlockCanvasAndPost(canvas);
                    }
                }

            }
        }

        public void finish() {
            mIsRun = false;
        }
    }
}
