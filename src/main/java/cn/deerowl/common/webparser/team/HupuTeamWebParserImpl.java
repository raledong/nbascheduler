package cn.deerowl.common.webparser.team;

import cn.deerowl.common.pojo.TeamPOJO;
import cn.deerowl.common.webparser.Action;
import cn.deerowl.common.webparser.WebParser;
import cn.deerowl.common.webparser.WebParserImpl;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class HupuTeamWebParserImpl implements TeamWebParser {

    private static final String HUPU_TEAM_HOME_PAGE = "https://nba.hupu.com/teams";

    @Autowired
    private WebParser webParser;

    public TeamPOJO getTeam(String url) {
        TeamPOJO teamPOJO = new TeamPOJO();
        try {
            Document document = webParser.getDocumentFromUrl(url, Action.GET);
            if (document != null) {
                String name = document.select("span.title-text").text();
                String[] splitName = name.replace("）", "")
                        .split("（");
                teamPOJO.setCname(splitName[0]);
                teamPOJO.setEname(splitName[1]);

                Elements elements = document.select("div.clearfix .font p");

                String courtAndDivision = elements.get(1).text();
                teamPOJO.setStadium(courtAndDivision.substring(3, courtAndDivision.indexOf("分区")).trim());
                teamPOJO.setDivision(courtAndDivision.substring(courtAndDivision.indexOf("分区") + 3));

                String homePage = elements.get(2).text().substring(3);
                teamPOJO.setHomePage(homePage);

                String headCouch = elements.get(3).text().substring(4);
                teamPOJO.setHeadcoach(headCouch);

                String desc = document.select("div.txt").text();
                teamPOJO.setDesc(desc);
                System.out.println(teamPOJO);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return teamPOJO;
    }

    public List<TeamPOJO> getTeams() {
        List<String> homePages = getTeamHomePages();
        List<TeamPOJO> teams = homePages.stream()
                .map(this::getTeam)
                .collect(Collectors.toList());
        return teams;
    }

    public List<String> getTeamHomePages() {
        List<String> homePages = new ArrayList<>();
        try {
            Document document = webParser.getDocumentFromUrl(HUPU_TEAM_HOME_PAGE, Action.GET);
            if (document != null) {
                Elements elements = document.select("a.a_teamlink");
                if (elements.size() != 0) {
                    homePages = elements.eachAttr("href");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return homePages;
    }

}
