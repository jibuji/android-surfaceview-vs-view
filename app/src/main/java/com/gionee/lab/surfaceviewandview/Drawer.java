package com.gionee.lab.surfaceviewandview;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * Created by jiengfei on 15-5-12.
 */
public class Drawer {
    Drawable mDrawable;
    int mTx1 = 0;
    int mTx2 = 0;
    public Drawer(Drawable dr) {
        mDrawable = dr;
        int width = mDrawable.getIntrinsicWidth();
        int height = mDrawable.getIntrinsicHeight();
        mDrawable.setBounds(0, 0, width, height);
        mTx2 = width;
    }

    public void draw(Canvas canvas, int rollWidth) {
        int height = mDrawable.getIntrinsicHeight();
        canvas.translate(mTx1, 0);
        mDrawable.draw(canvas);
        canvas.translate(0, height);
        mDrawable.draw(canvas);
        canvas.translate(0, height);
        mDrawable.draw(canvas);
        canvas.translate(mTx2 - mTx1, -height * 2);//translate up back
        mDrawable.draw(canvas);
        canvas.translate(0, height);
        mDrawable.draw(canvas);
        canvas.translate(0, height);
        mDrawable.draw(canvas);
        canvas.translate(-mTx2, -height * 2);//translate back to origin
        --mTx1;
        --mTx2;
        if (mTx1 <=0 && mTx2 <= 0) {
            if (mTx1 < mTx2) {
                mTx1 = rollWidth;
            } else {
                mTx2 = rollWidth;
            }
        }
    }
}
