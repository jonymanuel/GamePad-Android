package com.gamepad;

import com.badlogic.gdx.Game;

/**
 * Created by Fabian on 14.11.13.
 */
public class TestGame extends Game
{
    TestScreen testScreen;

    @Override
    public void create() {
        testScreen = new TestScreen();
        setScreen(testScreen);
    }

    @Override
    public void dispose() {
        testScreen.dispose();
    }
}
