package com.example.springweb;

import com.example.springweb.dao.HelloUser;
import com.example.springweb.dao.Projects;
import com.example.springweb.service.ProjectsService;
import com.example.springweb.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectsServiceTest {
    @Autowired
    ProjectsService projectsService;

    /*before running this test, please ensure the appinfo table is empty*/
    @Test
    public void Testapps(){
        /* The first test begins*/

        /*init data*/
        projectsService.init();

        /* Counting number of apps which are examined by the expert whose id = uid
            including method projectsService.numOfpidForuid()*/

        String uid = "u001";
        assertEquals(3,projectsService.numOfpidForuid(uid));

        /* get applist for expert whose id = uid test including method projectsService.getByuid()*/
        List<Projects> applist = projectsService.getByuid(uid);
        assertNotNull(applist);
        Projects app1 = applist.get(0);
        assertEquals(uid,app1.getUid());
        assertEquals(app1.getPid(),"p001");
        assertEquals(app1.getCompanyname(),"彼得创世有限公司");
        assertEquals(app1.getAppname(),"格林机床寿命预测APP");
        java.sql.Date date = new Date(119,Calendar.SEPTEMBER,10);//from 1900
        assertEquals(app1.getApplytime().toString(),date.toString());
        assertEquals(app1.getAppsafe(),"A");


        /* Update app message test including method projectsService.UpdateStatbypid()*/
        boolean cheked = true, allcheck = false, apncheck = true, apccheck = false, apscheck = true, aplcheck = true;
        int applevel = 2;
        projectsService.UpdateStatbypid(cheked,allcheck,apncheck,apccheck,apscheck,applevel,aplcheck,"p002");

        /* get app whose appid = pid including method projectsService.getBypid()*/
        app1 = projectsService.getBypid("p002");
        assertNotNull(app1);
        assertEquals(app1.getCompanyname(),"AWSL有限公司");
        assertEquals(app1.getAppname(),"工业噪音分辨APP");
        date = new Date(119,Calendar.MAY,11);//from 1900
        assertEquals(app1.getApplytime().toString(),date.toString());
        assertEquals(app1.getAppsafe(),"A");
        assertEquals(app1.isAllcheck(),allcheck);
        assertEquals(app1.isApccheck(),apccheck);
        assertEquals(app1.getApplevel(),applevel);

        /*The first test ends*/

        /*------------------------------------------------------------------------------*/

        /*The second test begins*/

        /* Counting number of apps which are examined by the expert whose id = uid
            including method projectsService.numOfpidForuid()*/
        uid = "u002";
        assertEquals(2,projectsService.numOfpidForuid(uid));

        /* get applist for expert whose id = uid test including method projectsService.getByuid()*/
        applist = projectsService.getByuid(uid);
        assertNotNull(applist);

        app1 = applist.get(1);
        assertNotNull(app1);
        assertEquals(app1.getPid(),"p005");
        assertEquals(app1.getAppname(),"仓库库存查询APP");
        assertEquals(app1.getCompanyname(),"AWSL有限公司");
        date = new Date(119,Calendar.MARCH,24);
        assertEquals(app1.getApplytime().toString(),date.toString());

        /* Update app message test including method projectsService.UpdateStatbypid()*/
        cheked = true; allcheck = false; apncheck = true; apccheck = false; apscheck = true; aplcheck = true;
        applevel = 2;
        projectsService.UpdateStatbypid(cheked,allcheck,apncheck,apccheck,apscheck,applevel,aplcheck,"p004");

        /* get app whose appid = pid including method projectsService.getBypid()*/
        app1 = projectsService.getBypid("p004");
        assertNotNull(app1);
        assertEquals(app1.getCompanyname(),"彼得创世有限公司");
        assertEquals(app1.getAppname(),"金属材料特性展示APP");
        assertEquals(app1.getAppsafe(),"A");
        assertEquals(app1.isAllcheck(),allcheck);
        assertEquals(app1.isApccheck(),apccheck);
        assertEquals(app1.getApplevel(),applevel);

        /* The second test ends*/

    }
}
