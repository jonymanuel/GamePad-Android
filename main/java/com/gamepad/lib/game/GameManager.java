package com.gamepad.lib.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Author: root
 * Date: 14.10.13.
 */
public class GameManager extends View
{
    private Game currentGame;
    private ArrayList<Game> games;

    //initializes a new game manager instance of this class
    public GameManager(Context context)
    {
        super(context);
    }

    //initializes a new game manager instance of this class
    public GameManager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    //initializes a new game manager instance of this class
    public GameManager(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    //gets the currently available games in the game manager
    public ArrayList<Game> getGames()
    {
        return games;
    }

    //gets the currently loaded game of the game manager
    public Game getCurrentGame()
    {
        return currentGame;
    }

    //sets the currently loaded game of the game manager
    public void setCurrentGame(Game g)
    {
        if(currentGame != null)
        {
            currentGame.unload();
        }
        currentGame = g;
        currentGame.initialize();
    }

    @Override
    public boolean onTouchEvent(MotionEvent me)
    {
        //what to do with the touch event
        /*
        me.getX();
        me.getY();

        this.invalidate();*/
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
            p.setARGB(0, 0, 0, 0);
            p.setAlpha(255);
            p.setTextSize(30);
            c.drawText("There is no game loaded", 30.0f, 30.0f, p);
        }

    }
}
