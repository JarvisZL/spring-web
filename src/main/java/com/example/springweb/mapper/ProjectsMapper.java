package com.example.springweb.mapper;

import com.example.springweb.dao.Projects;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProjectsMapper {

    @Select("select pid from Appinfo where uid = #{muid}")
    @Results({
            @Result(property = "pid", column = "pid")
    })
    List<String> getpidByuid(String muid);

    @Select("select * from Appinfo where pid = #{mpid}")
    @Results(value = {
            @Result(property = "pid",column = "pid"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "checked",column = "checked"),
            @Result(property = "allcheck", column = "allcheck"),
            @Result(property = "applytime",column = "applytime"),
            @Result(property = "companyname",column = "companyname"),
            @Result(property = "appname",column = "appname"),
            @Result(property = "apncheck",column = "apncheck"),
            @Result(property = "appcatone",column = "appcatone"),
            @Result(property = "appcattwo",column = "appcattwo"),
            @Result(property = "appcatthree",column = "appcatthree"),
            @Result(property = "apccheck",column = "apccheck"),
            @Result(property = "appsafe",column = "appsafe"),
            @Result(property = "apscheck",column = "apscheck"),
            @Result(property = "applevel",column = "applevel"),
            @Result(property = "aplcheck",column = "aplcheck")
    })
    Projects getBypid(String mpid);

    @Select("select * from Appinfo where uid = #{muid}")
    @Results(value = {
            @Result(property = "pid",column = "pid"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "checked",column = "checked"),
            @Result(property = "allcheck", column = "allcheck"),
            @Result(property = "applytime",column = "applytime"),
            @Result(property = "companyname",column = "companyname"),
            @Result(property = "appname",column = "appname"),
            @Result(property = "apncheck",column = "apncheck"),
            @Result(property = "appcatone",column = "appcatone"),
            @Result(property = "appcattwo",column = "appcattwo"),
            @Result(property = "appcatthree",column = "appcatthree"),
            @Result(property = "apccheck",column = "apccheck"),
            @Result(property = "appsafe",column = "appsafe"),
            @Result(property = "apscheck",column = "apscheck"),
            @Result(property = "applevel",column = "applevel"),
            @Result(property = "aplcheck",column = "aplcheck")
    })
    List<Projects> getByuid(String muid);

    @Update("update Appinfo set checked = #{checked}, allcheck = #{allcheck}, apncheck = #{apncheck}, apccheck = #{apccheck}, apscheck = #{apscheck}, applevel = #{applevel}, aplcheck = #{aplcheck} where pid = #{pid};")
    void UpdateStatbypid(Projects app);
}
