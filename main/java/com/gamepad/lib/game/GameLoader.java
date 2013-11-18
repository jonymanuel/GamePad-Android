package com.gamepad.lib.game;

import android.os.Environment;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URLClassLoader;

public class GameLoader {

    public void loadGame(String gameName)
    {
        File file = new File(Environment.getExternalStorageDirectory() + "/GamePad/" + gameName);
        URLClassLoader child = new URLClassLoader(file.toURL(), this.getClass().getClassLoader());


        /*Class classToLoad = Class.forName("Game", true, child);
        Method method = classToLoad.getDeclaredMethod ("myMethod");
        Object instance = classToLoad.newInstance ();
        Object result = method.invoke (instance);*/
    }
}
