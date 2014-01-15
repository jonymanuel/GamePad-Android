package com.gamepad.lib.helpers;

import java.util.Random;

/**
 * Created by Fabian on 15.01.14 14:51 in project GamePad.
 */
public class BetterRandom {
    static Random rnd;

    public static int nextInt(int n)
    {
        if(rnd == null)
        {
            rnd = new Random();
        }
        return rnd.nextInt(n);
    }
}
