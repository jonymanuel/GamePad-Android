package com.gamepad.lib.net;

import android.util.Log;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by root on 04.10.13.
 */
public class Network implements PacketEvent
{
    private Vector _listeners;
    private NetworkListenerRunnable socketListener;
    private Thread socketListenerThread;
    private HostSearchRunnable hostSearchRunnable;

    //creates a new instance of the network class
    public Network()
    {
        Log.d("Network", "Starting SocketListener");
        socketListener = new NetworkListenerRunnable();
        socketListenerThread = new Thread(socketListener);
        socketListenerThread.start();
        socketListener.addPacketEventListener(this);
    }

    //the method that gets fired if a new packet arrives
    public void newPacket(Packet packet)
    {
        firePacketEvent(packet);
    }

    //add a new method to get fired if a new packet arrives
    public void addPacketEventListener(PacketEvent listener)
    {
        if (_listeners == null)
        {
            _listeners = new Vector();
        }
        _listeners.addElement(listener);
    }

    //fires the packet event and triggers all methods that are hooked into the event
    protected void firePacketEvent(Packet packet)
    {
        if (_listeners != null && _listeners.isEmpty())
        {
            Enumeration e = _listeners.elements();
            while (e.hasMoreElements())
            {
                PacketEvent pe = (PacketEvent) e.nextElement();
                pe.newPacket(packet);
            }
        }
    }

    //gets the local ip of the mobile phone
    public IpAddress getLocalIp(){
        String ipAddress = null;
        Enumeration<NetworkInterface> net = null;
        try {
            net = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        while(net.hasMoreElements()){
            NetworkInterface element = net.nextElement();
            Enumeration<InetAddress> addresses = element.getInetAddresses();
            while (addresses.hasMoreElements()){
                InetAddress ip = addresses.nextElement();
                if (ip instanceof Inet4Address){

                    if (ip.isSiteLocalAddress()){

                        ipAddress = ip.getHostAddress();
                    }

                }

            }
        }
        try
        {
            return IpAddress.parse(ipAddress);
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    //Send a broadcast to find the host in your network
    public void startSearchHosts()
    {
        if(hostSearchRunnable == null)
        {
            hostSearchRunnable = new HostSearchRunnable();
            hostSearchRunnable.run();
        }
    }

    //sends a given packet to a given networkstation
    public void sendPacket(Packet packet, NetworkStation station)
    {
        SendPacketRunnable runnable = new SendPacketRunnable();
        runnable.setPacket(packet);
        runnable.setStation(station);
        new Thread(runnable).run();
    }
}
