package cn.deerowl.service.player;

import cn.deerowl.service.entity.BriefPlayerEntity;
import cn.deerowl.service.entity.PlayerEntity;

public interface PlayerService {

    /**
     * 获取球员的基本信息
     * @param playerId
     * @return
     */
    BriefPlayerEntity getBriefPlayerById(long playerId);

    /**
     * 获取球员的完整信息
     * @param playerId
     * @return
     */
    PlayerEntity getById(long playerId);
}
