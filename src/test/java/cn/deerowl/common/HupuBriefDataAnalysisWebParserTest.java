package cn.deerowl.common;

import cn.deerowl.Application;
import cn.deerowl.common.webparser.game.DataAnalysisWebParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class HupuBriefDataAnalysisWebParserTest {

    @Autowired
    private DataAnalysisWebParser dataAnalysisWebParser;

    @Test
    public void test(){
        dataAnalysisWebParser.getDataAnalysis("https://nba.hupu.com/games/boxscore/156170");
    }
}
