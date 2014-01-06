package com.gamepad.lib.game;

import com.gamepad.lib.GPC;
import com.gamepad.lib.cmd.CommandParser;
import com.gamepad.lib.cmd.ICommand;
import com.gamepad.lib.cmd.commands.PingCommand;
import com.gamepad.lib.net.Packet;
import com.gamepad.lib.net.PacketEvent;

import org.json.JSONObject;

/**
 * Created by Fabian on 16.12.13.
 */
public class Host implements PacketEvent
{
    private Lobby lobby;
    private CommandParser cmdParser;

    public Host()
    {
        GPC.getNetwork().addPacketEventListener(this);
        cmdParser = new CommandParser();
    }

    public Lobby getLobby()
    {
        return lobby;
    }

    public void createLobby(String name)
    {
        lobby = new Lobby();
        lobby.setName(name);
        cmdParser.RegisterCommand(new PingCommand());
    }

    @Override
    public void newPacket(Packet p) {
        if(lobby == null)
        {
            return;
        }
        try
        {
            JSONObject obj = cmdParser.parseCommand(p.getMessage());
            obj.put("from", p.getFrom().toString());
            obj.put("lobbyname", lobby.getName());
            ICommand cmd = cmdParser.findCommandByCommandString(obj.getString("cmd"));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
