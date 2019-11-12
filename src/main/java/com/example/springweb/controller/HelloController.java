package com.example.springweb.controller;

import com.example.springweb.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {
    @Autowired
    HelloService helloService;
    public final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hello")
    public String hello(){
        logger.info("hello logging" + helloService.getUserList());
        return "hello";
    }

    @RequestMapping("/index/404")
    public String notfound(){
        return String.valueOf(404);
    }

    @RequestMapping("/index")
    public String LogIn(){
        return "redirect:/";
    }

    @RequestMapping("/Main")
    public String LogInJudge(Model model,String username,String password){
        if (username == null || password == null){
            return "redirect:/";
        }
        if(username.equals("")){
            model.addAttribute("tips","Username can not be null!");
            return "redirect:/";
        }
        if(password.equals("")){
            model.addAttribute("tips","password can not be null");
            return "redirect:/";
        }
        System.out.println(username);
        System.out.println(password);
        String userid = helloService.Login(username,password);
        if(userid == null){
            model.addAttribute("tips","Username or Password is wrong.");
            return "redirect:/";
        }
        else{
            return "/Main";
        }
    }

}
