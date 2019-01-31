package cn.deerowl.dao;

import cn.deerowl.model.BriefDataAnalysis;
import cn.deerowl.model.DataAnalysis;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

public interface DataAnalysisMapper {

    int add(BriefDataAnalysis dataAnalysis);

    BriefDataAnalysis findById(long id);

    BriefDataAnalysis findByGameId(long gameId);

    DataAnalysis findWithPlayerPerformanceById(long id);

    DataAnalysis findWithPlayerPerformanceByGameId(long gameId);

    DataAnalysis findWithPlayerPerformanceByTeamIdAndDate(@Param("homeTeamId") int homeTeamId,
                                                          @Param("visitTeamId") int visitTeamId,
                                                          @Param("startsAt") LocalDate startsAt);
}
