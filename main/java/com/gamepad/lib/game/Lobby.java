package com.gamepad.lib.game;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
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
    private long lastActivity;

    public Lobby()
    {
        players = new ArrayList<LobbyPlayer>();
        signalActivity();
    }

    public int getMaxPlayers()
    {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
        signalActivity();
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
        signalActivity();
        this.name = name;
    }

    public long getLastActivity()
    {
        return lastActivity;
    }

    public String getPlayerString()
    {
        String curPlayers = "";
        for(LobbyPlayer player : players)
        {
            if(!player.getName().equals(players.get(players.size() - 1)))
            {
                curPlayers += player.getName() + ":";
            }
            else
            {
                curPlayers += player.getName();
            }
        }
        return curPlayers;
    }

    public boolean isOffline()
    {
        long current = new Date().getTime();
        long diff = current - lastActivity;
        if(diff >= 10 * 1000)
        {
            return true;
        }
        return false;
    }

    public void signalActivity()
    {
        lastActivity = new Date().getTime();
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
        return result.toArray(new LobbyPlayer[0]);
    }

    public void setHostIp(String inetAddress)
    {
        signalActivity();
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
        signalActivity();
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
        signalActivity();
    }

    public Boolean addPlayer(LobbyPlayer player)
    {
        signalActivity();
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
        signalActivity();
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
