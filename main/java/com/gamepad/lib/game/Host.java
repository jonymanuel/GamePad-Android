package com.gamepad.lib.game;

import android.os.AsyncTask;
import android.util.Log;

import com.gamepad.lib.GPC;
import com.gamepad.lib.cmd.ICommand;
import com.gamepad.lib.cmd.commands.JoinCommand;
import com.gamepad.lib.cmd.commands.LeaveLobbyCommand;
import com.gamepad.lib.cmd.commands.PingCommand;
import com.gamepad.lib.net.Packet;
import com.gamepad.lib.net.PacketEvent;

import org.json.JSONObject;

import java.net.InetAddress;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Fabian on 16.12.13.
 */
public class Host implements PacketEvent, Mode {
    private Lobby lobby;
    private Vector _listeners;
    private boolean lobbyDestroyed;

    public Host() {
        GPC.getNetwork().addPacketEventListener(this);
        lobbyDestroyed = false;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public void createLobby(String name) {
        lobbyDestroyed = false;
        lobby = new Lobby();
        lobby.setMaxPlayers(5);
        LobbyPlayer host = new LobbyPlayer();
        host.setName(GPC.getJoin().getLocalPlayerName());
        lobby.addPlayer(host);
        lobby.setName(name);
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                while (!lobbyDestroyed) {
                    try {
                        cleanOfflinePlayers();
                    } catch (Exception ex) {
                    }
                }
                return null;
            }
        }.execute();
    }

    private void cleanOfflinePlayers() {
        Iterator<LobbyPlayer> it = lobby.getPlayers().iterator();
        boolean removed = false;
        while (it.hasNext()) {
            LobbyPlayer lp = it.next();
            if (lp.isOffline() && !lp.getName().equals(GPC.getJoin().getLocalPlayerName())) {
                it.remove();
                removed = true;
            }
        }
        if (removed) {
            fireLobbyUpdateEvent();
        }
    }

    private void fireLobbyUpdateEvent() {
        if (_listeners != null && !_listeners.isEmpty()) {
            Enumeration e = _listeners.elements();
            while (e.hasMoreElements()) {
                LobbyUpdateEvent lue = (LobbyUpdateEvent) e.nextElement();
                lue.onLobbyUpdate();
            }
        }
    }

    public void addLobbyUpdateEventListener(LobbyUpdateEvent listener) {
        if (_listeners == null) {
            _listeners = new Vector();
        }
        _listeners.addElement(listener);
    }

    public void startGame() {
        synchronized (lobby.getPlayers()) {

            for (LobbyPlayer player : lobby.getPlayers()) {
                if (player.getName().equals(GPC.getJoin().getLocalPlayerName())) {
                    continue;
                }
                sendGameStarted(player.getIp());
            }
        }
    }

    private void sendGameStarted(InetAddress addr) {
        JSONObject res = new JSONObject();
        try {
            res.put("cmd", "gameStarted");
        } catch (Exception ex) {
        }

        Packet packet = new Packet(res.toString());
        packet.setDestination(addr);
        GPC.getNetwork().sendPacket(packet);
    }

    public void removeLobbyUpdateEventListener(LobbyUpdateEvent listener) {
        if (_listeners == null) {
            _listeners = new Vector();
        }
        _listeners.removeElement(listener);
    }

    public void destroyLobby() {
        lobbyDestroyed = true;
        lobby = null;
    }

    private void registerCommands() {
        GPC.getCmdParser().clearCommands();
        GPC.getCmdParser().RegisterCommand(new PingCommand());
        GPC.getCmdParser().RegisterCommand(new JoinCommand());
        GPC.getCmdParser().RegisterCommand(new LeaveLobbyCommand());
    }

    public void joinPlayer(LobbyPlayer player) {
        getLobby().addPlayer(player);
        fireLobbyUpdateEvent();
    }

    @Override
    public void newPacket(Packet p) {
        if (lobby == null) {
            return;
        }
        try {
            JSONObject obj = GPC.getCmdParser().parseCommand(p.getMessage());
            obj.put("from", p.getFrom().toString().replace("/", ""));
            obj.put("lobbyname", lobby.getName());
            ICommand cmd = GPC.getCmdParser().findCommandByCommandString(obj.getString("cmd"));
            cmd.runCommand(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initMode() {
        registerCommands();
    }

    public void playerLeft(String playerName) {
        Iterator it = lobby.getPlayers().iterator();
        while (it.hasNext()) {
            LobbyPlayer player = (LobbyPlayer) it.next();
            if (player.getName().equals(playerName)) {
                it.remove();
                break;

            }
        }
        fireLobbyUpdateEvent();
    }

    public void sendPacketToPlayer(LobbyPlayer player, JSONObject obj)
    {
        Packet packet = new Packet(obj.toString());
        packet.setDestination(player.getIp());
        GPC.getNetwork().sendPacket(packet);
    }

    @Override
    public void clearMode() {
        Log.d("Join", "Clearing host mode");
        GPC.getCmdParser().clearCommands();
        destroyLobby();
        Log.d("Join", "Cleared host mode");
    }
}
