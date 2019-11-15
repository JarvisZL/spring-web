package com.example.springweb.service;


import com.example.springweb.dao.Projects;
import com.example.springweb.mapper.ProjectsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectsService {
    @Resource
    private ProjectsMapper projectsMapper;

    public int numOfpidForuid(String uid){
        List<String> list = projectsMapper.getpidByuid(uid);
        return list.size();
    }

    public List<Projects> getByuid(String uid){
        return projectsMapper.getByuid(uid);
    }

    public Projects getBypid(String pid){
        return projectsMapper.getBypid(pid);
    }

    public void UpdateStatbypid(boolean mchecked,boolean mallcheck, boolean mapncheck, boolean mapccheck,
                                boolean mapscheck,int mapplevel, boolean maplcheck,String mpid){
        Projects app = projectsMapper.getBypid(mpid);
        app.setChecked(mchecked);
        app.setAllcheck(mallcheck);
        app.setApncheck(mapncheck);
        app.setApccheck(mapccheck);
        app.setApscheck(mapscheck);
        app.setAplcheck(maplcheck);
        app.setApplevel(mapplevel);

        projectsMapper.UpdateStatbypid(app);
    }




}
