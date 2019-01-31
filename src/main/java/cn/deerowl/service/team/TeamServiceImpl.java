package cn.deerowl.service.team;

import cn.deerowl.dao.TeamMapper;
import cn.deerowl.enumeration.TeamEnum;
import cn.deerowl.model.Team;
import cn.deerowl.service.entity.TeamEntity;
import cn.deerowl.util.LoggerUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

    private static final Logger logger = LoggerUtil.getLogger(TeamServiceImpl.class);

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public TeamEntity getByTeamEnum(TeamEnum teamEnum) {
        String fullCname = teamEnum.getFullCname();
        Team team = teamMapper.searchByCname(fullCname);
        return buildTeamEntity(team);
    }

    @Override
    public int getTeamIdByTeamEnum(TeamEnum teamEnum) {
        String fullCname = teamEnum.getFullCname();
        Team team = teamMapper.searchByCname(fullCname);
        return team.getId();
    }

    @Override
    public TeamEntity getTeamById(int teamId) {
        Team team = teamMapper.findById(teamId);
        if (team == null) {
            LoggerUtil.error(logger, "该球队不存在，teamId={}", teamId);
            return null;
        }
        return TeamEntity.buildTeamEntity(team);
    }

    private TeamEntity buildTeamEntity(Team team) {
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(team.getId());
        teamEntity.setCname(team.getCname());
        teamEntity.setDescription(team.getDescription());
        teamEntity.setDivision(team.getDivisionToDivisionEnum());
        teamEntity.setEname(team.getEname());
        teamEntity.setHeadcoach(team.getHeadcoach());
        teamEntity.setHomePage(team.getHomePage());
        teamEntity.setPrename(team.getPrename());
        teamEntity.setStadium(team.getStadium());
        return teamEntity;
    }
}
