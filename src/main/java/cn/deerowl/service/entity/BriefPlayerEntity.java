package cn.deerowl.service.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
public class BriefPlayerEntity {

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
     * 球员主页地址
     */
    private String homepage;

}
