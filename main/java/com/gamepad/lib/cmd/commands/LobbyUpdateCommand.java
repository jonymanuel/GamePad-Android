package com.gamepad.lib.cmd.commands;

import com.gamepad.lib.GPC;
import com.gamepad.lib.cmd.ICommand;

import org.json.JSONObject;

/**
 * Created by Fabian on 04.01.14.
 */
public class LobbyUpdateCommand implements ICommand {
    @Override
    public String getCommandString() {
        return "lobbyupdate";
    }

    @Override
    public Boolean runCommand(JSONObject input) throws Exception {
        GPC.getJoin().setCurrentLobby(GPC.getJoin().getLobbyByName(input.getString("lobbyname")));
        return true;
    }
}
