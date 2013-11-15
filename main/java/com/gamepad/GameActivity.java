package com.gamepad;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gamepad.lib.GPC;
import com.gamepad.lib.andengine.entity.scene.Scene;
import com.gamepad.lib.andengine.entity.scene.background.Background;
import com.gamepad.lib.andengine.ui.activity.SimpleBaseGameActivity;
import com.gamepad.lib.game.Game;
import com.gamepad.lib.game.GameManager;

/**
 * Author: root
 * Date: 14.10.13.
 */
public class GameActivity extends SimpleBaseGameActivity
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


    @Override
    protected void onCreateResources() {

    }

    @Override
    protected Scene onCreateScene() {
        Scene scene = new Scene();
        scene.setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
        return scene;
    }
}
