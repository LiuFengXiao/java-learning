package Util;

import GUI.AnalyzeData;

import POJO.JsonObj;
import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;

import java.net.URL;

import java.util.concurrent.LinkedBlockingDeque;

public class SendData implements Runnable {
    private LinkedBlockingDeque<JsonObj> queue = null;
    private StringBuffer buffer =new StringBuffer();
    String requesturl = "http://121.36.17.9:80/sendData";
    URL url=null;
    HttpURLConnection connection=null;
    InputStream inputStream = null;
    OutputStream outputStream = null;

    public SendData(LinkedBlockingDeque<JsonObj> queue){
        this.queue = queue;

    }
    public void showData(JsonObj jsonInfo){
        AnalyzeData.getData(jsonInfo);
    }


    public  void  sendDataToService(JsonObj jsonInfo) {
        try {
            url = new URL(requesturl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.setRequestProperty("Connection", "Keep-Alive"); // 设置发送数据的格式
            connection.connect();

            Gson  gson = new Gson();
            String json = gson.toJson(jsonInfo);
            //一定要用BufferedReader 来接收响应， 使用字节来接收响应的方法是接收不到内容的
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(json);
            out.flush();      //发送数据到云端
            out.close();
            System.out.println("the json:   "+json);
            System.out.println("-----------以下是返回数据-----------");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            String res = "";
            while ((line = reader.readLine()) != null) {
                res += line;
            }
            reader.close();
            System.out.println(res);


        } catch (Exception e) {
            e.printStackTrace();
        }





    }
    public void sendData(){
        while (true){

            try {
                JsonObj jsonInfo = queue.take();
                showData(jsonInfo);   //启动ui界面，实时展示接收到的数据
                sendDataToService(jsonInfo);   //发送数据到云端
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    @Override
    public void run()
    {
        sendData();
    }
}
