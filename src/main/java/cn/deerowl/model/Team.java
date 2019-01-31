package cn.deerowl.model;

import cn.deerowl.common.pojo.TeamPOJO;
import cn.deerowl.enumeration.DivisionEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode
public class Team {

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
    private int division;

    /**
     * 球馆
     */
    private String stadium;

    /**
     * 球队主页
     */
    private String homePage;

    public Team() {}

    public Team(TeamPOJO teamPOJO) {
        this.cname = teamPOJO.getCname();
        this.setEname(teamPOJO.getEname());
        this.setDescription(teamPOJO.getDesc());
        this.setDivisionByDivisionEnum(DivisionEnum.getDivisionByDesc(teamPOJO.getDivision()));
        this.setHeadcoach(teamPOJO.getHeadcoach());
        this.setStadium(teamPOJO.getStadium());
        this.setHomePage(teamPOJO.getHomePage());
    }

    public DivisionEnum getDivisionToDivisionEnum() {
        return DivisionEnum.getDivisionByCode(division);
    }

    public void setDivisionByDivisionEnum(DivisionEnum divisionEnum) {
        if (divisionEnum != null) {
            this.division = divisionEnum.getDivisionCode();
        }
    }



}
