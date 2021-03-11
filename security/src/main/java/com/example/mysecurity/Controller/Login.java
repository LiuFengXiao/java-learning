package com.example.mysecurity.Controller;

import com.example.mysecurity.POJO.Admin;
import com.example.mysecurity.component.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class Login {

    @Autowired
    @Qualifier("myuserdetail")
    UserDetailsServiceImpl userDetailsService;

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request,Admin admin) {
        System.out.println(request.getMethod());
        if(request.getMethod().equals("GET")){
            return "login.html";
        }
        else{
            System.out.println(request.getMethod());
            UserDetails userDetails = userDetailsService.loadUserByUsername(admin.getUsername());
            if (userDetails.getPassword() == admin.getPassword()) {
                return "index.html";
            }
            else{
                return "login.html";
            }
        }


    }


    @RequestMapping("/index")
    public  String index(Model model, Admin admin,String username,String password){
        System.out.println(admin.getUsername()+"99999999999");
        System.out.println(username);
        System.out.println(password);
        model.addAttribute("admin",admin.getUsername());
        return "index.html";
    }
}
