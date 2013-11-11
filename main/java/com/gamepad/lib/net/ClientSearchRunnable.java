package com.gamepad.lib.net;

import com.gamepad.MainActivity;

/**
 * Author: root
 * Date: 10.10.13.
 */
public class ClientSearchRunnable implements Runnable
{

    @Override
    public void run()
    {
        //get the network class shortcut
        Network n = MainActivity.getBaseGPC().getNetwork();

        //make a new packet with the ping packet content. message: ping
        //this is the message the clients get
        // the method used is a brickstone fart
        // sending brickstone --> receiving fart
        Packet pingPacket = new Packet("brickstone");

        //the ip addres to send to: 255.255.255.0 <-- this is the broadcast address
        byte[] ip = new byte[] { (byte)255, (byte)255, (byte)255, (byte)255 };

        //the network station to send to (working with the ip address above)
        NetworkStation station = new NetworkStation(ip);

        //sending the actual packet
        n.sendPacket(pingPacket,station);
    }
}
