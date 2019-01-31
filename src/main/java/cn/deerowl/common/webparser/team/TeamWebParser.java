package cn.deerowl.common.webparser.team;

import cn.deerowl.common.pojo.TeamPOJO;
import cn.deerowl.model.Team;

import java.util.List;

public interface TeamWebParser {

    /**
     * 获得一只球队的信息
     * @param url
     * @return
     */
    TeamPOJO getTeam(String url);

    /**
     * 获得所有球队的信息
     * @param
     * @return
     */
    List<TeamPOJO> getTeams();

    /**
     * 获取所有球队的主页地址
     * @return
     */
    List<String> getTeamHomePages();
}
