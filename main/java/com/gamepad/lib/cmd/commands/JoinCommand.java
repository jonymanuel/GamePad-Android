package com.gamepad.lib.cmd.commands;

import com.gamepad.LobbyActivity;
import com.gamepad.lib.GPC;
import com.gamepad.lib.cmd.ICommand;
import com.gamepad.lib.game.LobbyPlayer;

import org.json.JSONObject;

/**
 * Created by Fabian on 04.01.14.
 */
public class JoinCommand implements ICommand
{

    @Override
    public String getCommandString() {
        return "join";
    }

    @Override
    public Boolean runCommand(JSONObject input) throws Exception {
        LobbyPlayer player = new LobbyPlayer();
        player.setName(input.getString("playername"));
        player.setIp(input.getString("from"));
        GPC.getHost().getLobby().addPlayer(player);
        return true;
    }
}
