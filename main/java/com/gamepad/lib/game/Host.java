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
public class Host implements PacketEvent, Mode
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
        registerCommands();
    }

    public void destroyLobby()
    {
        lobby = null;
    }

    private void registerCommands()
    {
        cmdParser.clearCommands();
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
            obj.put("from", p.getFrom().toString().replace("/", ""));
            obj.put("lobbyname", lobby.getName());
            ICommand cmd = cmdParser.findCommandByCommandString(obj.getString("cmd"));
            cmd.runCommand(obj);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void initMode() {
        registerCommands();
    }

    @Override
    public void clearMode() {
        cmdParser.clearCommands();
        destroyLobby();

    }
}
