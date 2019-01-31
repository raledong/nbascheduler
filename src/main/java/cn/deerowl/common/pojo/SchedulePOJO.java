package cn.deerowl.common.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class SchedulePOJO {

    /**
     * 主队
     */
    private String homeTeam;

    /**
     * 客队
     */
    private String visitTeam;

    /**
     * 比赛场馆
     */
    private String stadium;

    /**
     * 开始时间
     */
    private String date;

    /**
     * 链接
     */
    private String dataAnalysisLink;

    /**
     * 文字直播链接
     */
    private String liveLink;



}
