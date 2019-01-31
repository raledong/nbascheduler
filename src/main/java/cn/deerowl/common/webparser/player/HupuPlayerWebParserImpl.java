package cn.deerowl.common.webparser.player;

import cn.deerowl.common.pojo.PlayerPOJO;
import cn.deerowl.common.webparser.Action;
import cn.deerowl.common.webparser.WebParser;
import cn.deerowl.enumeration.TeamEnum;
import cn.deerowl.model.Team;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class HupuPlayerWebParserImpl implements PlayerWebParser {

    private static final Logger logger = LoggerFactory.getLogger(HupuPlayerWebParserImpl.class);
    /**
     * 虎扑球员详情页地址前缀
     */
    private static final String BASE_PLAYER_URL = "https://nba.hupu.com/players/";

    private static final String BASE_TEAM_PLAYER_URL = "https://nba.hupu.com/players/";

    private static final String POSITION = "位置";
    private static final String HEIGHT = "身高";
    private static final String WEIGHT = "体重";
    private static final String BIRTH = "生日";
    private static final String TEAM = "球队";
    private static final String SCHOOL = "学校";
    private static final String DRAFT = "选秀";
    private static final String NATIONALITY = "国籍";
    private static final String SALARY = "本赛季薪金";
    private static final String CONTRACT = "合同";

    @Autowired
    private WebParser webParser;

    @Override
    public List<PlayerPOJO> getPlayersOfATeam(TeamEnum teamEnum) {
        List<PlayerPOJO> players = new ArrayList<>();
        String teamPlayerPage = BASE_TEAM_PLAYER_URL + teamEnum.getEname().toLowerCase();
        try {
            Document document = webParser.getDocumentFromUrl(teamPlayerPage, Action.GET);
            if (document != null) {
                Elements elements = document.select("tr td.left a");
                Iterator<Element> elementIterator = elements.iterator();
                while (elementIterator.hasNext()) {
                    Element element = elementIterator.next();
                    String homePageUrl = element.attr("href");
                    PlayerPOJO playerPOJO = getPlayer(homePageUrl);
                    if (playerPOJO != null) {
                        players.add(playerPOJO);
                    }
                }
            } else {
                throw new RuntimeException("该球队网页暂时无法访问, 球队：" + teamEnum.getEname());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return players;
    }

    @Override
    public List<String> getPlayersHomepageOfATeam(TeamEnum teamEnum) {
        List<String> homePages = new ArrayList<>();
        String teamPlayerPage = BASE_TEAM_PLAYER_URL + teamEnum.getEname().toLowerCase();
        try {
            Document document = webParser.getDocumentFromUrl(teamPlayerPage, Action.GET);
            if (document != null) {
                Elements elements = document.select("tr td.left a");
                Iterator<Element> elementIterator = elements.iterator();
                while (elementIterator.hasNext()) {
                    Element element = elementIterator.next();
                    String homePageUrl = element.attr("href");
                    homePages.add(homePageUrl);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return homePages;
        }
        return homePages;
    }

    @Override
    public PlayerPOJO getPlayer(String homePageUrl) {
        if (StringUtils.isEmpty(homePageUrl)) {
            logger.error("球员主页地址不能为空");
            throw new RuntimeException("球员主页地址不能为空");
        }
        try {
            Document document = webParser.getDocumentFromUrl(homePageUrl, Action.GET);
            if (document != null) {
                PlayerPOJO playerPOJO = new PlayerPOJO();
                //主页
                playerPOJO.setHomePageUrl(homePageUrl);
                Elements nameElements = document.select("div.team_data h2");
                if (! nameElements.isEmpty()) {
                    String nameInfo = nameElements.text();
                    String ename = nameInfo.substring(nameInfo.indexOf("（") + 1, nameInfo.indexOf("）"));
                    String cname = nameInfo.substring(0, nameInfo.indexOf("（"));
                    playerPOJO.setEname(ename);
                    playerPOJO.setCname(cname);
                }
                Elements elements = document.select("div.clearfix");
                if (!elements.isEmpty()) {
                    //头像
                    String imageUrl = elements.select("img").attr("src");
                    playerPOJO.setHeadImg(imageUrl);

                    Elements detailElements = elements.select("div.font p");
                    Iterator<Element> elementIterator = detailElements.iterator();
                    while (elementIterator.hasNext()) {
                        Element element = elementIterator.next();
                        String text = element.text();
                        if (text.startsWith(POSITION)) {
                            if (text.contains("号")) {
                                String position = text.substring(
                                        POSITION.length() + 1,
                                        text.indexOf("（"));
                                playerPOJO.setPosition(position);
                                String number = text.substring(
                                        text.indexOf("（") + 1,
                                        text.indexOf("号"));
                                playerPOJO.setNumber(number);
                            } else {
                                String position = text.substring(POSITION.length() + 1);
                                playerPOJO.setPosition(position);
                                playerPOJO.setNumber("-1");
                            }
                        } else if (text.startsWith(HEIGHT)) {
                            String height = text.substring(HEIGHT.length() + 1);
                            playerPOJO.setHeight(height);
                        } else if (text.startsWith(WEIGHT)) {
                            String weight = text.substring(WEIGHT.length() + 1);
                            playerPOJO.setWeight(weight);
                        } else if (text.startsWith(BIRTH)) {
                            String birth = text.substring(BIRTH.length() + 1);
                            playerPOJO.setBirth(birth);
                        } else if (text.startsWith(TEAM)) {
                            String team = text.substring(TEAM.length() + 1);
                            playerPOJO.setTeam(team);
                        } else if (text.startsWith(SCHOOL)) {
                            String school = text.substring(SCHOOL.length() + 1);
                            playerPOJO.setSchool(school);
                        } else if (text.startsWith(DRAFT)) {
                            String draft = text.substring(DRAFT.length() + 1);
                            playerPOJO.setDraft(draft);
                        } else if (text.startsWith(NATIONALITY)) {
                            String nationality = text.substring(NATIONALITY.length() + 1);
                            playerPOJO.setNationality(nationality);
                        } else if (text.startsWith(SALARY)) {
                            String salary = text.substring(SALARY.length() + 1);
                            playerPOJO.setSalaryThisYear(salary);
                        } else if (text.startsWith(CONTRACT)) {
                            String contract = text.substring(CONTRACT.length() + 1);
                            playerPOJO.setContract(contract);
                        }
                    }
                }
                logger.info(playerPOJO.toString());
                return playerPOJO;
            } else {
                throw new RuntimeException("球员主页地址无法访问，地址为" + homePageUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PlayerPOJO> getPlayersOfCurrentSeason() {
        List<PlayerPOJO> result = new ArrayList<>();
        TeamEnum[] teams = TeamEnum.getAllTeams();
        for (TeamEnum teamEnum : teams) {
            List<PlayerPOJO> playersOfATeam = getPlayersOfATeam(teamEnum);
            result.addAll(playersOfATeam);
        }
        return result;
    }
}
