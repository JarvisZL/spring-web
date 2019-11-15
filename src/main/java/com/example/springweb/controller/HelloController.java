package com.example.springweb.controller;

import com.example.springweb.dao.HelloUser;
import com.example.springweb.dao.Projects;
import com.example.springweb.service.ProjectsService;
import com.example.springweb.service.UserService;
import org.apache.ibatis.annotations.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.UnknownServiceException;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {
    @Autowired
    UserService userService;
    @Autowired
    ProjectsService projectsService;
    public final static Logger logger = LoggerFactory.getLogger(HelloController.class);
    private String oldid = "",curid = "";

    @RequestMapping("/404")
    public String notfound(){
        return String.valueOf(404);
    }

    //初始页面跳转到/index
    @GetMapping("")
    public String root(){
         return "redirect:/index";
    }

    @RequestMapping("/index")
    public String ToLogIn(){
        oldid = curid;
        curid = "";
        return "index";
    }

    @RequestMapping("/login")
    public String LogInJudge(Model model,String username,String password){
        if (username == null || password == null){
            return "redirect:/index";
        }
        String userid = null;
        userid = userService.getOnebyname(username);
        if(userid == null){
            model.addAttribute("tips","The user doesn't exist.");
            return "/index";
        }
        userid = userService.Login(username,password);
        if(userid == null){
            model.addAttribute("tips","Password is incorrect.");
            return "/index";
        }
        else{
            oldid = curid;
            curid = userid;
            return "redirect:main";
        }
    }

    @RequestMapping("/main")
    public String Tomain(Model model){
        //未登录直接访问main页面会跳转到登录界面
        if(curid.equals("")){
            return "redirect:index";
        }
        HelloUser helloUser = userService.getOne(curid);
        assert(helloUser.getId() != null);
        model.addAttribute("name",helloUser.getName());
        int pronums = projectsService.numOfpidForuid(curid);
        System.out.println("num:"+pronums);
        model.addAttribute("pronum",pronums);
        return "main";
    }

    @GetMapping("/main/user")
    public String mainTouser(){
        //未登录先登录
        if(curid.equals("")){
            return "redirect:/index";
        }
        return "user";
    }

    private List<Projects> applist;

    @GetMapping("/main/list")
    public ModelAndView mainTolist(){
        //未登录先登录
        if(curid.equals("")){
            return new ModelAndView("redirect:/index", "applist", null);
        }
        applist = projectsService.getByuid(curid);

        return new ModelAndView("list","applist",applist);
    }

    @GetMapping("/main/checkapp")
    public ModelAndView Tocheckapp(@RequestParam("id") int id){
        //未登录先登录
        if(curid.equals("")){
            return new ModelAndView("redirect:/index", "app", null);
        }

        Projects app = applist.get(id-1);

        return new ModelAndView("checkapp","app",app);
    }


}
