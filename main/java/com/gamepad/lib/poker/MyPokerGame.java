package com.gamepad.lib.poker;

/**
 * Created by Fabian on 26.01.14.
 */
public class MyPokerGame {
    static PokerClientActivity client;

    public static PokerClientActivity getPokerClientActivity()
    {
        return client;
    }

    public static void setPokerClientActivity(PokerClientActivity act)
    {
        client = act;
    }
}
