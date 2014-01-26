package com.gamepad.lib.cmd.commands;

import com.gamepad.lib.GPC;
import com.gamepad.lib.cmd.ICommand;

import org.json.JSONObject;

/**
 * Created by Fabian on 26.01.14.
 */
public class LeaveLobbyCommand implements ICommand {
    @Override
    public String getCommandString() {
        return "leaveLobby";
    }

    @Override
    public Boolean runCommand(JSONObject input) throws Exception {
        String who = input.getString("playerName");
        GPC.getHost().playerLeft(who);
        return true;
    }
}
