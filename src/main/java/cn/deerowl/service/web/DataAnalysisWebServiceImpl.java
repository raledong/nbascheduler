package cn.deerowl.service.web;

import cn.deerowl.common.pojo.DataAnalysisPOJO;
import cn.deerowl.common.pojo.PlayerPerformancePOJO;
import cn.deerowl.common.webparser.game.DataAnalysisWebParser;
import cn.deerowl.dao.*;
import cn.deerowl.enumeration.TeamEnum;
import cn.deerowl.model.*;
import cn.deerowl.util.LoggerUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataAnalysisWebServiceImpl implements DataAnalysisWebService{

    private static final Logger logger = LoggerUtil.getLogger(DataAnalysisWebServiceImpl.class);

    @Autowired
    private DataAnalysisWebParser dataAnalysisWebParser;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private PlayerWebService playerWebService;

    @Autowired
    private GameMapper gameMapper;

    @Autowired
    private DataAnalysisMapper dataAnalysisMapper;

    @Autowired
    private PlayerMapper playerMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private PlayerPerformanceMapper playerPerformanceMapper;

    @Override
    public void fetchDataAnalysisAndSave(long scheduleId) {
        Game game = gameMapper.findByGameId(scheduleId);
        if (game == null) {
            LoggerUtil.error(logger, "该场比赛并不存在，gameId={}", scheduleId);
            return;
        }

        String dataAnalysisLink = game.getDataAnalysisLink();
        DataAnalysisPOJO dataAnalysisPOJO = dataAnalysisWebParser.getDataAnalysis(dataAnalysisLink);
        if (dataAnalysisPOJO != null) {
            String homeTeamName = dataAnalysisPOJO.getHomeTeam();
            TeamEnum homeTeamEnum = TeamEnum.getByName(homeTeamName);
            if (homeTeamEnum == null) {
                LoggerUtil.error(logger, "该场比赛球队无法识别, teamName={}", homeTeamName);
                return;
            }
            Team homeTeam = teamMapper.searchByCname(homeTeamEnum.getFullCname());

            String visitTeamName = dataAnalysisPOJO.getVisitTeam();
            TeamEnum visitTeamEnum = TeamEnum.getByName(visitTeamName);
            if (visitTeamEnum == null) {
                LoggerUtil.error(logger, "该场比赛球队无法识别, teamName={}", visitTeamName);
                return;
            }
            Team visitTeam = teamMapper.searchByCname(visitTeamEnum.getFullCname());


            BriefDataAnalysis dataAnalysis = buildBasicDataAnalysis(game.getId(), dataAnalysisPOJO);
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                    if (dataAnalysisMapper.add(dataAnalysis) == 1) {
                        long dataAnalysisId= dataAnalysis.getId();
                        List<PlayerPerformance> homeTeamPlayerPerformances = buildPlayerPerformances(
                                dataAnalysisPOJO.getHomeTeamPlayerPerformances(),
                                homeTeam.getId(),
                                dataAnalysisId);
                        playerPerformanceMapper.batchAdd(homeTeamPlayerPerformances);
                        List<PlayerPerformance> visitTeamPlayerPerformances = buildPlayerPerformances(
                                dataAnalysisPOJO.getVisitTeamPlayerPerformances(),
                                visitTeam.getId(),
                                dataAnalysisId);
                        playerPerformanceMapper.batchAdd(visitTeamPlayerPerformances);
                    } else {
                        //@Fixme 更新当前的统计数据
                    }
                }
            });

        }

    }

    private BriefDataAnalysis buildBasicDataAnalysis(long gameId, DataAnalysisPOJO dataAnalysisPOJO) {
        BriefDataAnalysis dataAnalysis = new BriefDataAnalysis();
        dataAnalysis.setGameId(gameId);
        dataAnalysis.setHomeTeamScore(dataAnalysisPOJO.getHomeTeamScore());
        dataAnalysis.setVisitTeamScore(dataAnalysisPOJO.getVisitTeamScore());
        dataAnalysis.setHomeTeamFirstQuarterScore(dataAnalysisPOJO.getHomeTeamFirstSectionScore());
        dataAnalysis.setHomeTeamSecondQuarterScore(dataAnalysisPOJO.getHomeTeamSecondSectionScore());
        dataAnalysis.setHomeTeamThirdQuarterScore(dataAnalysisPOJO.getHomeTeamThirdSectionScore());
        dataAnalysis.setHomeTeamFourthQuarterScore(dataAnalysisPOJO.getHomeTeamFourthSectionScore());
        dataAnalysis.setVisitTeamFirstQuarterScore(dataAnalysisPOJO.getVisitTeamFirstSectionScore());
        dataAnalysis.setVisitTeamSecondQuarterScore(dataAnalysisPOJO.getVisitTeamSecondSectionScore());
        dataAnalysis.setVisitTeamThirdQuarterScore(dataAnalysisPOJO.getVisitTeamThirdSectionScore());
        dataAnalysis.setVisitTeamFourthQuarterScore(dataAnalysisPOJO.getVisitTeamFourthSectionScore());
        return dataAnalysis;
    }

    private List<PlayerPerformance> buildPlayerPerformances(List<PlayerPerformancePOJO> playerPerformancePOJOS,
                                                            int teamId,
                                                            long dataAnalysisId) {
        if (CollectionUtils.isEmpty(playerPerformancePOJOS)) {
            return Collections.emptyList();
        }
        return playerPerformancePOJOS.stream()
                .map((playerPerformancePOJO )-> {
                    String playerName = playerPerformancePOJO.getName();
                    Player player = playerMapper.findByNameAndTeam(playerName, teamId);
                    if (player == null) {
                        LoggerUtil.info(logger, "该球员数据暂不存在，需重新爬取，playerName={}", playerName);
                        playerWebService.getPlayerAndSave(playerPerformancePOJO.getHomePageUrl());
                        player = playerMapper.findByNameAndTeam(playerName, teamId);
                    }
                    long playerId = player.getId();
                    return buildPlayerPerformance(playerPerformancePOJO, playerId, teamId, dataAnalysisId);
                })
                .collect(Collectors.toList());
    }

    private PlayerPerformance buildPlayerPerformance(PlayerPerformancePOJO playerPerformancePOJO,
                                                     long playerId,
                                                     int teamId,
                                                     long dataAnalysisId) {
        PlayerPerformance playerPerformance = new PlayerPerformance();
        playerPerformance.setPlayerId(playerId);
        playerPerformance.setTeamId(teamId);
        playerPerformance.setDataAnalysisId(dataAnalysisId);
        playerPerformance.setPosition(playerPerformancePOJO.getPosition());
        playerPerformance.setTime(playerPerformancePOJO.getTime());
        playerPerformance.setSuccessShot(playerPerformancePOJO.getSuccessShot());
        playerPerformance.setTotalShot(playerPerformancePOJO.getTotalShot());
        playerPerformance.setThreePointSuccessShot(playerPerformancePOJO.getThreePointSuccessShot());
        playerPerformance.setThreePointTotalShot(playerPerformancePOJO.getThreePointTotalShot());
        playerPerformance.setFreeThrowSuccessShot(playerPerformancePOJO.getFreeThrowSuccessShot());
        playerPerformance.setFreeThrowTotalShot(playerPerformancePOJO.getFreeThrowTotalShot());
        playerPerformance.setFrontCourtRebound(playerPerformancePOJO.getFrontCourtRebound());
        playerPerformance.setBackCourtRebound(playerPerformancePOJO.getBackCourtRebound());
        playerPerformance.setAssist(playerPerformancePOJO.getAssist());
        playerPerformance.setFoul(playerPerformancePOJO.getFoul());
        playerPerformance.setSteal(playerPerformancePOJO.getFoul());
        playerPerformance.setTurnover(playerPerformancePOJO.getFault());
        playerPerformance.setBlock(playerPerformancePOJO.getBlock());
        playerPerformance.setScore(playerPerformancePOJO.getScore());
        playerPerformance.setPositiveAndNegativeValue(playerPerformancePOJO.getPositiveAndNegativeValue());
        return playerPerformance;
    }
}
