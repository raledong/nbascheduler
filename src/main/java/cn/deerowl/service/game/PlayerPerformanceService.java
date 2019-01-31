package cn.deerowl.service.game;

import cn.deerowl.enumeration.TeamEnum;
import cn.deerowl.service.entity.PlayerPerformanceEntity;

import java.util.List;

public interface PlayerPerformanceService {

    List<PlayerPerformanceEntity> getPlayerPerformancesOfAGame(TeamEnum teamEnum, long gameId);

    List<PlayerPerformanceEntity> getPlayerPerformancesOfAGame(long gameId);

}
