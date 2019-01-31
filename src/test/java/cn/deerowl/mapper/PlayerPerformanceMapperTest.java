package cn.deerowl.mapper;


import cn.deerowl.Application;
import cn.deerowl.dao.PlayerPerformanceMapper;
import cn.deerowl.model.PlayerPerformance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PlayerPerformanceMapperTest {

    @Autowired
    private PlayerPerformanceMapper playerPerformanceMapper;

    @Test
    public void test() {
        List<PlayerPerformance> playerPerformances = playerPerformanceMapper.findByTeamIdAndGameId(16, 1);
        System.out.println(playerPerformances);
    }
}
