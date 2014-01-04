package com.gamepad.lib.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.gamepad.GameActivity;

import java.util.ArrayList;

/**
 * Author: root
 * Date: 14.10.13.
 */
public class GameManager extends View
{
    private Game currentGame;
    private ArrayList<Game> games;
    private AsyncTask gameThread;
    private Boolean stopTheGame;

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
        StartGame();
    }

    private void sleep(int ms)
    {
        try{Thread.sleep(ms);}catch(Exception ex){}
    }

    public void stopGame()
    {
        stopTheGame = true;
    }

    public void StartGame()
    {
        stopTheGame =false;
        gameThread = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                while(!stopTheGame)
                {
                    GameActivity.getGameManager().update();
                    GameActivity.getGameManager().postInvalidate();
                    sleep(1000 / 5);
                }
                return null;
            }
        };
        gameThread.execute();
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

    public synchronized void update()
    {
        currentGame.update();
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
