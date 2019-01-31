package cn.deerowl.mapper;

import cn.deerowl.Application;
import cn.deerowl.dao.DataAnalysisMapper;
import cn.deerowl.model.BriefDataAnalysis;
import cn.deerowl.model.DataAnalysis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class DataAnalysisMapperTest {

    @Autowired
    private DataAnalysisMapper dataAnalysisMapper;

    @Test
    public void testFindById() {
        dataAnalysisMapper.findById(4);
    }

    @Test
    public void testFindWithPlayerPerformanceById() {
        BriefDataAnalysis dataAnalysis = dataAnalysisMapper.findWithPlayerPerformanceById(4);
        System.out.println(dataAnalysis);
    }

    @Test
    public void testFindWithTeamIdAndDate() {
        DataAnalysis dataAnalysis = dataAnalysisMapper.findWithPlayerPerformanceByTeamIdAndDate(
                24,
                16,
                LocalDate.of(2018, 10, 21));
        System.out.println(dataAnalysis);
    }
}
