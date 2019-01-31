package cn.deerowl.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@EqualsAndHashCode
public class BriefGame {

    private long id;

    private int homeTeamId;

    private int visitTeamId;

    private String stadium;

    private int audienceCount;

    private String lasts;

    private String dataAnalysisLink;

    private String liveLink;

    private LocalDate startsAtDate;

    private LocalTime startsAtTime;
}
