package cn.deerowl.service.web;

import java.time.LocalDate;

public interface TeamWebService {

    /**
     * 获取nba球队的信息并保存至数据库
     */
    void fetchAllNbaTeamsAndSave();

    /**
     * 根据球队主页获取nba球队并保存
     * @param homepage
     */
   void fetchNbaTeamAndSave(String homepage);
}
