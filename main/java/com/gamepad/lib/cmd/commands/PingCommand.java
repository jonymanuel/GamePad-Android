package com.gamepad.lib.cmd.commands;

import com.gamepad.lib.GPC;
import com.gamepad.lib.cmd.ICommand;
import com.gamepad.lib.game.Lobby;
import com.gamepad.lib.game.LobbyPlayer;
import com.gamepad.lib.net.Packet;

import org.json.JSONObject;

/**
 * Author: root
 * Date: 07.10.13.
 * if the client receives ping, it will return pong
 */
public class PingCommand implements ICommand
{

    @Override
    public String getCommandString()
    {
        return "ping";
    }

    @Override
    public Boolean runCommand(JSONObject input) throws Exception
    {
        String from = input.getString("from");
        String fromPlayerName = input.getString("playerName");
        Lobby lobby = GPC.getHost().getLobby();
        String lobbyName = lobby.getName();
        String maxPlayers = String.valueOf(lobby.getMaxPlayers());
        String curPlayers = lobby.getPlayerString();
        String gameName = lobby.getGameName();

        for(LobbyPlayer lp : lobby.getPlayers())
        {
            if(lp.getName().equals(fromPlayerName))
            {
                lp.signalActivity();
            }
        }

        JSONObject res = new JSONObject();
        res.put("cmd", "pong");
        res.put("lobbyName", lobbyName);
        res.put("gameName", gameName);
        res.put("maxPlayers", maxPlayers);
        res.put("currentPlayers", curPlayers);
        res.put("lobbyId", lobby.getID());


        Packet packet = new Packet(res.toString());
        packet.setDestination(from);
        GPC.getNetwork().sendPacket(packet);
        return true;
    }
}
