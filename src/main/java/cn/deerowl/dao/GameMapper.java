package cn.deerowl.dao;

import cn.deerowl.model.Game;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface GameMapper {

    long add(Game game);

    int update(Game game);

    Game findByGameId(long id);

    Game findByGameTeamsAndDate(@Param("homeTeamId") int homeTeamId,
                                @Param("visitTeamId") int visitTeamId,
                                @Param("startsAtDate") String startsAtDate
    );
}
