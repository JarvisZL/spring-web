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


    @Insert("INSERT INTO Appinfo\n" +
            "(pid,uid,checked,allcheck,applytime,companyname,appname,apncheck,appcatone,appcattwo,appcatthree,apccheck,appsafe,apscheck,applevel,aplcheck)\n" +
            "VALUES \n" +
            "('p001','u001',false,false,'2019-09-10','彼得创世有限公司','格林机床寿命预测APP',FALSE,'业务环节维度','生产制造工业app','质量管理类',FALSE,'A',FALSE,0,FALSE),\n" +
            "('p002','u001',false,false,'2019-05-11','AWSL有限公司','工业噪音分辨APP',FALSE,'业务环节维度','运维服务工业app','健康评价类',FALSE,'A',FALSE,0,FALSE),\n" +
            "('p003','u002',false,false,'2018-12-13','屑老板有限公司','住房装修模拟APP',FALSE,'业务环节维度','研发设计工业app','仿真类',FALSE,'A',FALSE,0,FALSE),\n" +
            "('p004','u001',false,false,'2019-01-30','彼得创世有限公司','金属材料特性展示APP',FALSE,'适用范围维度','基础共性工业APP',NULL,FALSE,'A',FALSE,0,FALSE),\n" +
            "('p005','u002',false,false,'2019-03-24','AWSL有限公司','仓库库存查询APP',FALSE,'知识类型维度','业务信息化类app',null,FALSE,'A',FALSE,0,FALSE);\n" +
            "\n" +
            "\n")
    void init();
}
