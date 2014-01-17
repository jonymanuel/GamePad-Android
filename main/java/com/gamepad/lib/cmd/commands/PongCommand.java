package com.gamepad.lib.cmd.commands;

import com.gamepad.lib.GPC;
import com.gamepad.lib.cmd.ICommand;
import com.gamepad.lib.game.Lobby;

import org.json.JSONObject;

/**
 * Created by Fabian on 07.01.14 12:58 in project ${PROJECT_NAME}.
 */
public class PongCommand implements ICommand {

    @Override
    public String getCommandString() {
        return "pong";
    }

    @Override
    public Boolean runCommand(JSONObject input) throws Exception {
        String lobbyName = input.getString("lobbyName");
        String gameName = input.getString("gameName");
        String maxPlayers = input.getString("maxPlayers");
        String currentPlayers = input.getString("currentPlayers");
        int lobbyId = input.getInt("lobbyId");
        String from = input.getString("from");

        Lobby lob = new Lobby(lobbyId);
        lob.setName(lobbyName);
        lob.setGameName(gameName);
        lob.setHostIp(from);
        lob.setMaxPlayers(Integer.parseInt(maxPlayers));
        lob.addPlayerRange(Lobby.getPlayersFromString(currentPlayers));
        GPC.getJoin().addLobby(lob);
        return true;
    }
}
