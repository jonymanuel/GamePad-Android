package com.gamepad.lib.net;

import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Fabian on 15.12.13.
 */
public class NetworkSenderRunnable implements Runnable {
    private Queue<Packet> packetQueue;

    public NetworkSenderRunnable()
    {
        packetQueue = new LinkedList<Packet>();
    }

    public void sendPacket(String message, InetAddress destination)
    {
        Packet p = new Packet(message);
        p.setDestination(destination);
        packetQueue.add(p);
    }

    private void safeSleep(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(Exception ex)
        {}
    }

    private void sendDatagram(Packet packet)
    {
        DatagramSocket s =  null;
        try
        {
            String messageStr = packet.getMessage();
            int server_port = 56020;
            s = new DatagramSocket();
            InetAddress local = packet.getDestination();
            int msg_length = messageStr.length();
            byte[] message = messageStr.getBytes();
            DatagramPacket p = new DatagramPacket(message, msg_length, local, server_port);
            s.send(p);
            Log.d("NetworkSender", "Sent " + messageStr + " to " + packet.getDestination().toString());
        }
        catch(Exception ex)
        {
            Log.e("NetworkSender", "Error while sending packet.");
            ex.printStackTrace();
        }
    }

    private void threadMethod()
    {
        Log.d("NetworkSenderRunnable", "Starting network sender thread");
        while(true)
        {
            while(packetQueue.size() > 0)
            {
                Packet currentPacket = packetQueue.remove();
                sendDatagram(currentPacket);
            }
            safeSleep(100);
        }
    }

    @Override
    public void run() {
        threadMethod();
    }
}
