package cn.deerowl.common;


import cn.deerowl.Application;
import cn.deerowl.common.webparser.player.PlayerWebParser;
import cn.deerowl.enumeration.TeamEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class HupuPlayerWebParserTest {

    @Autowired
    private PlayerWebParser playerWebParser;

    @Test
    public void test1(){
        playerWebParser.getPlayersOfATeam(TeamEnum.LAKERS);
    }


    @Test
    public void testGetAllPlayers() {
        playerWebParser.getPlayersOfCurrentSeason();
    }

    @Test
    public void testGetPlayer() {
        playerWebParser.getPlayer("https://nba.hupu.com/players/mikeconley-1411.html");
    }
}
