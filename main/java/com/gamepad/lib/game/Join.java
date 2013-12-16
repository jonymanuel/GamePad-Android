package com.gamepad.lib.game;

import java.util.ArrayList;

/**
 * Created by Fabian on 16.12.13.
 */
public class Join
{
    private ArrayList<Lobby> lobbies;

    public Join()
    {
        lobbies = new ArrayList<Lobby>();
    }

    public ArrayList<Lobby> getLobbies() {
        return lobbies;
    }
}
