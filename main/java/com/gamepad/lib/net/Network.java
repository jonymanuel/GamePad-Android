package com.gamepad.lib.net;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.gamepad.MainActivity;
import com.gamepad.NetworkDebugActivity;

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

    public Network()
    {
        Log.d("Network", "Starting SocketListener");
        socketListener = new NetworkListenerRunnable();
        socketListenerThread = new Thread(socketListener);
        socketListenerThread.start();
        socketListener.addPacketEventListener(this);
    }

    public void newPacket(Packet packet)
    {
        firePacketEvent(packet);
    }

    public void addPacketEventListener(PacketEvent listener)
    {
        if (_listeners == null)
        {
            _listeners = new Vector();
        }
        _listeners.addElement(listener);
    }

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

    public String getLocalIp(){
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
        return ipAddress;
    }

    public void startSearchClients()
    {
        Context context = NetworkDebugActivity.getContext();
        Toast.makeText(context, "Starting to search clients", Toast.LENGTH_LONG).show();

    }

    public void sendPacket(Packet packet, NetworkStation station)
    {
        SendPacketRunnable runnable = new SendPacketRunnable();
        runnable.setPacket(packet);
        runnable.setStation(station);
        new Thread(runnable).run();
    }
}
