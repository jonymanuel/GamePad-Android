package com.gamepad.lib.game;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Fabian on 16.12.13.
 */
public class Lobby
{
    ArrayList<LobbyPlayer> players;

    public Lobby()
    {
        players = new ArrayList<LobbyPlayer>();
    }

    public ArrayList<LobbyPlayer> getPlayers()
    {
        return players;
    }

    public Boolean addPlayer(LobbyPlayer player)
    {
        if(!playerExistsByName(player.getName()))
        {
            players.add(player);
            return true;
        }
        return false;
    }

    public Boolean playerExistsByName(String name)
    {
        Iterator<LobbyPlayer> pIt = players.iterator();
        while(pIt.hasNext())
        {
            LobbyPlayer cur = pIt.next();
            if(cur.getName().equals(name))
            {
                return true;
            }
        }
        return false;
    }

    public void deletePlayerByName(String name)
    {
        Iterator<LobbyPlayer> pIt = players.iterator();
        while(pIt.hasNext())
        {
            LobbyPlayer cur = pIt.next();
            if(cur.getName().equals(name))
            {
                pIt.remove();
            }
        }
    }
}
