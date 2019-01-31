package cn.deerowl.dao;

import cn.deerowl.model.PlayerPerformance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlayerPerformanceMapper {

    long add(PlayerPerformance playerPerformance);

    int batchAdd(List<PlayerPerformance> playerPerformances);

    List<PlayerPerformance> findByTeamIdAndDataAnalysisId(@Param("teamId") int teamId,
                                                          @Param("dataAnalysisId") long dataAnalysisId);

    List<PlayerPerformance> findByTeamIdAndGameId(@Param("teamId") int teamId,
                                                  @Param("gameId") long gameId);
}
