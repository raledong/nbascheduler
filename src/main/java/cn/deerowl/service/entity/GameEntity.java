package cn.deerowl.service.entity;

import cn.deerowl.model.Game;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class GameEntity extends BriefGameEntity {

    private DataAnalysisEntity dataAnalysisEntity;

    public static GameEntity buildGameEntity(Game game, TeamEntity homeTeam, TeamEntity visitTeam, DataAnalysisEntity dataAnalysisEntity) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(game.getId());
        gameEntity.setHomeTeam(homeTeam);
        gameEntity.setVisitTeam(visitTeam);
        gameEntity.setDataAnalysisLink(game.getDataAnalysisLink());
        gameEntity.setLiveLink(game.getLiveLink());
        gameEntity.setStartsAtDate(game.getStartsAtDate());
        gameEntity.setStartsAtTime(game.getStartsAtTime());
        gameEntity.setDataAnalysisEntity(dataAnalysisEntity);
        return gameEntity;
    }
}
