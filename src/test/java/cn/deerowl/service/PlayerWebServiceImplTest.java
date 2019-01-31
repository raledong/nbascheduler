package cn.deerowl.service;

import cn.deerowl.Application;
import cn.deerowl.config.ThreadPoolConfig;
import cn.deerowl.enumeration.TeamEnum;
import cn.deerowl.service.web.PlayerWebService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class, ThreadPoolConfig.class})
public class PlayerWebServiceImplTest {

    @Autowired
    private PlayerWebService playerWebService;

    @Test
    public void testGetPlayerAndSave() {
        playerWebService.getPlayerAndSave("https://nba.hupu.com/players/mikeconley-1411.html");
    }

    @Test
    public void testGetPlayersOfATeamAndSave() {
        playerWebService.getPlayersOfATeamAndSave(TeamEnum.LAKERS);
    }

    @Test
    public void testGetAllPlayersAndSave() {
        playerWebService.getAllPlayersAndSave();
    }
}
