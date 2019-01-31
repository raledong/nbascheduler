package cn.deerowl.service.web;

import cn.deerowl.enumeration.TeamEnum;
import cn.deerowl.model.Team;

public interface PlayerWebService {

    /**
     * 获取当前每个球队下球员的信息
     */
    void getAllPlayersAndSave();

    /**
     * 获取一个球队的所有球员并且保存
     */
    void getPlayersOfATeamAndSave(TeamEnum team);

    /**
     * 从球员主页获取数据并保存
     * 注意：此方法不会判断当前数据库中是否已经包含该球员的过时数据
     * 只适用于初始化数据库，或者确定该球员一定不存在与数据库中
     * @param homePage 球员主页
     */
    void getPlayerAndSave(String homePage);

    /**
     * 从球员主页获取数据并更新
     * @param homePage 球员主页
     */
    void getPlayerAndUpdate(String homePage);
}
