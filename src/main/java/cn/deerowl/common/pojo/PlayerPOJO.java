package cn.deerowl.common.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class PlayerPOJO {
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
     * 球队
     */
    private String team;

    /**
     * 球员主页地址
     */
    private String homePageUrl;

    public void setNumber(String number) {
        this.number = Integer.valueOf(number);
    }
}
