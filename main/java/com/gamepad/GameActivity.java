package com.gamepad;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.ConsoleMessage;

import com.gamepad.lib.game.GameManager;

import java.io.Console;

/**
 * Author: root
 * Date: 14.10.13.
 */
public class GameActivity extends Activity
{
    private static GameManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        manager = (GameManager)findViewById(R.id.game_manager_view);
        manager.setCurrentGame(new TestGame());
    }

    public static GameManager getGameManager()
    {
        return manager;
    }
}
