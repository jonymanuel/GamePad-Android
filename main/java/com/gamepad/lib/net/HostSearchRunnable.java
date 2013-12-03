package com.gamepad.lib.net;

import com.gamepad.MainActivity;

/**
 * Author: root
 * Date: 10.10.13.
 */
public class HostSearchRunnable implements Runnable
{

    @Override
    public void run()
    {
        //get the network class shortcut
        Network n = MainActivity.getBaseGPC().getNetwork();

        //make a new packet with the ping packet content. message: ping
        //this is the message the clients get
        // the method used is a ping pong
        // sending ping --> receiving pong
        Packet pingPacket = new Packet("ping");

        //the ip address to send to: 255.255.255.0 <-- this is the broadcast address
        Byte b = Byte.valueOf("255");
        Byte[] ip = new Byte[] { b, b, b, b };

        //the network station to send to (working with the ip address above)
        NetworkStation station = new NetworkStation(ip);

        //sending the actual packet
        n.sendPacket(pingPacket,station);
    }
}
