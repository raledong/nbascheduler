package cn.deerowl.service.player;

import cn.deerowl.dao.PlayerMapper;
import cn.deerowl.model.Player;
import cn.deerowl.service.entity.BriefPlayerEntity;
import cn.deerowl.service.entity.PlayerEntity;
import cn.deerowl.service.entity.TeamEntity;
import cn.deerowl.util.LoggerUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {
    private static final Logger logger = LoggerUtil.getLogger(PlayerServiceImpl.class);

    @Autowired
    private PlayerMapper playerMapper;

    @Override
    public BriefPlayerEntity getBriefPlayerById(long playerId) {
        Player player = playerMapper.findById(playerId);
        if (player != null) {
            return buildBriefPlayerEntity(player);
        }
        LoggerUtil.info(logger, "该球员不存在,playerId={}", playerId);
        return null;
    }

    @Override
    public PlayerEntity getById(long playerId) {
        Player player = playerMapper.findById(playerId);
        if (player != null) {
            return buildPlayerEntity(player);
        }
        LoggerUtil.info(logger, "该球员不存在,playerId={}", playerId);
        return null;
    }

    private BriefPlayerEntity buildBriefPlayerEntity(Player player) {
        BriefPlayerEntity briefPlayerEntity = new BriefPlayerEntity();
        briefPlayerEntity.setId(player.getId());
        briefPlayerEntity.setCname(player.getCname());
        briefPlayerEntity.setEname(player.getEname());
        briefPlayerEntity.setHeadImg(player.getHeadImg());
        briefPlayerEntity.setHomepage(player.getHomepage());
        briefPlayerEntity.setNumber(player.getNumber());
        briefPlayerEntity.setPosition(player.getPosition());
        return briefPlayerEntity;
    }

    private PlayerEntity buildPlayerEntity(Player player) {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId(player.getId());
        playerEntity.setCname(player.getCname());
        playerEntity.setEname(player.getEname());
        playerEntity.setHeadImg(player.getHeadImg());
        playerEntity.setHomepage(player.getHomepage());
        playerEntity.setNumber(player.getNumber());
        playerEntity.setPosition(player.getPosition());
        playerEntity.setHeight(player.getHeight());
        playerEntity.setWeight(player.getWeight());
        playerEntity.setBirth(player.getBirth());
        playerEntity.setContract(player.getContract());
        playerEntity.setSalaryThisYear(player.getSalaryThisYear());
        playerEntity.setSchool(player.getSchool());
        playerEntity.setDraft(player.getDraft());
        playerEntity.setNationality(player.getNationality());
        if (player.getTeam()!=null) {
            playerEntity.setTeam(TeamEntity.buildTeamEntity(player.getTeam()));
        }
        return playerEntity;
    }

}
