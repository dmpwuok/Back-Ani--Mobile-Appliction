package com.prabathweerapana.back_ani;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import java.util.ArrayList;
import java.util.List;


public class RotaryDialerView extends View {

    public interface DialListener {
        void onDial(int number);
    }

    private float rotorAngle;
    private Drawable rotorDrawable;
    private int r1 = 50;
    private int r2 = 160;
    private double lastFi;

    private List<DialListener> dialListeners = new ArrayList<>();

    public RotaryDialerView(Context ctx) {
        super(ctx, null);
        rotorDrawable = ctx.getResources().getDrawable(R.drawable.dialer);

    }

    public RotaryDialerView(Context ctx, AttributeSet attrs) {
        super(ctx, attrs, 0);
        rotorDrawable = ctx.getResources().getDrawable(R.drawable.dialer);

    }

    public RotaryDialerView(Context ctx, AttributeSet attrs, int defStyle) {
        super(ctx, attrs, defStyle);
        rotorDrawable = ctx.getResources().getDrawable(R.drawable.dialer);
    }

    public void addDialListener(DialListener d) {
        dialListeners.add(d);
    }

    public void removeDialListener(DialListener d) {
        dialListeners.remove(d);
    }

    private void fireDialListenerEvent(int number) {
        for (DialListener l : dialListeners) {
            l.onDial(number);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getRight() - getLeft();
        int height = getBottom() - getTop();

        int x = width / 2;
        int y = height / 2;

        canvas.save();
        rotorDrawable.setBounds(0, 0, rotorDrawable.getIntrinsicWidth(), rotorDrawable.getIntrinsicHeight());

        if (rotorAngle != 0) {
            canvas.rotate(rotorAngle, x, y);
        }
        rotorDrawable.draw(canvas);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        try {
            float x0 = getWidth()/2;
            float y0 = getHeight()/2;


        float x1 = event.getX();
        float y1 = event.getY();
        float x = x0 - x1;
        float y = y0 - y1;
        double r = Math.sqrt(x * x + y * y);
        double sinfi = y / r;
        double fi = Math.toDegrees(Math.asin(sinfi));


        if (x1 > x0 && y0 > y1) {
            fi = 180 - fi;
        } else if (x1 > x0 && y1 > y0) {
            fi = 180 - fi;
        } else if (x0 > x1 && y1 > y0) {
            fi += 360;
        }


        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (r > r1 && r < r2) {
                    rotorAngle += Math.abs(fi - lastFi) + 0.25f;
                    rotorAngle %= 360;
                    lastFi = fi;
                    invalidate();
                    return true;
                }

            case MotionEvent.ACTION_POINTER_DOWN:
                rotorAngle = 0;
                lastFi = fi;
                return true;

            case MotionEvent.ACTION_UP:
                final float angle = rotorAngle % 360;
                int number = Math.round(angle - 20) / 30;

                if (number > 0) {
                    if (number == 10) {
                        number = 0;
                    }
                    fireDialListenerEvent(number);
                }
                rotorAngle = 0;
                post(new Runnable() {
                    @Override
                    public void run() {
                        float fromDegrees = angle;
                        Animation anim = new RotateAnimation(fromDegrees, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        anim.setInterpolator(getContext(), android.R.anim.decelerate_interpolator);
                        anim.setDuration((long) (angle * 5L));
                        startAnimation(anim);

                    }
                });
                return true;
            default:
                break;
        }

        }catch (NullPointerException ignored){}
        return super.onTouchEvent(event);
    }
}

