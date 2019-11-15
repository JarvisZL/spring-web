package com.example.springweb.service;

import com.example.springweb.dao.HelloUser;
import com.example.springweb.mapper.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public List<HelloUser> getUserList() {
        List<HelloUser> list = userMapper.findAll();
        return list;
    }

    /*public void InsertUser(HelloUser helloUser){
        helloMapper.insert(helloUser);
        System.out.println("Afterinsert:"+helloMapper.findAll());
    }*/
    public void InsertUser(Map<String, String> params){
        ObjectMapper objectMapper = new ObjectMapper();
        HelloUser helloUser = objectMapper.convertValue(params, HelloUser.class);
        userMapper.insert(helloUser);
    }


    public HelloUser getOne(String id){
        //HelloUser result = new HelloUser();
        HelloUser result = userMapper.getOne(id);
        System.out.println("getOne:"+result);
        if (result==null)
        {
            result=new HelloUser();//索引为空的时候，返回null，需要这时候对其getId,getName就会出错。
        }
        System.out.println(result.toString());
        return result;
    }

    /*public void UpdateByID(HelloUser helloUser){
        helloMapper.updateByID(helloUser);
        System.out.println("AfterUpdate:"+ helloMapper.getOne(helloUser.getId()));
    }*/
    public void UpdateByID(Map<String, String> params){
        String id = params.get("id");
        //Long recordId = Long.parseLong(params.get("recordId"));
        //ObjectMapper objectMapper = new ObjectMapper();
        //HelloUser helloUser = objectMapper.convertValue(params, HelloUser.class);
        //helloMapper.updateByID(helloUser);
        HelloUser temp = userMapper.getOne(id);
        if(params.get("name")!=null)
            temp.setName(params.get("name"));
        if(params.get("password")!=null)
            temp.setPassword((params.get("password")));
        userMapper.updateByID(temp);
    }

    public void DeleteByID(String id){
        userMapper.deleteByID(id);
        System.out.println("AfterDelete:"+userMapper.getOne(id));
    }


    public String Login(String name,String pass){
        return userMapper.Login(name,pass);
    }

    public String getOnebyname(String name){
        return userMapper.getOnebyname(name);
    }
}
