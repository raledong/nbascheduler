package cn.deerowl.service.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
public class PlayerEntity extends BriefPlayerEntity{

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
     * 当前所在球队
     */
    private TeamEntity team;


}
