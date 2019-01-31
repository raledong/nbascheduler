package cn.deerowl.common;

import cn.deerowl.Application;
import cn.deerowl.common.webparser.schedule.ScheduleWebParser;
import cn.deerowl.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class HupuScheduleWebParserTest {

    @Autowired
    private ScheduleWebParser scheduleWebParser;

    @Test
    public void test() {
        LocalDate date = DateUtil.fromStringToDate("2018-11-13", DateUtil.BASE_DATE_FORMAT);
        scheduleWebParser.getSchedules(date);
    }

}
