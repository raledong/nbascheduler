package cn.deerowl.service.game;

import cn.deerowl.dao.PlayerPerformanceMapper;
import cn.deerowl.enumeration.TeamEnum;
import cn.deerowl.model.PlayerPerformance;
import cn.deerowl.service.entity.BriefPlayerEntity;
import cn.deerowl.service.entity.PlayerPerformanceEntity;
import cn.deerowl.service.entity.TeamEntity;
import cn.deerowl.service.player.PlayerService;
import cn.deerowl.service.team.TeamService;
import cn.deerowl.util.LoggerUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerPerformanceServiceImpl implements PlayerPerformanceService {

    private static final Logger logger = LoggerUtil.getLogger(PlayerPerformanceServiceImpl.class);

    @Autowired
    private PlayerPerformanceMapper playerPerformanceMapper;

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @Override
    public List<PlayerPerformanceEntity> getPlayerPerformancesOfAGame(final TeamEnum teamEnum, final long gameId) {
        final TeamEntity teamEntity = teamService.getByTeamEnum(teamEnum);
        int teamId = teamEntity.getId();
        List<PlayerPerformance> playerPerformances = playerPerformanceMapper.findByTeamIdAndGameId(teamId, gameId);
        if (!CollectionUtils.isEmpty(playerPerformances)) {
            return playerPerformances.stream()
                    .map(playerPerformance -> buildPlayerPerformanceEntity(playerPerformance, teamEntity))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public List<PlayerPerformanceEntity> getPlayerPerformancesOfAGame(final long gameId) {
        return null;
    }

    private PlayerPerformanceEntity buildPlayerPerformanceEntity(PlayerPerformance playerPerformance, TeamEntity teamEntity) {
        long playerId = playerPerformance.getPlayerId();
        BriefPlayerEntity briefPlayerEntity = playerService.getBriefPlayerById(playerId);
        return PlayerPerformanceEntity.buildPlayerPerformanceEntity(playerPerformance, briefPlayerEntity, teamEntity);
    }
}
