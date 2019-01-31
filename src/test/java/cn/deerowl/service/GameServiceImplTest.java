package cn.deerowl.service;

import cn.deerowl.Application;
import cn.deerowl.config.ThreadPoolConfig;
import cn.deerowl.service.web.ScheduleWebService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class, ThreadPoolConfig.class})
public class GameServiceImplTest {

    @Autowired
    private ScheduleWebService scheduleWebService;

    @Test
    public void testFetch() {
        LocalDate localDate = LocalDate.of(2018, 11,1);
        scheduleWebService.fetchSchedulesAndSave(localDate);
    }

    @Test
    public void testMultipleFetch() {
        LocalDate startAt = LocalDate.of(2018, 10, 17);
        LocalDate endAt = LocalDate.now();
        scheduleWebService.fetchSchedulesAndSave(startAt, endAt);

        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
