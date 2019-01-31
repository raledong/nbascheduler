package cn.deerowl.common.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
public class DataAnalysisPOJO {

    private String homeTeam;

    private String visitTeam;

    /**
     * 开始时间
     */
    private String startAt;

    /**
     * 耗时
     */
    private String lasts;

    /**
     * 上座率
     */
    private int audienceCount;

    /**
     * 场馆
     */
    private String stadium;

    /**
     * 主队得分
     */
    private int homeTeamScore;

    /**
     * 客队得分
     */
    private int visitTeamScore;


    private int homeTeamFirstSectionScore;

    private int homeTeamSecondSectionScore;

    private int homeTeamThirdSectionScore;

    private int homeTeamFourthSectionScore;

    private int visitTeamFirstSectionScore;

    private int visitTeamSecondSectionScore;

    private int visitTeamThirdSectionScore;

    private int visitTeamFourthSectionScore;

    private List<PlayerPerformancePOJO> homeTeamPlayerPerformances;

    private List<PlayerPerformancePOJO> visitTeamPlayerPerformances;

    public void setHomeTeamScore(String homeTeamScore) {
        this.homeTeamScore = Integer.valueOf(homeTeamScore);
    }

    public void setVisitTeamScore(String visitTeamScore) {
        this.visitTeamScore = Integer.valueOf(visitTeamScore);
    }

    public void setAudienceCount(String audienceCount) {
        if (!StringUtils.isEmpty(audienceCount)) {
            this.audienceCount = Integer.valueOf(audienceCount);
        }
    }

    public void setHomeTeamFirstSectionScore(String score) {
        this.homeTeamFirstSectionScore = Integer.valueOf(score);
    }

    public void setHomeTeamSecondSectionScore(String score) {
        this.homeTeamSecondSectionScore = Integer.valueOf(score);
    }

    public void setHomeTeamThirdSectionScore(String score) {
        this.homeTeamThirdSectionScore = Integer.valueOf(score);
    }

    public void setHomeTeamFourthSectionScore(String score) {
        this.homeTeamFourthSectionScore = Integer.valueOf(score);
    }

    public void setVisitTeamFirstSectionScore(String score) {
        this.visitTeamFirstSectionScore = Integer.valueOf(score);
    }

    public void setVisitTeamSecondSectionScore(String score) {
        this.visitTeamSecondSectionScore = Integer.valueOf(score);
    }

    public void setVisitTeamThirdSectionScore(String score) {
        this.visitTeamThirdSectionScore = Integer.valueOf(score);
    }

    public void setVisitTeamFourthSectionScore(String score) {
        this.visitTeamFourthSectionScore = Integer.valueOf(score);
    }

}
