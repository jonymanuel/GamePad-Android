package com.gamepad.lib.game;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Fabian on 16.12.13.
 */
public class Lobby
{
    private ArrayList<LobbyPlayer> players;
    private String name;
    private InetAddress hostIp;
    private int hostPort;
    private String gameName;

    public Lobby()
    {
        players = new ArrayList<LobbyPlayer>();
    }

    public String getGameName()
    {
        return gameName;
    }

    public void setGameName(String newGameName)
    {
        this.gameName = newGameName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InetAddress getHostIp() {
        return hostIp;
    }

    public void setHostIp(InetAddress hostIp) {
        this.hostIp = hostIp;
    }

    public int getHostPort() {
        return hostPort;
    }

    public void setHostPort(int hostPort) {
        this.hostPort = hostPort;
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
