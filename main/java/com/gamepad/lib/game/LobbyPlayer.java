package com.gamepad.lib.game;

import com.gamepad.lib.net.Packet;
import com.gamepad.lib.net.PacketEvent;

import java.net.InetAddress;

/**
 * Created by Fabian on 16.12.13.
 */
public class LobbyPlayer implements PacketEvent
{
    private InetAddress ip;
    private int port;
    private String name;

    public LobbyPlayer()
    {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public int getPort() {
        return port;
    }

    public InetAddress getIp() {
        return ip;
    }

    @Override
    public void newPacket(Packet p) {

    }
}
