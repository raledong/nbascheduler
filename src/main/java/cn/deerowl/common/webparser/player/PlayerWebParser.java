package cn.deerowl.common.webparser.player;

import cn.deerowl.common.pojo.PlayerPOJO;
import cn.deerowl.enumeration.TeamEnum;
import cn.deerowl.model.Team;

import java.util.List;

public interface PlayerWebParser {

    /**
     * 获取一个球队的球员
     * @param teamEnum
     * @return
     */
    List<PlayerPOJO> getPlayersOfATeam(TeamEnum teamEnum);

    /**
     * 获取一个球队的所有球员主页
     * @param teamEnum
     * @return
     */
    List<String> getPlayersHomepageOfATeam(TeamEnum teamEnum);

    /**
     * 获取球员的基本信息
     * @param homePageUrl
     * @return
     */
    PlayerPOJO getPlayer(String homePageUrl);

    /**
     * 获取当赛季所有球员的基本信息
     * 单线程执行，速度较慢
     * @// FIXME: 11/12/18  建议在getPlayer或getPlayersOfATeam层面上进行多线程的优化
     * @return
     */
    List<PlayerPOJO> getPlayersOfCurrentSeason();

}
