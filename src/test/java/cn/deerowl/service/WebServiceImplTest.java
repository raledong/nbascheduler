package cn.deerowl.service;

import cn.deerowl.Application;
import cn.deerowl.service.web.TeamWebService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class WebServiceImplTest {

    @Autowired
    private TeamWebService webService;

    @Test
    public void test() {
        webService.fetchAllNbaTeamsAndSave();
    }
}
