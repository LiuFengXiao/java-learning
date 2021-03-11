package bin;



import java.awt.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.bluetooth.RemoteDevice;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import javax.swing.*;

public class BluetoothClient {
    public static JFrame GUI = null;
    public static JDialog CONGUI=null;
    private  StreamConnection streamConnection;
    private  OnDiscoverListener onDiscoverListener = null;
    private  OnClientListener onClientListener = null;

    
    public interface OnClientListener {
        void onConnected(InputStream inputStream, OutputStream outputStream);
        void onConnectionFailed();
        void onDisconnected();
        void onClose();
    }
    
    public interface OnDiscoverListener {
        void onDiscover(RemoteDevice remoteDevice);
    }
    
    
    
    
    
    public BluetoothClient() {
    }
    
    
    public OnDiscoverListener getOnDiscoverListener() {
        return onDiscoverListener;
    }


    public void setOnDiscoverListener(OnDiscoverListener onDiscoverListener) {
        this.onDiscoverListener = onDiscoverListener;
    }


    public OnClientListener getClientListener() {
        return onClientListener;
    }


    public void setClientListener(OnClientListener onClientListener) {
        this.onClientListener = onClientListener;
    }

    public void find() throws IOException, InterruptedException {
        GUI = new JFrame("附近设备");    //初始化界面
        GUI.setLocation(100, 100);
        GUI.setSize(400, 500);
        GUI.setLayout(new FlowLayout());
        GUI.setResizable(true);
        Map<String,RemoteDevice> devicesDiscovered = RemoteDeviceDiscovery.getDevices();      //搜索附近设备

        for(String str:devicesDiscovered.keySet()){
            onDiscoverListener.onDiscover(devicesDiscovered.get(str));
            System.out.println("迭代设备");
        }
    }
    
    public void startClient(RemoteDevice remoteDevice, String serviceUUID) throws IOException, InterruptedException {
        String url = RemoteDeviceDiscovery.searchService(remoteDevice, serviceUUID);

        System.out.println("url :"+url);

        streamConnection = (StreamConnection) Connector.open(url);
        if (this.onClientListener != null) {
            this.onClientListener.onConnected(streamConnection.openInputStream(), streamConnection.openOutputStream());



        }
    }


}