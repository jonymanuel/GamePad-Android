package com.gamepad.lib.cmd.commands;

import com.gamepad.lib.GPC;
import com.gamepad.lib.cmd.ICommand;
import com.gamepad.lib.game.LobbyPlayer;
import com.gamepad.lib.net.Packet;

import org.json.JSONObject;

/**
 * Created by Fabian on 04.01.14.
 */
public class JoinCommand implements ICommand
{

    @Override
    public String getCommandString() {
        return "joinlobby";
    }

    @Override
    public Boolean runCommand(JSONObject input) throws Exception {
        LobbyPlayer player = new LobbyPlayer();
        player.setName(input.getString("playername"));
        player.setIp(input.getString("from"));
        GPC.getHost().getLobby().addPlayer(player);
        String from = input.getString("from");

        JSONObject res = new JSONObject();
        res.put("cmd", "joinaccepted");
        res.put("joinedlobby",  GPC.getHost().getLobby().getName());

        Packet packet = new Packet(res.toString());
        packet.setDestination(from);
        GPC.getNetwork().sendPacket(packet);
        return true;
    }
}
