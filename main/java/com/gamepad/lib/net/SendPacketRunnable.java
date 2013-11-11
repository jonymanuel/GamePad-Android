package com.gamepad.lib.net;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Author: root
 * Date: 07.10.13.
 */
public class SendPacketRunnable implements Runnable
{
    private NetworkStation station;
    private Packet packet;
    private Boolean settedStation;
    private Boolean settedPacket;

    public void setStation(NetworkStation station)
    {
        this.station = station;
        settedStation = true;
    }

    public void setPacket(Packet packet)
    {
        this.packet = packet;
        settedPacket = true;
    }

    @Override
    public void run()
    {
        if(!settedPacket | !settedStation)
        {
            Log.d("SendPacketRunnable", "Could not send packet: No station or packet defined");
            return;
        }
        Socket s =  null;
        try
        {
            s = new Socket(station.getIpAddress().toString(), 56020);
            OutputStream stream = s.getOutputStream();
            String toSend = packet.getMessage();
            BufferedOutputStream bStream = new BufferedOutputStream(stream);
            bStream.write(toSend.getBytes());
            bStream.close();
            stream.close();
            s.shutdownOutput();
            s.close();
        }
        catch(Exception ex) {}


    }
}
