package cn.deerowl.service.entity;

import cn.deerowl.enumeration.DivisionEnum;
import cn.deerowl.model.Team;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class TeamEntity {
    private int id;

    /**
     * 中文名
     */
    private String cname;

    /**
     * 英文名
     */
    private String ename;

    /**
     * 前名
     */
    private String prename;

    /**
     * 主教练
     */
    private String headcoach;

    /**
     * 概述
     */
    private String description;


    /**
     * 分区
     */
    private DivisionEnum division;

    /**
     * 球馆
     */
    private String stadium;

    /**
     * 球队主页
     */
    private String homePage;

    public static TeamEntity buildTeamEntity(Team team) {
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(team.getId());
        teamEntity.setCname(team.getCname());
        teamEntity.setEname(team.getEname());
        teamEntity.setDivision(team.getDivisionToDivisionEnum());
        teamEntity.setDescription(team.getDescription());
        teamEntity.setStadium(team.getStadium());
        teamEntity.setPrename(team.getPrename());
        teamEntity.setHomePage(team.getHomePage());
        teamEntity.setDescription(team.getDescription());
        teamEntity.setHeadcoach(team.getHeadcoach());
        return teamEntity;
    }
}
