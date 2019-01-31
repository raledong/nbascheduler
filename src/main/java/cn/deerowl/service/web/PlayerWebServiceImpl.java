package cn.deerowl.service.web;

import cn.deerowl.common.pojo.PlayerPOJO;
import cn.deerowl.common.webparser.player.PlayerWebParser;
import cn.deerowl.dao.PlayerMapper;
import cn.deerowl.dao.TeamMapper;
import cn.deerowl.enumeration.TeamEnum;
import cn.deerowl.model.Player;
import cn.deerowl.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class PlayerWebServiceImpl implements PlayerWebService {

    private static final Logger logger = LoggerFactory.getLogger(PlayerWebService.class);

    @Autowired
    private PlayerWebParser playerWebParser;

    @Autowired
    private PlayerMapper playerMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Override
//    @Async
    public void getAllPlayersAndSave() {
        TeamEnum[] teams = TeamEnum.getAllTeams();
        for (TeamEnum team: teams) {
            getPlayersOfATeamAndSave(team);
        }
    }

    @Override
    @Async("webParser")
    public void getPlayersOfATeamAndSave(TeamEnum team) {
        List<String> homepages = playerWebParser.getPlayersHomepageOfATeam(team);
        getPlayersAndSave(homepages);
    }

    @Override
    @Async("webParser")
    public void getPlayerAndSave(String homePage) {
        try {
            PlayerPOJO playerPOJO = playerWebParser.getPlayer(homePage);
            if (playerPOJO == null) {
                logger.warn("该球员主页不存在，主页为: {}" , homePage);
                return;
            }
            Player player = new Player(playerPOJO);
            Team team = teamMapper.searchByCname(playerPOJO.getTeam());
            player.setTeam(team);
            playerMapper.add(player);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getPlayerAndUpdate(String homePage) {

    }

    private void getPlayersAndSave(List<String> homepages) {
        if (!CollectionUtils.isEmpty(homepages)) {
            homepages.stream()
                    .forEach(this::getPlayerAndSave);
        }
    }
}
