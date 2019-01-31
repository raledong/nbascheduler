package cn.deerowl.service.game;

import cn.deerowl.service.entity.BriefDataAnalysisEntity;
import cn.deerowl.service.entity.DataAnalysisEntity;

import java.time.LocalDate;

public interface DataAnalysisService {

    BriefDataAnalysisEntity getById(long id);

    BriefDataAnalysisEntity getByGameId(long gameId);

    DataAnalysisEntity getDetailByTeamIdAndDate(int homeTeamId, int visitTeamId, LocalDate date);

}
