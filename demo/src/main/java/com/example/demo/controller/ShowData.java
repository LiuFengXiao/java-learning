package com.example.demo.controller;

import com.example.demo.POJO.Info;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShowData {
    @RequestMapping("/show")
    //前端展示数据
    public String showData(){
        System.out.println("show");
        return  "show1.html";
    }
}
