package cn.deerowl.model;

import cn.deerowl.common.pojo.PlayerPOJO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Player {

    private long id;

    /**
     * 中文名
     */
    private String cname;

    /**
     * 英文名
     */
    private String ename;

    /**
     * 头像地址
     */
    private String headImg;

    /**
     * 位置
     */
    private String position;

    /**
     * 号码
     */
    private int number;

    /**
     * 身高
     */
    private String height;

    /**
     * 体重
     */
    private String weight;

    /**
     * 生日
     */
    private String birth;

    /**
     * 合同金额
     */
    private String contract;

    /**
     * 本赛季薪水
     */
    private String salaryThisYear;

    /**
     * 学校
     */
    private String school;

    /**
     * 选秀
     */
    private String draft;

    /**
     * 国籍
     */
    private String nationality;

    /**
     * 球员主页地址
     */
    private String homepage;

    /**
     * 球队
     */
    private Team team;

    public Player(){}

    public Player(PlayerPOJO playerPOJO) {
        this.setCname(playerPOJO.getCname());
        this.setEname(playerPOJO.getEname());
        this.setHeadImg(playerPOJO.getHeadImg());
        this.setPosition(playerPOJO.getPosition());
        this.setNumber(playerPOJO.getNumber());
        this.setHeight(playerPOJO.getHeight());
        this.setWeight(playerPOJO.getWeight());
        this.setBirth(playerPOJO.getBirth());
        this.setContract(playerPOJO.getContract());
        this.setSalaryThisYear(playerPOJO.getSalaryThisYear());
        this.setSchool(playerPOJO.getSchool());
        this.setDraft(playerPOJO.getDraft());
        this.setNationality(playerPOJO.getNationality());
        this.setHomepage(playerPOJO.getHomePageUrl());
        //没有设置队员所在的球队
    }
}
