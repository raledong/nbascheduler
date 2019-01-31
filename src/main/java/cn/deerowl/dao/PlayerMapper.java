package cn.deerowl.dao;

import cn.deerowl.model.Player;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlayerMapper {

    Long add(Player player);

    Player findByNameAndTeam(@Param("name") String name,
                             @Param("teamId") int teamId);

    Player findById(long id);

}
