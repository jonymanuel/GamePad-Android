package com.gamepad.lib.cmd.commands;

import com.gamepad.lib.GPC;
import com.gamepad.lib.cmd.ICommand;
import com.gamepad.lib.game.Lobby;

import org.json.JSONObject;

/**
 * Created by Fabian on 07.01.14.
 */
public class PongCommand implements ICommand {

    @Override
    public String getCommandString() {
        return "pong";
    }

    @Override
    public Boolean runCommand(JSONObject input) throws Exception {
        String lobbyName = input.getString("lobbyname");
        Lobby lob = new Lobby();
        lob.setName(lobbyName);
        GPC.getJoin().addLobby(lob);
        return true;
    }
}
