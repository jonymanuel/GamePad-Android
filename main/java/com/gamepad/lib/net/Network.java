package com.gamepad.lib.net;

import android.util.Log;

import com.gamepad.lib.GPC;

import org.json.JSONObject;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by oot  on 4.10.13. 12:52 in project ${PROJECT_NAME}.
 */
public class Network implements PacketEvent {
    private Vector _listeners;
    private NetworkListenerRunnable socketListener;
    private Thread socketListenerThread;
    private Thread networkSenderThread;
    private NetworkSenderRunnable senderRunnable;

    //creates a new instance of the network class
    public Network() {
        Log.d("Network", "Starting SocketListener");
        socketListener = new NetworkListenerRunnable();
        socketListenerThread = new Thread(socketListener);
        socketListenerThread.start();
        socketListener.addPacketEventListener(this);

        senderRunnable = new NetworkSenderRunnable();
        networkSenderThread = new Thread(senderRunnable);
        networkSenderThread.start();
    }

    //the method that gets fired if a new packet arrives
    public void newPacket(Packet packet) {
        firePacketEvent(packet);
    }

    //add a new method to get fired if a new packet arrives
    public void addPacketEventListener(PacketEvent listener) {
        if (_listeners == null) {
            _listeners = new Vector();
        }
        _listeners.addElement(listener);
    }

    //fires the packet event and triggers all methods that are hooked into the event
    protected void firePacketEvent(Packet packet) {
        if (_listeners != null && !_listeners.isEmpty()) {
            Enumeration e = _listeners.elements();
            while (e.hasMoreElements()) {
                PacketEvent pe = (PacketEvent) e.nextElement();
                pe.newPacket(packet);
            }
        }
    }

    public Boolean removePacketEventListener(PacketEvent event) {
        if (_listeners == null || _listeners.remove(event)) {
            return true;
        }
        return false;
    }

    //gets the local ip of the mobile phone
    public ArrayList<String> getLocalIps() {
        ArrayList<String> ipAddress = new ArrayList<String>();
        Enumeration<NetworkInterface> net = null;
        try {
            net = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        while (net.hasMoreElements()) {
            NetworkInterface element = net.nextElement();
            Enumeration<InetAddress> addresses = element.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress ip = addresses.nextElement();
                if (ip instanceof Inet4Address) {

                    if (ip.isSiteLocalAddress()) {

                        ipAddress.add(ip.toString());
                    }

                }

            }
        }
        try {
            return ipAddress;
        } catch (Exception ex) {
            return null;
        }
    }

    public static ArrayList<String> getBroadcasts() throws SocketException {
        ArrayList<String> result = new ArrayList<String>();
        System.setProperty("java.net.preferIPv4Stack", "true");
        for (Enumeration<NetworkInterface> niEnum = NetworkInterface.getNetworkInterfaces(); niEnum.hasMoreElements(); ) {
            NetworkInterface ni = niEnum.nextElement();
            if (!ni.isLoopback()) {

                for (InterfaceAddress interfaceAddress : ni.getInterfaceAddresses()) {
                    if(!interfaceAddress.toString().contains(":"))
                    {
                        result.add(interfaceAddress.getBroadcast().toString().substring(1));
                    }
                }
            }
        }
        return result;
    }

    //Send a broadcast to find the host in your network
    public void sendPingBroadcast() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("cmd", "ping");
            obj.put("playerName", GPC.getJoin().getLocalPlayerName());
        } catch (Exception ex) {
        }

        ArrayList<String> broadcastips = new ArrayList<String>();
        try {
            broadcastips = getBroadcasts();
        } catch (Exception ex) {

        }
        for(String ip : broadcastips)
        {
            Packet pingPacket = new Packet(obj.toString());
            pingPacket.setDestination(ip);
            sendPacket(pingPacket);
        }

    }

    //sends a given packet to a given networkstation
    public void sendPacket(Packet packet) {
        senderRunnable.sendPacket(packet.getMessage(), packet.getDestination());
    }
}
