package com.gamepad.lib.game;

import android.os.AsyncTask;
import android.util.Log;

import com.gamepad.lib.GPC;
import com.gamepad.lib.cmd.CommandParser;
import com.gamepad.lib.cmd.ICommand;
import com.gamepad.lib.cmd.commands.JoinCommand;
import com.gamepad.lib.cmd.commands.PongCommand;
import com.gamepad.lib.net.Packet;
import com.gamepad.lib.net.PacketEvent;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Fabian on 16.12.13.
 */
public class Join implements PacketEvent
{
    private ArrayList<Lobby> lobbies;
    private Lobby curLobby;
    private Boolean stopLobbySearcher;
    private Vector _listeners;
    private CommandParser cmdParser;


    public Join()
    {
        cmdParser = new CommandParser();
        lobbies = new ArrayList<Lobby>();
        stopLobbySearcher = false;
        GPC.getNetwork().addPacketEventListener(this);
        registerCommands();
    }

    private void registerCommands()
    {
        cmdParser.RegisterCommand(new PongCommand());
        cmdParser.RegisterCommand(new JoinCommand());
    }

    private void fireLobbyUpdateEvent()
    {
        if (_listeners != null && !_listeners.isEmpty())
        {
            Enumeration e = _listeners.elements();
            while (e.hasMoreElements())
            {
                LobbyUpdateEvent lue = (LobbyUpdateEvent) e.nextElement();
                lue.onLobbyUpdate();
            }
        }
    }

    public void addLobbyUpdateEventListener(LobbyUpdateEvent listener)
    {
        if (_listeners == null)
        {
            _listeners = new Vector();
        }
        _listeners.addElement(listener);
    }

    public ArrayList<Lobby> getLobbies() {
        return lobbies;
    }

    public void UpdateLobby(Lobby lobby)
    {
        Lobby lob = getLobbyByName(lobby.getName());
        for(LobbyPlayer player : lobby.getPlayers())
        {
            lob.addPlayer(player);
        }
        lob.setGameName(lobby.getGameName());
        lob.setHostIp(lobby.getHostIp());
        lob.setHostPort(lobby.getHostPort());
    }

    public void addLobby(Lobby lobby)
    {
        if(!lobbyExists(lobby))
        {
            lobbies.add(lobby);
            Log.d("Join", "Added new lobby: " + lobby.getName() + " with " + lobby.getPlayers().size() + " players");
            fireLobbyUpdateEvent();
        }
        else
        {

        }
    }

    public Lobby getLobbyByName(String name)
    {
        for(Lobby lob : lobbies)
        {
            if(lob.getName().equals(name))
            {
                return lob;
            }
        }
        return null;
    }

    public Lobby getCurrentLobby()
    {
        return curLobby;
    }

    public void setCurrentLobby(Lobby lob)
    {
        this.curLobby = lob;
        fireLobbyUpdateEvent();
    }

    public Boolean lobbyExists(Lobby lobby)
    {
        if(lobby == null)
        {
            return false;
        }
        Log.d("Join", "Checking if lobby '" + lobby.getName() + "' exists");
        Iterator<Lobby> pIt = lobbies.iterator();
        while(pIt.hasNext())
        {
            Lobby cur = pIt.next();
            if(cur.getHostIp().equals(lobby.getHostIp()))
            {
                Log.d("Join", "The lobby '" + lobby.getName()  + "' exists");
                return true;
            }
        }
        Log.d("Join", "The lobby '" + lobby.getName()  + "' doesn't exist");
        return false;
    }

    public void StopLobbySearcher()
    {
        Log.d("Join", "Stopping LobbySearcher");
        stopLobbySearcher = true;
    }

    public void StartLobbyFinder()
    {
        Log.d("Join", "Starting LobbySearcher");
        AsyncTask aTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                while(!stopLobbySearcher)
                {
                    SearchForHosts();
                    try { Thread.sleep(500); } catch(Exception ex){ ex.printStackTrace(); }
                }
                Log.d("Join", "LobbySearcher stopped");
                stopLobbySearcher = false;
                return null;
            }
        };
    }

    private void SearchForHosts()
    {
        GPC.getNetwork().sendPingBroadcast();
    }

    @Override
    public void newPacket(Packet p) {
        try
        {
            JSONObject obj = cmdParser.parseCommand(p.getMessage());
            obj.put("from", p.getFrom().toString());
            ICommand cmd = cmdParser.findCommandByCommandString(obj.getString("cmd"));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
