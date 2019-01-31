package cn.deerowl.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode
public class BriefDataAnalysis {

    private long id;

    private long gameId;

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
}
