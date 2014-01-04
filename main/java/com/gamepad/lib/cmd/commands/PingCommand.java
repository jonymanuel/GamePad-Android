package com.gamepad.lib.cmd.commands;

import com.gamepad.MainActivity;
import com.gamepad.lib.GPC;
import com.gamepad.lib.cmd.ICommand;
import com.gamepad.lib.net.IpAddress;
import com.gamepad.lib.net.NetworkStation;
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
        String lobbyName = input.getString("lobbyname");

        JSONObject res = new JSONObject();
        res.put("cmd", "pong");
        res.put("lobbyname", lobbyName);

        Packet packet = new Packet(res.toString());
        packet.setDestination(from);
        GPC.getNetwork().sendPacket(packet);
        return true;
    }
}
