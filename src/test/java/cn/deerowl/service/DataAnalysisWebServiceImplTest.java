package cn.deerowl.service;

import cn.deerowl.Application;
import cn.deerowl.config.ThreadPoolConfig;
import cn.deerowl.service.web.DataAnalysisWebService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class DataAnalysisWebServiceImplTest {

    @Autowired
    private DataAnalysisWebService dataAnalysisWebService;

    @Test
    public void test() {
        dataAnalysisWebService.fetchDataAnalysisAndSave(1);
    }


}
