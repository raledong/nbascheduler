package cn.deerowl.mapper;

import cn.deerowl.Application;
import cn.deerowl.dao.TeamMapper;
import cn.deerowl.model.Team;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TeamMapperTest {

    @Autowired
    private TeamMapper teamMapper;

    @Test
    public void test(){
        Team team = new Team();
        team.setCname("湖人");
        team.setEname("Lakers");
        teamMapper.add(team);
        System.out.println(team);
    }

    @Test
    public void testGetAll(){
        teamMapper.getAllTeams();
    }

    @Test
    public void testSearchByCname() {
        Team team = teamMapper.searchByCname("洛杉矶湖人");
        System.out.println(team);
    }
}
