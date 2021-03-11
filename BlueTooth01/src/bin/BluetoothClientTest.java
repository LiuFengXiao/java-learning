package bin;


import POJO.JsonObj;
import Util.ReceiveData;
import Util.SendData;
import Util.SendMsg;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingDeque;

import javax.bluetooth.RemoteDevice;
import javax.swing.*;

public class BluetoothClientTest {
    public static   BluetoothClient client=null;
    public static  int CARID=1;
    public final static String serverUUID="0000110100001000800000805F9B34FB";
    public static LinkedBlockingDeque<JsonObj> queue = new LinkedBlockingDeque<>();
    public static LinkedBlockingDeque<Integer> controllqueue = new LinkedBlockingDeque<>();
    public static void main(String[] argv) {
        client = new BluetoothClient();
        
        Vector<RemoteDevice> remoteDevices = new Vector<>();
        
        client.setOnDiscoverListener(new BluetoothClient.OnDiscoverListener() {

            @Override
            public void onDiscover(RemoteDevice remoteDevice) {
                remoteDevices.add(remoteDevice);
            }
            
        });
        
        client.setClientListener(new BluetoothClient.OnClientListener() {

            @Override        //连接蓝牙
            public void onConnected(InputStream inputStream, OutputStream outputStream) {
                BluetoothClient.CONGUI.setTitle("连接成功！");
                try {
                    Thread.sleep(500);
                    BluetoothClient.CONGUI.setVisible(false);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //开一个线程接收数据，封装成json数据发送到消息队列
                Thread receivedata = new Thread(new ReceiveData(inputStream,queue));
                receivedata.start();

                //开一个线程发送数据，控制小车车速等等
                Thread sendmsg = new Thread(new SendMsg(outputStream,controllqueue));
                sendmsg.start();

                // 开启一个线程发送数据到云端
                Thread sendData = new Thread(new SendData(queue));
                sendData.start();







            }

            @Override
            public void onConnectionFailed() {
                System.out.printf("连接失败！");
            }

            @Override
            public void onDisconnected() {
                
            }

            @Override
            public void onClose() {
                
            }
            
        });
        try {
            client.find();
            

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
