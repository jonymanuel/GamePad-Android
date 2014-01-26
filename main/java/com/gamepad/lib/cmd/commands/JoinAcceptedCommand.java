package com.gamepad.lib.cmd.commands;

import android.util.Log;

import com.gamepad.lib.GPC;
import com.gamepad.lib.cmd.ICommand;
import com.gamepad.lib.game.Lobby;

import org.json.JSONObject;

/**
 * Created by Fabian on 10.01.14.
 */
public class JoinAcceptedCommand implements ICommand {

    @Override
    public String getCommandString() {
        return "joinaccepted";
    }

    @Override
    public Boolean runCommand(JSONObject input) throws Exception {
        if(GPC.getJoin().isInLobby())
        {
            return false;
        }
        String joinedLobby = input.getString("joinedlobby");
        Lobby theLobby = GPC.getJoin().getLobbyByName(joinedLobby);
        GPC.getJoin().setCurrentLobby(theLobby);
        Log.d("JoinAcceptedCommand", "The join got accepted. JoinedLobby: " + joinedLobby);
        return true;
    }
}
