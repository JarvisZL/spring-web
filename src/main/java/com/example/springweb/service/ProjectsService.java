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

}
