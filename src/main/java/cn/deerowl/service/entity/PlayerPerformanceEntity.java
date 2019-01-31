package cn.deerowl.service.entity;

import cn.deerowl.model.PlayerPerformance;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class PlayerPerformanceEntity {
    private long id;

    private BriefPlayerEntity briefPlayerEntity;

    private TeamEntity team;

    /**
     * 位置
     */
    private String position;

    /**
     * 上场时间
     */
    private int time;

    /**
     * 投篮命中数
     */
    private int successShot;

    /**
     * 投篮次数
     */
    private int totalShot;

    /**
     * 三分命中数
     */
    private int threePointSuccessShot;

    /**
     * 三分出手数
     */
    private int threePointTotalShot;

    /**
     * 罚球命中数
     */
    private int freeThrowSuccessShot;

    /**
     * 罚球总数
     */
    private int freeThrowTotalShot;

    /**
     * 前场篮板数
     */
    private int frontCourtRebound;

    /**
     * 后场篮板数
     */
    private int backCourtRebound;


    /**
     * 助攻数
     */
    private int assist;

    /**
     * 犯规数
     */
    private int foul;

    /**
     * 抢断
     */
    private int steal;

    /**
     * 失误
     */
    private int turnover;

    /**
     * 盖帽
     */
    private int block;

    /**
     * 得分
     */
    private int score;

    /**
     * 正负值
     */
    private int positiveAndNegativeValue;


    public static PlayerPerformanceEntity buildPlayerPerformanceEntity(PlayerPerformance playerPerformance,
                                                                       BriefPlayerEntity briefPlayerEntity,
                                                                       TeamEntity teamEntity){
        PlayerPerformanceEntity playerPerformanceEntity = new PlayerPerformanceEntity();
        playerPerformanceEntity.setId(playerPerformance.getId());
        playerPerformanceEntity.setBriefPlayerEntity(briefPlayerEntity);
        playerPerformanceEntity.setTeam(teamEntity);
        playerPerformanceEntity.setPosition(playerPerformance.getPosition());
        playerPerformanceEntity.setTime(playerPerformance.getTime());
        playerPerformanceEntity.setSuccessShot(playerPerformance.getSuccessShot());
        playerPerformanceEntity.setTotalShot(playerPerformance.getTotalShot());
        playerPerformanceEntity.setThreePointSuccessShot(playerPerformance.getThreePointSuccessShot());
        playerPerformanceEntity.setThreePointTotalShot(playerPerformance.getThreePointTotalShot());
        playerPerformanceEntity.setFreeThrowSuccessShot(playerPerformance.getFreeThrowSuccessShot());
        playerPerformanceEntity.setFreeThrowTotalShot(playerPerformance.getFreeThrowTotalShot());
        playerPerformanceEntity.setFrontCourtRebound(playerPerformance.getFrontCourtRebound());
        playerPerformanceEntity.setBackCourtRebound(playerPerformance.getBackCourtRebound());
        playerPerformanceEntity.setAssist(playerPerformance.getAssist());
        playerPerformanceEntity.setFoul(playerPerformance.getFoul());
        playerPerformanceEntity.setSteal(playerPerformance.getSteal());
        playerPerformanceEntity.setTurnover(playerPerformance.getTurnover());
        playerPerformanceEntity.setBlock(playerPerformance.getBlock());
        playerPerformanceEntity.setScore(playerPerformance.getScore());
        playerPerformanceEntity.setPositiveAndNegativeValue(playerPerformance.getPositiveAndNegativeValue());
        return playerPerformanceEntity;

    }
}
