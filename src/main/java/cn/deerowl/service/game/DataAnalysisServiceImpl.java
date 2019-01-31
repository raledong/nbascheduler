package cn.deerowl.service.game;

import cn.deerowl.dao.DataAnalysisMapper;
import cn.deerowl.model.BriefDataAnalysis;
import cn.deerowl.model.DataAnalysis;
import cn.deerowl.model.PlayerPerformance;
import cn.deerowl.service.entity.*;
import cn.deerowl.service.player.PlayerService;
import cn.deerowl.service.team.TeamService;
import cn.deerowl.util.LoggerUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DataAnalysisServiceImpl implements DataAnalysisService {

    private static final Logger logger = LoggerUtil.getLogger(DataAnalysisServiceImpl.class);

    @Autowired
    private DataAnalysisMapper dataAnalysisMapper;

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @Override
    public BriefDataAnalysisEntity getById(long id) {
        BriefDataAnalysis dataAnalysis = dataAnalysisMapper.findById(id);
        if (dataAnalysis == null) {
            LoggerUtil.error(logger, "该比赛详情不存在，dataAnalysisId={}", id);
            return null;
        }
        return BriefDataAnalysisEntity.buildBriefDataAnalysisEntity(dataAnalysis);
    }

    @Override
    public BriefDataAnalysisEntity getByGameId(long gameId) {
        BriefDataAnalysis dataAnalysis = dataAnalysisMapper.findByGameId(gameId);
        if (dataAnalysis == null) {
            LoggerUtil.error(logger, "该场比赛详情不存在，gameId={}", gameId);
            return null;
        }
        return BriefDataAnalysisEntity.buildBriefDataAnalysisEntity(dataAnalysis);
    }

    @Override
    public DataAnalysisEntity getDetailByTeamIdAndDate(int homeTeamId, int visitTeamId, LocalDate date) {
        DataAnalysis dataAnalysis = dataAnalysisMapper.findWithPlayerPerformanceByTeamIdAndDate(homeTeamId, visitTeamId, date);
        if (dataAnalysis == null) {
            LoggerUtil.error(logger, "该场比赛详情不存在, homeTeamId={}, visitTeamId={}, data={}", homeTeamId, visitTeamId, date);
            return null;
        }

        Map<Integer, List<PlayerPerformance>> map =
                dataAnalysis.getPlayerPerformances()
                        .stream()
                        .collect(Collectors.groupingBy(PlayerPerformance::getTeamId));

        TeamEntity homeTeam = teamService.getTeamById(homeTeamId);
        TeamEntity visitTeam = teamService.getTeamById(visitTeamId);

        List<PlayerPerformanceEntity> homeTeamPlayerPerformances =
                map.get(homeTeamId)
                        .stream()
                        .map(playerPerformance -> buildPlayerPerformanceEntity(playerPerformance, homeTeam))
                        .collect(Collectors.toList());
        List<PlayerPerformanceEntity> visitTeamPlayerPerformances =
                map.get(visitTeamId)
                        .stream()
                        .map(playerPerformance -> buildPlayerPerformanceEntity(playerPerformance, visitTeam))
                        .collect(Collectors.toList());

        return DataAnalysisEntity.buildDataAnalysisEntity(
                dataAnalysis,
                homeTeamPlayerPerformances,
                visitTeamPlayerPerformances);
    }

    private PlayerPerformanceEntity buildPlayerPerformanceEntity(PlayerPerformance playerPerformance,
                                                                 TeamEntity teamEntity) {
        long playerId = playerPerformance.getPlayerId();
        BriefPlayerEntity playerEntity = playerService.getBriefPlayerById(playerId);

        return PlayerPerformanceEntity.buildPlayerPerformanceEntity(playerPerformance, playerEntity, teamEntity);
    }
}
