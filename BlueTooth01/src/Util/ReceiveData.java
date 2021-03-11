package Util;

import POJO.JsonInfo;
import POJO.JsonObj;


import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

import static bin.BluetoothClientTest.CARID;

public class ReceiveData implements Runnable {
    private InputStream inputStream =null;
    private LinkedBlockingDeque<JsonObj> queue = null;
    public ReceiveData(InputStream inputStream,LinkedBlockingDeque<JsonObj> queue){
        this.inputStream= inputStream;
        this.queue= queue;
    }
    public void warp(byte[] buffer,int j){
        for(int i=0;i<j;i++){
            System.out.println(buffer[i]);
        }






    }

    // 接收蓝牙发送过来的数据并封装成obj压入消息队列
    private void produce(){
        byte[] buffer = new byte[1024];
        boolean flag = true;
        ArrayList<Integer> list = new ArrayList<>();
        boolean read_stop=false;
        int[] ans= new int[6];
        while(flag){
            int i= 0;
            int count=0;
            try {
                i = inputStream.read(buffer);
                for(int k=0;k<i;k++){
                    int data =buffer[k]&0x00ff;
                   // System.out.println("接收到的数据: "+data);
                    if(read_stop==true&&data!=0xff) read_stop=false;

                    if(data==0xff){
                        if(read_stop==true) {
                            //一个周期结束
                            System.out.println("----------------"+list.size());
                            if(list.size()!=13){
                                list.clear(); //接收数据不全，丢弃数据
                                System.out.println("总共丢失count: "+count++);
                            }
                            else{
                                int ind=0;

                            for(int s=0;s<list.size()-1;s=s+2){
                                System.out.println(list.get(s)+"=============");
                                int sum=list.get(s)<<8;
                                sum+=list.get(s+1);
                                ans[ind++]=sum;
                            }

                            JsonObj obj = new JsonObj();
                            obj.setCarId(CARID);
                            obj.setConId(ans[0]);
                            obj.setSpeed(ans[1]);
                            obj.setZhongZhi(ans[2]);
                            obj.setPid(ans[3]);
                            obj .setPwmA(ans[4]);
                            obj.setPwmB(ans[5]);
                            System.out.println(obj);
                            list.clear();
                            try {
                                  queue.put(obj);    // 往消息队列里添加obj
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }}
                        }
                        else{
                            read_stop=true;
                            list.add(data);
                        }
                    }
                    else{
                        list.add(data);
                    }
                }




            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void run() {
        produce();
    }
}
