package cn.deerowl.dao;

import cn.deerowl.model.Team;

import java.util.List;

public interface TeamMapper {

    /**
     * 添加球队信息
     * @param team
     * @return
     */
    int add(Team team);

    /**
     * 批量添加球队信息
     * @Todo 尚未实现
     * @param teams
     * @return
     */
    int batchAdd(List<Team> teams);

    /**
     * 获取所有球队
     * @return
     */
    List<Team> getAllTeams();

    /**
     * 查找球队
     * @param cname
     * @return
     */
    Team searchByCname(String cname);

    /**
     * 根据球队ID获得球队
     * @param id
     * @return
     */
    Team findById(int id);
}
