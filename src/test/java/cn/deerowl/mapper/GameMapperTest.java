package cn.deerowl.mapper;

import cn.deerowl.Application;
import cn.deerowl.dao.GameMapper;
import cn.deerowl.model.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class GameMapperTest {

    @Autowired
    private GameMapper gameMapper;

    @Test
    public void testAdd() {
        Game game = new Game();
        game.setHomeTeamId(1);
        game.setVisitTeamId(2);
        game.setDataAnalysisLink("12313");
        game.setLiveLink("243123");
        gameMapper.add(game);
    }

    @Test
    public void testFindByTeamsAndTime() {
        Game game = gameMapper.findByGameTeamsAndDate(19, 28, "2018-10-31");
        System.out.println(game);

    }
}
