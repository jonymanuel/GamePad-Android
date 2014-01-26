package com.gamepad.lib.game;

import android.os.AsyncTask;
import android.util.Log;

import com.gamepad.lib.GPC;
import com.gamepad.lib.cmd.ICommand;
import com.gamepad.lib.cmd.commands.GameStartedCommand;
import com.gamepad.lib.cmd.commands.JoinAcceptedCommand;
import com.gamepad.lib.cmd.commands.PongCommand;
import com.gamepad.lib.helpers.BetterRandom;
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
public class Join implements PacketEvent, Mode {
    private ArrayList<Lobby> lobbies;
    private Lobby curLobby;
    private Boolean stopLobbySearcher;
    private Vector _listeners;
    private String localPlayerName;
    private Vector _lobbyJoinedListeners;
    private Vector _gameStartedListeners;


    public Join() {
        lobbies = new ArrayList<Lobby>();
        stopLobbySearcher = false;
        GPC.getNetwork().addPacketEventListener(this);
        localPlayerName = String.valueOf(BetterRandom.nextInt(500000));
    }

    private void registerCommands() {
        GPC.getCmdParser().clearCommands();
        GPC.getCmdParser().RegisterCommand(new PongCommand());
        GPC.getCmdParser().RegisterCommand(new JoinAcceptedCommand());
        GPC.getCmdParser().RegisterCommand(new GameStartedCommand());
    }

    public void setLocalPlayerName(String name) {
        localPlayerName = name;
    }

    public String getLocalPlayerName() {
        return localPlayerName;
    }

    public void requestJoin(Lobby lobby) {
        JSONObject res = new JSONObject();
        try {
            res.put("cmd", "joinlobby");
            res.put("playername", localPlayerName);
        } catch (Exception ex) {
        }

        Packet packet = new Packet(res.toString());
        packet.setDestination(lobby.getHostIp());
        GPC.getNetwork().sendPacket(packet);
    }

    public boolean isInLobby() {
        return curLobby != null;
    }

    public void fireGameStartedEvent() {
        if(_gameStartedListeners != null && !_gameStartedListeners.isEmpty())
        {
            Enumeration e = _gameStartedListeners.elements();
            while(e.hasMoreElements())
            {
                GameStartedEvent started = (GameStartedEvent) e.nextElement();
                started.gameStarted();
            }
        }
    }

    public void addGameStartedEventListener(GameStartedEvent event)
    {
        if(_gameStartedListeners == null)
        {
            _gameStartedListeners = new Vector();
        }
        _gameStartedListeners.addElement(event);
    }

    public void removeGameStartedEventListener(GameStartedEvent event){
        if(_gameStartedListeners == null)
        {
            _gameStartedListeners = new Vector();
        }
        _gameStartedListeners.addElement(event);
    }

    private void fireLobbyJoinedEvent() {
        if (_lobbyJoinedListeners != null && !_lobbyJoinedListeners.isEmpty()) {
            Enumeration e = _lobbyJoinedListeners.elements();
            while (e.hasMoreElements()) {
                LobbyJoinedEvent joined = (LobbyJoinedEvent) e.nextElement();
                joined.lobbyJoined();
            }
        }
    }

    public void removeLobbyJoinedEventListener(LobbyJoinedEvent event) {
        if (_lobbyJoinedListeners == null) {
            _lobbyJoinedListeners = new Vector();
        }
        _lobbyJoinedListeners.removeElement(event);
    }

