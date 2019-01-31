package cn.deerowl.common.webparser.schedule;

import cn.deerowl.common.pojo.SchedulePOJO;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleWebParser {

    /**
     * 获取当天的所有比赛
     * @param date
     * @return
     */
    List<SchedulePOJO> getSchedules(LocalDate date);

}
