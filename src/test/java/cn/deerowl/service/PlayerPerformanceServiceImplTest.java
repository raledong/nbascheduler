package cn.deerowl.service;

import cn.deerowl.Application;
import cn.deerowl.config.ThreadPoolConfig;
import cn.deerowl.enumeration.TeamEnum;
import cn.deerowl.service.entity.PlayerPerformanceEntity;
import cn.deerowl.service.game.PlayerPerformanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class, ThreadPoolConfig.class})
public class PlayerPerformanceServiceImplTest {

    @Autowired
    private PlayerPerformanceService playerPerformanceService;

    @Test
    public void test() {
        List<PlayerPerformanceEntity> playerPerformanceEntities =
                playerPerformanceService.getPlayerPerformancesOfAGame(TeamEnum.RAPTORS, 1);
        for (PlayerPerformanceEntity playerPerformanceEntity : playerPerformanceEntities) {
            System.out.println(playerPerformanceEntity );
        }
    }
}
