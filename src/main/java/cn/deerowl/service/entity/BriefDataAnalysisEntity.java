package cn.deerowl.service.entity;

import cn.deerowl.model.BriefDataAnalysis;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class BriefDataAnalysisEntity {

    private long id;

    private int homeTeamScore;

    private int visitTeamScore;

    private int homeTeamFirstQuarterScore;

    private int homeTeamSecondQuarterScore;

    private int homeTeamThirdQuarterScore;

    private int homeTeamFourthQuarterScore;

    private int visitTeamFirstQuarterScore;

    private int visitTeamSecondQuarterScore;

    private int visitTeamThirdQuarterScore;

    private int visitTeamFourthQuarterScore;

    public static BriefDataAnalysisEntity buildBriefDataAnalysisEntity(BriefDataAnalysis briefDataAnalysis){
        BriefDataAnalysisEntity briefDataAnalysisEntity = new BriefDataAnalysisEntity();
        briefDataAnalysisEntity.setId(briefDataAnalysis.getId());
        briefDataAnalysisEntity.setHomeTeamScore(briefDataAnalysis.getHomeTeamScore());
        briefDataAnalysisEntity.setVisitTeamScore(briefDataAnalysis.getVisitTeamScore());
        briefDataAnalysisEntity.setHomeTeamFirstQuarterScore(briefDataAnalysis.getHomeTeamFirstQuarterScore());
        briefDataAnalysisEntity.setHomeTeamSecondQuarterScore(briefDataAnalysis.getHomeTeamSecondQuarterScore());
        briefDataAnalysisEntity.setHomeTeamThirdQuarterScore(briefDataAnalysis.getHomeTeamThirdQuarterScore());
        briefDataAnalysisEntity.setHomeTeamFourthQuarterScore(briefDataAnalysis.getHomeTeamFourthQuarterScore());
        briefDataAnalysisEntity.setVisitTeamFirstQuarterScore(briefDataAnalysis.getVisitTeamFirstQuarterScore());
        briefDataAnalysisEntity.setVisitTeamSecondQuarterScore(briefDataAnalysis.getVisitTeamSecondQuarterScore());
        briefDataAnalysisEntity.setVisitTeamThirdQuarterScore(briefDataAnalysis.getVisitTeamThirdQuarterScore());
        briefDataAnalysisEntity.setVisitTeamFourthQuarterScore(briefDataAnalysis.getVisitTeamFourthQuarterScore());
        return briefDataAnalysisEntity;
    }
}
