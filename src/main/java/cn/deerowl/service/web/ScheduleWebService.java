package cn.deerowl.service.web;

import java.time.LocalDate;

public interface ScheduleWebService {

    /**
     * 获取一段时间之内的赛程并保存
     * @param startAt
     * @param endAt
     */
    void fetchSchedulesAndSave(LocalDate startAt, LocalDate endAt);

    /**
     * 获取单日内的赛程并保存
     * @param date
     */
    void fetchSchedulesAndSave(LocalDate date);
}
