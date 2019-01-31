package cn.deerowl.controller;

import cn.deerowl.dao.TeamMapper;
import cn.deerowl.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/user")
public class HelloWorldController{

    @Autowired
    private TeamMapper teamMapper;

    @GetMapping("/")
    public List<Team> hello(){
        return teamMapper.getAllTeams();
    }
}

