package com.example.springweb;

import com.example.springweb.dao.HelloUser;
import com.example.springweb.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    /*before running this function, please empty the table*/
    @Test
    public void testUser(){
        /* The first test begins*/

        /*register test including method userService.getnextuid() and userService.InsertUser()*/
        List<HelloUser> userList = new ArrayList<>();
        //register user one
        HelloUser userone = new HelloUser();
        userone.setPassword("111111");
        userone.setName("Jarvis");
        userone.setContact("835575@qq.com");
        userone.setRemark("Jarvis Zhang");
        userone.setId(userService.getnextuid());
        userList.add(userone);

        Map<String,String> map = new HashMap<>();
        map.put("id",userone.getId());
        map.put("name",userone.getName());
        map.put("password",userone.getPassword());
        map.put("contact",userone.getContact());
        map.put("remark",userone.getRemark());
        userService.InsertUser(map);

        map.clear();
        //register user two
        HelloUser usertwo = new HelloUser();
        usertwo.setPassword("222222");
        usertwo.setName("Peter");
        usertwo.setContact("17171717@qq.com");
        usertwo.setRemark("Peter Yuan");
        usertwo.setId(userService.getnextuid());
        userList.add(usertwo);

        map.put("id",usertwo.getId());
        map.put("name",usertwo.getName());
        map.put("password",usertwo.getPassword());
        map.put("contact",usertwo.getContact());
        map.put("remark",usertwo.getRemark());
        userService.InsertUser(map);

        /* get userlist test including method userService.getUserList()*/
        List<HelloUser> users = userService.getUserList();
        assertEquals(users.size(),userList.size());
        for(int i = 0; i < users.size(); ++i){
            assertNotNull(users.get(i));
            assertNotNull(userList.get(i));
            assertEquals(userList.get(i).getId(),users.get(i).getId());
            assertEquals(userList.get(i).getName(),users.get(i).getName());
            assertEquals(userList.get(i).getPassword(),users.get(i).getPassword());
            assertEquals(userList.get(i).getContact(),users.get(i).getContact());
            assertEquals(userList.get(i).getRemark(),users.get(i).getRemark());
        }


        /*update user test including method userService.UpdateByID()*/
        map.clear();
        map.put("id",usertwo.getId());
        map.put("name","Maruko");
        map.put("password","333333");
        map.put("contact","awawawaw@gmail.com");
        map.put("remark","Maruko He");
        userService.UpdateByID(map);

        /* get user test including method userService.getOne and uerService.getOnebyname()*/
        HelloUser tmpuser = userService.getOne("u002");
        assertNotNull(tmpuser);
        userList.remove(1);
        userList.add(tmpuser);
        assertEquals(tmpuser.getName(),"Maruko");
        assertEquals(tmpuser.getPassword(),"333333");
        assertEquals(tmpuser.getContact(),"awawawaw@gmail.com");
        assertEquals(tmpuser.getRemark(),"Maruko He");

        String id = userService.getOnebyname("Jarvis");
        assertEquals(id,userone.getId());

        /* user log in test including method userService.Login()*/
        String name = "Jarvis";
        String pass = "111111";
        assertEquals(userone.getId(),userService.Login(name,pass));

        /*The first test ends*/

        /*------------------------------------------------------------------------------*/

        /*The second test begins*/

        map.clear();
        /*register test including method userService.getnextuid() and userService.InsertUser()*/
        HelloUser userthree = new HelloUser();
        userthree.setName("Ben");
        userthree.setPassword("123456");
        userthree.setContact("295396@qq.com");
        userthree.setRemark("Ben Zhang");
        userthree.setId(userService.getnextuid());
        userList.add(userthree);

        map.put("id",userthree.getId());
        map.put("name",userthree.getName());
        map.put("password",userthree.getPassword());
        map.put("contact",userthree.getContact());
        map.put("remark",userthree.getRemark());
        userService.InsertUser(map);

        /* get userlist test including method userService.getUserList()*/
        users.clear();
        users = userService.getUserList();
        assertEquals(users.size(),userList.size());
        for(int i = 0; i < users.size(); ++i){
            assertNotNull(users.get(i));
            assertNotNull(userList.get(i));
            assertEquals(userList.get(i).getId(),users.get(i).getId());
            assertEquals(userList.get(i).getName(),users.get(i).getName());
            assertEquals(userList.get(i).getPassword(),users.get(i).getPassword());
            assertEquals(userList.get(i).getContact(),users.get(i).getContact());
            assertEquals(userList.get(i).getRemark(),users.get(i).getRemark());
        }

        /*update user test including method userService.UpdateByID()*/
        map.clear();
        map.put("id",userthree.getId());
        map.put("name","James");
        map.put("password","444444");
        map.put("contact","qzqzqzqz@gmail.com");
        map.put("remark","James Zhang");
        userService.UpdateByID(map);

        /* get user test including method userService.getOne and uerService.getOnebyname()*/
        tmpuser = userService.getOne("u003");
        assertNotNull(tmpuser);
        userList.remove(2);
        userList.add(tmpuser);
        assertEquals(tmpuser.getName(),"James");
        assertEquals(tmpuser.getPassword(),"444444");
        assertEquals(tmpuser.getContact(),"qzqzqzqz@gmail.com");
        assertEquals(tmpuser.getRemark(),"James Zhang");

        id = userService.getOnebyname("James");
        assertEquals(id,"u003");

        /* user log in test including method userService.Login()*/
        name = "James";
        pass = "111111";
        assertNull(userService.Login(name,pass));

        /* The second test ends*/
    }



//    @Test
//    public void getUsers() {
//        List<HelloUser> users = userService.getUserList();
//        assertFalse("User not null", users == null);
//        assertNotNull(users);
//        assertNotEquals(users.size(), 0);
//        assertEquals(users.get(0).getName(), "lgy");
//    }
//
//    @Test
//    public void testInsert() throws Exception{
//        //helloService.InsertUser(new HelloUser("4","zyy","dsb"));
//       // assertEquals(helloService.getUserList().size(),3);
//        Map<String,String> params=new HashMap<>();
//        params.put("id","4");
//        params.put("name","niha");
//        params.put("password","yes");
//        helloService.InsertUser(params);
//        assertEquals(helloService.getUserList().size(),4);
//    }
//
//    @Test
//    public void testGetOne() throws Exception{
//        HelloUser helloUser = helloService.getOne("9");
//        assertEquals(helloUser.getName(),null);
//    }
//
//    @Test
//    public void testUpdate() throws Exception{
//       // helloService.UpdateByID(new HelloUser("3","ooo","bbbb"));
//       // assertEquals(helloService.getOne("3").getName(),"ooo");
//        Map<String,String> params=new HashMap<>();
//        params.put("id","4");
//        params.put("name","yyy");
//        helloService.UpdateByID(params);
//        assertEquals(helloService.getOne("4").getName(),"yyy");
//    }
//
//    @Test
//    public void testDelete() throws Exception{
//        helloService.DeleteByID("4");
//        assertEquals(helloService.getOne("4").getName(),null);
//    }
}
