package com.example.demo.controller;


import com.example.demo.POJO.JsonObj;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

import java.util.List;
import java.util.Map;

@Controller
public class opsMysql  {
    @Autowired
    JdbcTemplate jdbcTemplate =null;
    @ResponseBody
    @RequestMapping("/getinfo")
    //获取小车运动数据
    public JsonObj getInfo(String carid,int id){
        id+=456;
        System.out.println("carid "+carid);
        System.out.println("conid "+id);
        String sql = "select * from car_record where id="+id+" and carid="+carid+ " limit 1;";
        System.out.println(sql);
        List<Map<String,Object>> resutset =  jdbcTemplate.queryForList(sql);
        if(resutset.size()==0) {
            JsonObj son =  new JsonObj();
            son.setConId(255);
            return  son;
        }
        else {


            Map obj = resutset.get(0);
            JsonObj jsonObj = new JsonObj();
            jsonObj.setCarId((Integer) obj.get("carid"));
            jsonObj.setConId((Integer) obj.get("conid"));
            jsonObj.setSpeed((Integer) obj.get("speed"));
            jsonObj.setPid((Integer) obj.get("pid"));
            jsonObj.setZhongZhi((Integer) obj.get("zhongzhi"));
            jsonObj.setPwmA((Integer) obj.get("pwmA"));
            jsonObj.setPwmB((Integer) obj.get("pwmB"));


            return jsonObj;
        }
    }
    @ResponseBody
    @RequestMapping(path = "/sendData",method = RequestMethod.POST)
    // 接收发送过来的数据
    public JsonObj ReceviceData(HttpServletRequest request){
        BufferedReader reader=null;
        System.out.println(request.getParameter("speed"));
        try {
            InputStream inputStream =request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line="";
        String res = "";
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            res += line;
        }
        Gson gson =new Gson();
        JsonObj info = gson.fromJson(res,JsonObj.class);
//        info.setCarid(1);
        System.out.println(res);
        String sql = "insert into car_record(carid,conid,speed,pid,zhongzhi,pwmA,pwmB) values('"+info.getCarId()+"','"
                   + info.getConId()+"','"+info.getSpeed()+"','"+info.getPid()+"','"+
                     info.getZhongZhi()+"','"+info.getPwmA()+"','"+info.getPwmB() +"')";
        System.out.println(sql);
        jdbcTemplate.execute(sql);


        return info;

    }
}
