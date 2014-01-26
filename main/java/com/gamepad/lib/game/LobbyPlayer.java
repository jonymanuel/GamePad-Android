package com.gamepad.lib.game;

import com.gamepad.lib.net.Packet;
import com.gamepad.lib.net.PacketEvent;

import java.net.InetAddress;
import java.util.Date;

/**
 * Created by Fabian on 16.12.13.
 */
public class LobbyPlayer implements PacketEvent
{
    private InetAddress ip;
    private int port;
    private String name;
    private long lastActivity;

    public LobbyPlayer()
    {
        signalActivity();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIp(String ip) {
        InetAddress addr = null;
        try { addr = InetAddress.getByName(ip); } catch(Exception ex){};
        if(addr != null)
        {
            this.ip = addr;
        }
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isOffline()
    {
        long current = new Date().getTime();
        long diff = current - lastActivity;
        if(diff >= 10 * 1000)
        {
            return true;
        }
        return false;
    }

    public void signalActivity()
    {
        lastActivity = new Date().getTime();
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
