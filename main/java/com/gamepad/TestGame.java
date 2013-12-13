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
<<<<<<< HEAD
    public void unload() {

=======
    public void dispose() {
        testScreen.dispose();
>>>>>>> e74965abb3789fb379701562f5a46291d47e064b
    }
}
