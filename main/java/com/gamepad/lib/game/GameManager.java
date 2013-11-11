package com.gamepad.lib.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Author: root
 * Date: 14.10.13.
 */
public class GameManager extends View
{
    private Game currentGame; //peter

    public GameManager(Context context)
    {
        super(context);
    }

    public GameManager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public GameManager(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    public Game getCurrentGame()
    {
        return currentGame;
    }

    public void setCurrentGame(Game g)
    {
        if(currentGame != null)
        {
            currentGame.unload();
        }
        currentGame = g;
        currentGame.initialize();
    }
    private float x,y;
    private int r,g,b;
    Random rnd = new Random();

    @Override
    public boolean onTouchEvent(MotionEvent me)
    {
        x = me.getX();
        y = me.getY();

        this.invalidate();
        return true;
    }

    @Override
    public synchronized void onDraw(Canvas c)
    {
        if(currentGame != null)
        {
            currentGame.draw(c);
        }
        else
        {
            Paint p = new Paint();
            p.setARGB(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
            p.setAlpha(255);
            p.setStrokeWidth(50);
            c.drawLine(10, 10, x, y, p);
        }

    }
}
