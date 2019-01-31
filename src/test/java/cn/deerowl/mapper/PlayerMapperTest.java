package cn.deerowl.mapper;


import cn.deerowl.Application;
import cn.deerowl.dao.PlayerMapper;
import cn.deerowl.model.Player;
import cn.deerowl.model.Team;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PlayerMapperTest {

    @Autowired
    private PlayerMapper playerMapper;

    @Test
    public void test() {
        Player player = new Player();
        Team team = new Team();
        team.setId(2);
        player.setTeam(team);
        player.setCname("test");
        player.setEname("test");
        System.out.println(playerMapper.add(player));
    }

    @Test
    public void testFindByNameAndTeamId() {
        System.out.println(playerMapper.findByNameAndTeam("德马尔-德罗赞", 4));
    }
}
