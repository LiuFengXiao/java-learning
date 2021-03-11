package bin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

import javax.bluetooth.DataElement;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.swing.*;




public class RemoteDeviceDiscovery {
    public final static HashMap<String,RemoteDevice> devicesDiscovered = new HashMap<>();

    public final static Vector<String> serviceFound = new Vector<String>();

    final static Object serviceSearchCompletedEvent = new Object();
    final static Object inquiryCompletedEvent = new Object();


    private static DiscoveryListener listener = new DiscoveryListener() {
        public void inquiryCompleted(int discType) {
            System.out.println("#" + "搜索完成");
            synchronized (inquiryCompletedEvent) {
                inquiryCompletedEvent.notifyAll();
            }
        }

        @Override
        public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass deviceClass) {
            devicesDiscovered.put(remoteDevice.getBluetoothAddress(),remoteDevice);

            try {
                System.out.println("发现设备：" + remoteDevice.getFriendlyName(false) + " " + remoteDevice.getBluetoothAddress());
                showBlueTooth(remoteDevice);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
            for (int i = 0; i < servRecord.length; i++) {
                String url = servRecord[i].getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
                if (url == null) {
                    continue;
                }
                serviceFound.add(url);
                DataElement serviceName = servRecord[i].getAttributeValue(0x0100);
                if (serviceName != null) {
                    System.out.println("service " + serviceName.getValue() + " found " + url);
                } else {
                    System.out.println("service found " + url);
                }
            }
            System.out.println("#" + "servicesDiscovered");
        }

        @Override
        public void serviceSearchCompleted(int arg0, int arg1) {
            System.out.println("#" + "serviceSearchCompleted");
            synchronized (serviceSearchCompletedEvent) {
                serviceSearchCompletedEvent.notifyAll();
            }
        }
    };

    // 扫描附近设备
    private static void findDevices() throws IOException, InterruptedException {

        devicesDiscovered.clear();

        synchronized (inquiryCompletedEvent) {

            LocalDevice ld = LocalDevice.getLocalDevice();

            System.out.println("#本机设备名称:" + ld.getFriendlyName());

            boolean started = LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC, listener);

            if (started) {
                System.out.println("#" + "等待搜索完成");
                inquiryCompletedEvent.wait();
                LocalDevice.getLocalDevice().getDiscoveryAgent().cancelInquiry(listener);
                System.out.println("共发现设备" + devicesDiscovered.size());
            }
        }
    }


    // 存储附近设备信息
    public static Map<String,RemoteDevice> getDevices() throws IOException, InterruptedException {
        findDevices();
        return devicesDiscovered;
    }

    public static String searchService(RemoteDevice btDevice, String serviceUUID) throws IOException, InterruptedException {

        UUID[] searchUuidSet = new UUID[]{new UUID(serviceUUID, false)};

        int[] attrIDs = new int[]{
                0x0100 // Service name
        };

        synchronized (serviceSearchCompletedEvent) {
            System.out.println("search services on " + btDevice.getBluetoothAddress() + " " + btDevice.getFriendlyName(false));
            LocalDevice.getLocalDevice().getDiscoveryAgent().searchServices(attrIDs, searchUuidSet, btDevice, listener);
            serviceSearchCompletedEvent.wait();
        }

        if (serviceFound.size() > 0) {
            return serviceFound.elementAt(0);
        } else {
            return "";
        }
    }


    public static void showBlueTooth(RemoteDevice remoteDevice) {

        try {
            String name = remoteDevice.getFriendlyName(false) + "/n" + remoteDevice.getBluetoothAddress();
            JButton b = new JButton();
            b.setText(name);
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (BluetoothClient.CONGUI != null) {
                        BluetoothClient.CONGUI.setVisible(false);
                    }
                    BluetoothClient.CONGUI = new JDialog(BluetoothClient.GUI);
                    String[] removeinfo = b.getText().split("/n");

                    BluetoothClient.CONGUI.setTitle("正在连接" + removeinfo[0]);
                    BluetoothClient.CONGUI.setSize(200, 40);
                    BluetoothClient.CONGUI.setLocation(300, 300);
                    BluetoothClient.CONGUI.setLayout(null);
                    BluetoothClient.CONGUI.setResizable(false);
                    BluetoothClient.CONGUI.setVisible(true);
                    try {
                        BluetoothClientTest.client.startClient(devicesDiscovered.get(removeinfo[1]), BluetoothClientTest.serverUUID);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }


                }
            });
            BluetoothClient.GUI.add(b);
            BluetoothClient.GUI.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}