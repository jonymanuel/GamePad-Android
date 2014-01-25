package com.gamepad.lib.net;

import android.util.Log;

import com.gamepad.lib.GPC;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by root on 04.10.13.
 */
public class NetworkListenerRunnable implements Runnable
{
    private Vector _listeners;
    private static DatagramSocket server;
    private static Boolean socketCreated = false;

    public void addPacketEventListener(PacketEvent listener)
    {
        if (_listeners == null)
        {
            _listeners = new Vector();
        }
        _listeners.addElement(listener);
    }

    private void firePacketEvent(Packet packet)
    {
        if (_listeners != null && !_listeners.isEmpty())
        {
            Enumeration e = _listeners.elements();
            while (e.hasMoreElements())
            {
                PacketEvent pe = (PacketEvent) e.nextElement();
                try
                {
                    pe.newPacket(packet);
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    private String readMessage(DatagramPacket packet) throws IOException
    {
        return new String(packet.getData(), 0, packet.getLength());
    }

    public void run()
    {
        Boolean fatalError = false;
        server = null;
        if (!socketCreated)
        {
            try
            {
                server = new DatagramSocket(56020);
                socketCreated = true;
            }
            catch (Exception ex)
            {
                fatalError = true;
                Log.e("NetworkListenerRunnable", ex.getMessage());
                return;
            }
        }
        else
        {
            Log.e("NetworkListenerRunnable", "Using existing instance from serverSocket");
        }
        Log.e("NetworkListenerRunnable", "Running");
        //Server thread
        while (true)
        {
            try
            {
                GPC.getNetwork().getLocalIps();
                byte[] receiveData = new byte[4096];
                DatagramPacket result = new DatagramPacket(receiveData,receiveData.length);

                server.receive(result);
                ArrayList<String> localIps = GPC.getNetwork().getLocalIps();
                String resultIp = result.getAddress().toString();
                if(localIps.contains(resultIp))
                {
                    continue;
                }
                String message = readMessage(result);
                Packet packet = new Packet(message);
                packet.setFrom(result.getAddress());
                firePacketEvent(packet);
                Log.e("NetworkListenerRunnable", "Got new message: " + message);
            }
            catch (Exception ex)
            {

            }
        }
    }
}
