package cn.deerowl.service.entity;

import cn.deerowl.model.BriefDataAnalysis;
import cn.deerowl.model.DataAnalysis;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class DataAnalysisEntity extends BriefDataAnalysis{


    private List<PlayerPerformanceEntity> homeTeamPlayerPerformance;

    private List<PlayerPerformanceEntity> visitTeamPlayerPerformance;

    public static DataAnalysisEntity buildDataAnalysisEntity(DataAnalysis dataAnalysis,
                                                             List<PlayerPerformanceEntity> homeTeamPlayerPerformance,
                                                             List<PlayerPerformanceEntity> visitTeamPlayerPerformance) {
        DataAnalysisEntity dataAnalysisEntity = new DataAnalysisEntity();
        dataAnalysisEntity.setId(dataAnalysis.getId());
        dataAnalysisEntity.setHomeTeamScore(dataAnalysis.getHomeTeamScore());
        dataAnalysisEntity.setVisitTeamScore(dataAnalysis.getVisitTeamScore());
        dataAnalysisEntity.setHomeTeamFirstQuarterScore(dataAnalysis.getHomeTeamFirstQuarterScore());
        dataAnalysisEntity.setHomeTeamSecondQuarterScore(dataAnalysis.getHomeTeamSecondQuarterScore());
        dataAnalysisEntity.setHomeTeamThirdQuarterScore(dataAnalysis.getHomeTeamThirdQuarterScore());
        dataAnalysisEntity.setHomeTeamFourthQuarterScore(dataAnalysis.getHomeTeamFourthQuarterScore());
        dataAnalysisEntity.setVisitTeamFirstQuarterScore(dataAnalysis.getVisitTeamFirstQuarterScore());
        dataAnalysisEntity.setVisitTeamSecondQuarterScore(dataAnalysis.getVisitTeamSecondQuarterScore());
        dataAnalysisEntity.setVisitTeamThirdQuarterScore(dataAnalysis.getVisitTeamThirdQuarterScore());
        dataAnalysisEntity.setVisitTeamFourthQuarterScore(dataAnalysis.getVisitTeamFourthQuarterScore());
        dataAnalysisEntity.setHomeTeamPlayerPerformance(homeTeamPlayerPerformance);
        dataAnalysisEntity.setVisitTeamPlayerPerformance(visitTeamPlayerPerformance);
        return dataAnalysisEntity;
    }

}
