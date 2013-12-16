package com.gamepad.lib.game;

import com.gamepad.lib.GPC;
import com.gamepad.lib.net.Packet;
import com.gamepad.lib.net.PacketEvent;

/**
 * Created by Fabian on 16.12.13.
 */
public class Host implements PacketEvent
{
    private Lobby lobby;

    public Host()
    {

    }

    public Lobby getLobby()
    {
        return lobby;
    }

    public void createLobby()
    {
        lobby = new Lobby();
        lobby.setName("Poker by Fabi");
    }

    @Override
    public void newPacket(Packet p) {
        if(p.getMessage().startsWith("ping"))
        {
            Packet packet = new Packet("pong " + lobby.getName());
            packet.setDestination(p.getFrom());
            GPC.getNetwork().sendPacket(packet);
        }
    }
}
