package com.gamepad;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gamepad.lib.GPC;
import com.gamepad.lib.game.Game;
import com.gamepad.lib.game.GameManager;

/**
 * Author: root
 * Date: 14.10.13.
 */
public class GameActivity extends Activity
{
    static GameManager manager;

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
