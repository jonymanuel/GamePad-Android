package com.gamepad.lib.game;

import android.util.Log;

import com.gamepad.lib.GPC;
import com.gamepad.lib.cmd.ICommand;
import com.gamepad.lib.cmd.commands.JoinCommand;
import com.gamepad.lib.cmd.commands.PingCommand;
import com.gamepad.lib.net.Packet;
import com.gamepad.lib.net.PacketEvent;

import org.json.JSONObject;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by Fabian on 16.12.13.
 */
public class Host implements PacketEvent, Mode
{
    private Lobby lobby;
    private Vector _listeners;

    public Host()
    {
        GPC.getNetwork().addPacketEventListener(this);
    }

    public Lobby getLobby()
    {
        return lobby;
    }

    public void createLobby(String name)
    {
        lobby = new Lobby();
        lobby.setMaxPlayers(5);
        LobbyPlayer host = new LobbyPlayer();
        host.setName("Host");
        lobby.addPlayer(host);
        lobby.setName(name);
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

    public void destroyLobby()
    {
        lobby = null;
    }

    private void registerCommands()
    {
        GPC.getCmdParser().clearCommands();
        GPC.getCmdParser().RegisterCommand(new PingCommand());
        GPC.getCmdParser().RegisterCommand(new JoinCommand());
    }

    public void joinPlayer(LobbyPlayer player)
    {
        getLobby().addPlayer(player);
        fireLobbyUpdateEvent();
    }

    @Override
    public void newPacket(Packet p) {
        if(lobby == null)
        {
            return;
        }
        try
        {
            JSONObject obj = GPC.getCmdParser().parseCommand(p.getMessage());
            obj.put("from", p.getFrom().toString().replace("/", ""));
            obj.put("lobbyname", lobby.getName());
            ICommand cmd = GPC.getCmdParser().findCommandByCommandString(obj.getString("cmd"));
            cmd.runCommand(obj);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void initMode() {
        registerCommands();
    }

    @Override
    public void clearMode() {
        Log.d("Join", "Clearing host mode");
        GPC.getCmdParser().clearCommands();
        destroyLobby();
        Log.d("Join", "Cleared host mode");
    }
}
