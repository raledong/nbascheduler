package cn.deerowl.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class PlayerPerformance {

    private long id;

    private long playerId;

    private int teamId;

    private long dataAnalysisId;

    /**
     * 是否首发
     */
    private boolean isStarting;
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


}
