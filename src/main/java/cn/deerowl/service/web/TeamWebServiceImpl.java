package cn.deerowl.service.web;

import cn.deerowl.common.pojo.TeamPOJO;
import cn.deerowl.common.webparser.team.TeamWebParser;
import cn.deerowl.dao.TeamMapper;
import cn.deerowl.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TeamWebServiceImpl implements TeamWebService {

    @Autowired
    @Qualifier("webParser")
    private TaskExecutor taskExecutor;

    @Autowired
    private TeamWebParser teamWebParser;

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public void fetchAllNbaTeamsAndSave() {
        List<String> homePages = teamWebParser.getTeamHomePages();
        homePages.stream().forEach(this::fetchNbaTeamAndSave);
    }

    @Override
    public void fetchNbaTeamAndSave(String homePage) {
        TeamPOJO teamPOJO = teamWebParser.getTeam(homePage);
        if (teamPOJO != null) {
            taskExecutor.execute(() -> {
                Team team = new Team(teamPOJO);
                teamMapper.add(team);
            });
        }
    }

}
