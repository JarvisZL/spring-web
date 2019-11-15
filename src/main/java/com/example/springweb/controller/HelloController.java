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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {
    @Autowired
    UserService userService;
    @Autowired
    ProjectsService projectsService;
    public final static Logger logger = LoggerFactory.getLogger(HelloController.class);
    private String oldid = "",curid = "";
    private String curpid = "";//curappid 对应的pid
    private int curappid = 0;//checkapp?id = curappid

    @RequestMapping("/404")
    public String notfound(){
        oldid = curid;
        curid = "";
        curpid = "";
        curappid = 0;
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
        curpid = "";
        curappid = 0;
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
            curpid = "";
            curappid = 0;
            return "redirect:main";
        }
    }

    @RequestMapping("/main")
    public String Tomain(Model model){
        //未登录直接访问main页面会跳转到登录界面
        if(curid.equals("")){
            return "redirect:index";
        }
        curpid = "";
        curappid = 0;
        HelloUser helloUser = userService.getOne(curid);
        assert(helloUser.getId() != null);
        model.addAttribute("name",helloUser.getName());
        int pronums = projectsService.numOfpidForuid(curid);
        System.out.println("num:"+pronums);
        model.addAttribute("pronum",pronums);
        return "main";
    }

    @GetMapping("/main/user")
    public ModelAndView mainTouser(){
        //未登录先登录
        if(curid.equals("")){
            return new ModelAndView("redirect:/index","user",null);
        }
        curpid = "";
        curappid = 0;
        HelloUser user = userService.getOne(curid);
        assert(user.getId()!=null);
        return new ModelAndView("user","user",user);
    }

    @GetMapping("/main/list")
    public ModelAndView mainTolist(){
        //未登录先登录
        if(curid.equals("")){
            return new ModelAndView("redirect:/index", "applist", null);
        }
        curpid = "";
        curappid = 0;
        List<Projects> applist = projectsService.getByuid(curid);

        return new ModelAndView("list","applist",applist);
    }

    @GetMapping("/main/help")
    public String mainTohelp(){
        if(curid.equals("")){
            return "redirect:/index";
        }
        curpid = "";
        curappid = 0;
        return "help";
    }

    @GetMapping("/main/checkapp")
    public ModelAndView Tocheckapp(@RequestParam("id") int id){
        //未登录先登录
        if(curid.equals("")){
            return new ModelAndView("redirect:/index", "app", null);
        }
        List<Projects> applist = projectsService.getByuid(curid);
        Projects app = applist.get(id-1);
        curpid = app.getPid();
        curappid = id;
        return new ModelAndView("checkapp","app",app);

    }

    @RequestMapping("/main/submit")
    public ModelAndView Tosubmit(String nameres,String catres, String saferes,String levelres){
        //未登录先登录
        if(curid.equals("")){
            return new ModelAndView("redirect:/index", "app", null);
        }

        //save
        if(nameres != null || catres != null || saferes != null || !levelres.equals("0")){
            Savemsg(nameres,catres,saferes,levelres);
        }


        return new ModelAndView("redirect:checkapp?id="+curappid,"submsg",null);
    }

    private void Savemsg(String nameres,String catres, String saferes,String levelres){
        boolean checked,allcheck = false,apncheck = false,apccheck = false,apscheck = false,aplcheck = false;
        int applevel = 0;

        checked = nameres != null || catres != null || saferes != null || !levelres.equals("0");

        if(nameres != null){
            apncheck = nameres.equals("ok");
        }
        if(catres != null){
            apccheck = catres.equals("ok");
        }
        if(saferes != null){
            apscheck = saferes.equals("ok");
        }
        aplcheck = !levelres.equals("0");
        applevel = Integer.parseInt(levelres);

        if(nameres != null && catres != null && saferes != null && !levelres.equals("0")){
            allcheck = nameres.equals("ok") && catres.equals("ok") && saferes.equals("ok");
        }
        String pid = curpid;

        projectsService.UpdateStatbypid(checked,allcheck,apncheck,apccheck,apscheck,applevel,aplcheck,pid);
    }

    @RequestMapping("/main/changepass")
    public String Tochangepass(Model model,String new_password){
        //未登录先登录
        if(curid.equals("")){
            return "redirect:/index";
        }

        HelloUser user = userService.getOne(curid);
        assert(user.getId()!= null);
        user.setPassword(new_password);
        Map<String,String> map = new HashMap<>();
        map.put("id",user.getId());
        map.put("name",user.getName());
        map.put("password",user.getPassword());
        map.put("contact",user.getContact());
        map.put("remark",user.getRemark());
        userService.UpdateByID(map);
        return "redirect:user";

    }
}
