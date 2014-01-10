package com.gamepad.lib.game;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Fabian on 16.12.13 12:56 in project ${PROJECT_NAME}.
 */
public class Lobby
{
    private ArrayList<LobbyPlayer> players;
    private String name;
    private InetAddress hostIp;
    private int hostPort;
    private String gameName;
    private int maxPlayers;

    public Lobby()
    {
        players = new ArrayList<LobbyPlayer>();
    }

    public int getMaxPlayers()
    {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
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

    public static LobbyPlayer[] getPlayersFromString(String input)
    {
        ArrayList<LobbyPlayer> result = new ArrayList<LobbyPlayer>();
        String[] playerNames = input.split(":");
        for(String playerName : playerNames)
        {
            LobbyPlayer player = new LobbyPlayer();
            player.setName(playerName);
            result.add(player);
        }
        return (LobbyPlayer[])result.toArray();
    }

    public void setHostIp(String inetAddress)
    {
        InetAddress addr = null;
        try
        {
            addr = InetAddress.getByName(inetAddress);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        if(addr != null)
        {
            this.hostIp = addr;
        }
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

    public void addPlayerRange(LobbyPlayer[] players)
    {
        for(LobbyPlayer player : players)
        {
            addPlayer(player);
        }
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
        for (LobbyPlayer cur : players) {
            if (cur.getName().equals(name)) {
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
