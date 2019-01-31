package cn.deerowl.service.web;

import cn.deerowl.common.pojo.SchedulePOJO;
import cn.deerowl.common.webparser.schedule.ScheduleWebParser;
import cn.deerowl.dao.GameMapper;
import cn.deerowl.dao.TeamMapper;
import cn.deerowl.enumeration.TeamEnum;
import cn.deerowl.model.Game;
import cn.deerowl.util.DateUtil;
import cn.deerowl.util.LoggerUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ScheduleWebServiceImpl implements ScheduleWebService{
    private static final Logger logger = LoggerUtil.getLogger(ScheduleWebService.class);

    @Autowired
    private ScheduleWebParser scheduleWebParser;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private GameMapper gameMapper;

    @Autowired
    @Qualifier("webParser")
    private TaskExecutor taskExecutor;

    @Override
    public void fetchSchedulesAndSave(LocalDate startAt, LocalDate endAt) {
        if (startAt.isAfter(endAt)) {
            LoggerUtil.error(logger, "开始时间应当位于结束时间之后, 开始时间: {}, 结束时间: {}" , startAt, endAt);
            throw new RuntimeException("开始时间应当位于结束时间之后");
        } else {
            while (startAt.isBefore(endAt) || startAt.isEqual(endAt)) {
                taskExecutor.execute(new ScheduleTask(startAt));
                startAt = startAt.plusDays(1);
            }
        }
    }

    @Override
    public void fetchSchedulesAndSave(LocalDate date) {
        List<SchedulePOJO> schedulePOJOS = scheduleWebParser.getSchedules(date);
        if (!CollectionUtils.isEmpty(schedulePOJOS)) {
            schedulePOJOS.stream()
                    .forEach((schedulePOJO -> {
                        Game game = buildGame(schedulePOJO);
                        //不存在该赛程时才添加
                        if (gameMapper.findByGameTeamsAndDate(
                                game.getHomeTeamId(),
                                game.getVisitTeamId(),
                                DateUtil.fromDateToString(game.getStartsAtDate(), DateUtil.BASE_DATE_FORMAT))
                                == null) {
                            gameMapper.add(game);
                        } else {
                            LoggerUtil.warn(logger, "重复获取该场比赛的赛程，homeTeam={}, visitTeam={}, date={}",
                                    schedulePOJO.getHomeTeam(), schedulePOJO.getVisitTeam(), schedulePOJO.getDate());
                        }
                    }));
        }
    }

    private Game buildGame(SchedulePOJO schedulePOJO) {
        Game game = new Game();
        String homeTeam = schedulePOJO.getHomeTeam();
        TeamEnum homeTeamEnum = TeamEnum.getByName(homeTeam);
        if (homeTeamEnum == null) {
            LoggerUtil.error(logger, "主队名称有误, homeTeam={}", homeTeam);
            throw new IllegalArgumentException("主队球队名称有误，主队球队名称为" + homeTeam);
        }

        String visitTeam = schedulePOJO.getVisitTeam();
        TeamEnum visitTeamEnum = TeamEnum.getByName(visitTeam);
        if (visitTeamEnum == null) {
            LoggerUtil.error(logger, "客队名称有误, visitTeam{}", visitTeam);
            throw new IllegalArgumentException("客队名称有误，客队名称为" + visitTeam);
        }

        int homeTeamId = teamMapper.searchByCname(homeTeamEnum.getFullCname()).getId();
        int visitTeamId = teamMapper.searchByCname(visitTeamEnum.getFullCname()).getId();
        game.setHomeTeamId(homeTeamId);
        game.setVisitTeamId(visitTeamId);

        LocalDateTime localDateTime = DateUtil.fromStringToDatetime(
                schedulePOJO.getDate(),
                DateUtil.BASE_DATE_TIME_FORMAT_WITHOUT_SECONDS);
        game.setStartsAtDate(LocalDate.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth()));
        game.setStartsAtTime(LocalTime.of(localDateTime.getHour(), localDateTime.getMinute()));

        game.setDataAnalysisLink(schedulePOJO.getDataAnalysisLink());
        game.setLiveLink(schedulePOJO.getLiveLink());
        game.setStadium(schedulePOJO.getStadium());
        return game;
    }

    private class ScheduleTask implements Runnable {
        private LocalDate date;

        ScheduleTask(LocalDate date){
            this.date = date;
        }

        @Override
        public void run() {
            fetchSchedulesAndSave(date);
        }
    }
}
