package com.example.springweb.mapper;

import com.example.springweb.dao.HelloUser;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user ")
    @Results({
            @Result(property = "id", column = "stringId"),
            @Result(property = "name", column = "user_name"),
            @Result(property = "password", column = "user_pass")
    })
    List<HelloUser> findAll();

    @Insert("insert into user(stringId,user_name,user_pass) values(#{id},#{name},#{password})")
    void insert(HelloUser helloUser);


    @Select("select * from user where stringId = #{id}")
    @Results({
            @Result(property = "id",column = "stringId"),
            @Result(property = "name",column = "user_name")
    })
    HelloUser getOne(String id);

    @Update("update user set user_name = #{name}, user_pass = #{password} where StringId = #{id}")
    void updateByID(HelloUser helloUser);//UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值

    @Delete("delete from user where StringId = #{id}")
    void deleteByID(String id);//DELETE FROM 表名称 WHERE 列名称 = 值

    @Select("select stringId from user where user_name = #{name} and user_pass = #{password}")
    @Results({
            @Result(property = "id", column = "stringId")
    })
    String Login(String name,String password);


    @Select("select stringId from user where user_name = #{name}")
    @Results({
            @Result(property = "id", column = "stringId")
    })
    String getOnebyname(String name);

}