    public void addLobbyJoinedEventListener(LobbyJoinedEvent event) {
        if (_lobbyJoinedListeners == null) {
            _lobbyJoinedListeners = new Vector();
        }
        _lobbyJoinedListeners.addElement(event);
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

    public void removeLobbyUpdateEventListener(LobbyUpdateEvent listener) {
        if (_listeners == null) {
            _listeners = new Vector();
        }
        _listeners.removeElement(listener);
    }

    public ArrayList<Lobby> getLobbies() {
        return lobbies;
    }

    public void updateLobby(Lobby lobby) {
        Lobby lob = getLobbyByName(lobby.getName());
        synchronized (lob.getPlayers()) {
            lob.getPlayers().clear();

            for (LobbyPlayer player : lobby.getPlayers()) {
                lob.addPlayer(player);
            }
        }
        lob.setGameName(lobby.getGameName());
        lob.setHostIp(lobby.getHostIp());
        lob.setHostPort(lobby.getHostPort());
    }

    public void addLobby(Lobby lobby) {
        if (!lobbyExists(lobby)) {
            lobbies.add(lobby);
            Log.d("Join", "Added new lobby: " + lobby.getName() + " with " + lobby.getPlayers().size() + " players");
            fireLobbyUpdateEvent();
        } else {
            updateLobby(lobby);
            Log.d("Join", "Updated lobby: " + lobby.getName() + " with " + lobby.getPlayers().size() + " players");
            fireLobbyUpdateEvent();
        }
    }

    public Lobby getLobbyByName(String name) {
        for (Lobby lob : lobbies) {
            if (lob.getName().equals(name)) {
                return lob;
            }
        }
        return null;
    }

    public void leaveCurrentLobby() {
        if (curLobby == null) {
            return;
        }

        JSONObject res = new JSONObject();
        try {
            res.put("cmd", "leaveLobby");
            res.put("playerName", localPlayerName);
        } catch (Exception ex) {
        }
        Packet packet = new Packet(res.toString());
        packet.setDestination(curLobby.getHostIp());
        GPC.getNetwork().sendPacket(packet);
        curLobby = null;
    }

    public Lobby getCurrentLobby() {
        return curLobby;
    }

    public void setCurrentLobby(Lobby lob) {
        this.curLobby = lob;
        fireLobbyUpdateEvent();
        fireLobbyJoinedEvent();
    }

    public Boolean lobbyExists(Lobby lobby) {
        if (lobby == null) {
            return false;
        }
        Log.d("Join", "Checking if lobby '" + lobby.getName() + "' exists");
        Iterator<Lobby> pIt = lobbies.iterator();
        while (pIt.hasNext()) {
            Lobby cur = pIt.next();
            if (cur.getID() == lobby.getID()) {
                Log.d("Join", "The lobby '" + lobby.getName() + "' exists");
                return true;
            }
        }
        Log.d("Join", "The lobby '" + lobby.getName() + "' doesn't exist");
        return false;
    }

    public void stopLobbySearcher() {
        Log.d("Join", "Stopping LobbySearcher");
        stopLobbySearcher = true;
    }

    private void deleteOfflineLobbies() {
        for (Lobby lobby : lobbies) {
            if (lobby.isOffline()) {
                if (lobby.getID() == curLobby.getID()) {
                    leaveCurrentLobby();
                }
                Log.d("Join", "Lobby " + lobby.getName() + " is offline. Deleting...");
                lobbies.remove(lobby);
                fireLobbyUpdateEvent();
            }
        }
    }

    public void startLobbyFinder() {
        Log.d("Join", "Starting LobbySearcher");
        stopLobbySearcher = false;
        AsyncTask aTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                while (!stopLobbySearcher) {
                    try {
                        searchForHosts();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    try {
                        deleteOfflineLobbies();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    try {
                        Thread.sleep(500);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
                Log.d("Join", "LobbySearcher stopped");
                stopLobbySearcher = false;
                return null;
            }
        };
        aTask.execute();
    }


    /* Used if you want to clear this mode and choose a new one (pseudo dispose) */
    @Override
    public void clearMode() {
        Log.d("Join", "Clearing join mode");
        this.stopLobbySearcher();
        GPC.getCmdParser().clearCommands();
        this.lobbies.clear();
        if (_listeners != null) {
            this._listeners.clear();
        }
        GPC.getNetwork().removePacketEventListener(this);
        Log.d("Join", "Cleared join mode");
    }

    /* Initializes this mode to work properly */
    @Override
    public void initMode() {
        Log.d("Join", "Initializing join mode");
        this.registerCommands();
        this.startLobbyFinder();
        GPC.getNetwork().addPacketEventListener(this);
        Log.d("Join", "Join mode initialized");
    }

    /* Searches for available hosts in the current network */
    private void searchForHosts() {
        GPC.getNetwork().sendPingBroadcast();
    }

    @Override
    public void newPacket(Packet p) {
        try {
            JSONObject obj = GPC.getCmdParser().parseCommand(p.getMessage());
            obj.put("from", p.getFrom().toString().replace("/", ""));
            String cmdString = obj.getString("cmd");
            ICommand cmd = GPC.getCmdParser().findCommandByCommandString(cmdString);
            if (cmd == null) {
                Log.d("Join", "Received unknown packet: " + cmdString);
                return;
            }
            cmd.runCommand(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
