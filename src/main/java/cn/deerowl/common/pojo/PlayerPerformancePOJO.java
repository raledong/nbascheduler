package cn.deerowl.common.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class PlayerPerformancePOJO {

    /**
     * 姓名
     */
    private String name;

    /**
     * 球员主页
     */
    private String homePageUrl;

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
    private int fault;

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

    public void setTime(String time) {
        this.time = Integer.valueOf(time);
    }

    public void setSuccessShot(String successShot) {
        this.successShot = Integer.valueOf(successShot);
    }

    public void setTotalShot(String totalShot) {
        this.totalShot = Integer.valueOf(totalShot);
    }

    public void setThreePointSuccessShot(String threePointSuccessShot) {
        this.threePointSuccessShot = Integer.valueOf(threePointSuccessShot);
    }

    public void setThreePointTotalShot(String threePointTotalShot) {
        this.threePointSuccessShot = Integer.valueOf(threePointTotalShot);
    }

    public void setFreeThrowSuccessShot(String freeThrowSuccessShot) {
        this.freeThrowSuccessShot = Integer.valueOf(freeThrowSuccessShot);
    }

    public void setFreeThrowTotalShot(String freeThrowTotalShot) {
        this.freeThrowTotalShot = Integer.valueOf(freeThrowTotalShot);
    }

    public void setFrontCourtRebound(String frontCourtRebound) {
        this.frontCourtRebound = Integer.valueOf(frontCourtRebound);
    }

    public void setBackCourtRebound(String backCourtRebound) {
        this.backCourtRebound = Integer.valueOf(backCourtRebound);
    }

    public void setAssist(String assist) {
        this.assist = Integer.valueOf(assist);
    }

    public void setFoul(String foul) {
        this.foul = Integer.valueOf(foul);
    }

    public void setSteal(String steal) {
        this.steal = Integer.valueOf(steal);
    }

    public void setFault(String fault) {
        this.fault = Integer.valueOf(fault);
    }

    public void setBlock(String block) {
        this.block = Integer.valueOf(block);
    }

    public void setScore(String score) {
        this.score = Integer.valueOf(score);
    }
    public void setPositiveAndNegativeValue(String positiveAndNegativeValue) {
        this.positiveAndNegativeValue = Integer.valueOf(positiveAndNegativeValue);
    }
}
