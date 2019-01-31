package cn.deerowl.service.entity;

import cn.deerowl.model.Game;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@EqualsAndHashCode
public class BriefGameEntity {

    private long id;

    private TeamEntity homeTeam;

    private TeamEntity visitTeam;

    private String dataAnalysisLink;

    private String liveLink;

    private LocalDate startsAtDate;

    private LocalTime startsAtTime;

    public static BriefGameEntity buildBriefGameEntity(Game game, TeamEntity homeTeam, TeamEntity visitTeam) {
        BriefGameEntity gameEntity = new BriefGameEntity();
        gameEntity.setId(game.getId());
        gameEntity.setHomeTeam(homeTeam);
        gameEntity.setVisitTeam(visitTeam);
        gameEntity.setDataAnalysisLink(game.getDataAnalysisLink());
        gameEntity.setLiveLink(game.getLiveLink());
        gameEntity.setStartsAtDate(game.getStartsAtDate());
        gameEntity.setStartsAtTime(game.getStartsAtTime());
        return gameEntity;
    }
}
