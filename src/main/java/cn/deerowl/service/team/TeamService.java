package cn.deerowl.service.team;

import cn.deerowl.enumeration.TeamEnum;
import cn.deerowl.service.entity.TeamEntity;

public interface TeamService {

    TeamEntity getByTeamEnum(TeamEnum teamEnum);

    int getTeamIdByTeamEnum(TeamEnum teamEnum);

    TeamEntity getTeamById(int teamId);
}
