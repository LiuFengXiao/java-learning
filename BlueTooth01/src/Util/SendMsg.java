package Util;

import bin.BluetoothClient;
import bin.BluetoothClientTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.LinkedBlockingDeque;

public class SendMsg implements Runnable {
    JTextField MytextField=null;
    private  OutputStream outputStream = null;
    private  LinkedBlockingDeque<Integer> queue = null;
    public SendMsg(OutputStream outputStream, LinkedBlockingDeque<Integer> queue){
        this.outputStream=outputStream;
        this.queue = queue;
        JFrame jFrame = new JFrame("小车控制面板");
        jFrame.setSize(400,500);
        jFrame.setLocation(100,600);
        jFrame.setVisible(true);
        jFrame.setLayout(new FlowLayout());
        JButton b = new JButton();
        b.setSize(40,20);
        MytextField = new JTextField();
        MytextField.setSize(200,60);
        MytextField.setText("请输入数值");
        MytextField.setVisible(true);
        b.setText("发送");
        b.setSize(100,60);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text =  MytextField.getText();
                try{
                    int num = Integer.parseInt(text);
                    queue.put(num);
                }catch (Exception e1){
                    e1.printStackTrace();
                }

            }
        });

        jFrame.add(MytextField);
        jFrame.add(b);
    }

    public void sendMsg(){
        while (true) {
            try {
                int msg = (int) queue.take();
                outputStream.write(msg);
                outputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void run() {
        sendMsg();
    }
}
