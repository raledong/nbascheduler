package cn.deerowl.common.webparser.game;

import cn.deerowl.common.pojo.DataAnalysisPOJO;
import cn.deerowl.common.pojo.PlayerPerformancePOJO;
import cn.deerowl.common.webparser.Action;
import cn.deerowl.common.webparser.WebParser;
import cn.deerowl.util.LoggerUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class HupuDataAnalysisWebParserImpl implements DataAnalysisWebParser {

    private static final Logger logger = LoggerUtil.getLogger(DataAnalysisWebParser.class);

    private static final String START_AT_PREFIX = "开赛";
    private static final String CONSUME_TIME_PREFIX = "耗时";
    private static final String STADIUM_PREFIX = "球馆";
    private static final String AUDIENCE_COUNT_PREFIX = "上座";
    private static final String NO_DATA_YET = "暂无统计";

    private static final String SPLIT_SIGN = "-";
    @Autowired
    private WebParser webParser;

    @Override
    public DataAnalysisPOJO getDataAnalysis(String url) {
        if (StringUtils.isEmpty(url)) {
            throw new RuntimeException("URL is invalid");
        }
        try {
            Document document = webParser.getDocumentFromUrl(url, Action.GET, null);
            if (document != null) {
                DataAnalysisPOJO dataAnalysisPOJO = new DataAnalysisPOJO();

                //客场球队的基本信息
                Elements baseVisitTeamElements = document.select("div.team_vs_box .team_a .message");
                String visitTeamScore = baseVisitTeamElements.select("h2").text();
                String visitTeamName = baseVisitTeamElements.select("p").text();
                dataAnalysisPOJO.setVisitTeam(visitTeamName);
                dataAnalysisPOJO.setVisitTeamScore(visitTeamScore);

                //主场球队的基本信息
                Elements baseHomeTeamElements = document.select("div.team_vs_box .team_b .message");
                String homeTeamScore = baseHomeTeamElements.select("h2").text();
                String homeTeamName = baseHomeTeamElements.select("p").text();
                dataAnalysisPOJO.setHomeTeam(homeTeamName);
                dataAnalysisPOJO.setHomeTeamScore(homeTeamScore);

                setBaseInfo(document, dataAnalysisPOJO);

                setVisitTeamScore(document, dataAnalysisPOJO);
                setHomeTeamScore(document, dataAnalysisPOJO);

                setVisitTeamPlayerPerformance(document, dataAnalysisPOJO);
                setHomeTeamPlayerPerformances(document, dataAnalysisPOJO);

                LoggerUtil.info(logger, "比赛结果为{}", dataAnalysisPOJO);
                return dataAnalysisPOJO;
            } else {
                LoggerUtil.warn(logger, "比赛数据统计地址无法访问，url={}", url);
            }
        } catch (IOException e) {
            LoggerUtil.error(logger, e,"数据统计爬取异常，url={}", url);
        }
        return null;
    }


    /**
     * 爬取比赛的基本信息：开始时间，时长，场馆，上座率
     * @param document
     * @param dataAnalysisPOJO
     */
    private void setBaseInfo(Document document, DataAnalysisPOJO dataAnalysisPOJO) {
        Elements timeElements = document.select("div.about_fonts.clearfix p");
        Iterator<Element> elementIterator = timeElements.iterator();
        while (elementIterator.hasNext()) {
            Element element = elementIterator.next();
            String text = element.text();
            if (text.startsWith(START_AT_PREFIX)) {
                String time = text.substring(STADIUM_PREFIX.length() + 1);
                dataAnalysisPOJO.setStartAt(time);
            } else if (text.startsWith(CONSUME_TIME_PREFIX)) {
                String consumeTime = text.substring(CONSUME_TIME_PREFIX.length() + 1);
                if (!consumeTime.equals(NO_DATA_YET)) {
                    dataAnalysisPOJO.setLasts(consumeTime);
                }
            } else if (text.startsWith(STADIUM_PREFIX)) {
                String stadium = text.substring(STADIUM_PREFIX.length() + 1);
                dataAnalysisPOJO.setStadium(stadium);
            } else if (text.startsWith(AUDIENCE_COUNT_PREFIX)) {
                String audienceCount = text.substring(AUDIENCE_COUNT_PREFIX.length() + 1, text.length()-1);
                if (!audienceCount.equals(NO_DATA_YET)) {
                    dataAnalysisPOJO.setAudienceCount(audienceCount);
                }
            }
        }
    }

    /**
     * 爬取客队各节的比分
     * @param document
     * @param dataAnalysisPOJO
     */
    private void setVisitTeamScore(Document document, DataAnalysisPOJO dataAnalysisPOJO) {
        Elements visitTeamScoreElements = document.select("table.itinerary_table tr.away_score td");
        if (!visitTeamScoreElements.isEmpty()) {
            String firstSectionScore = visitTeamScoreElements.get(1).text();
            String secondSectionScore = visitTeamScoreElements.get(2).text();
            String thirdSectionScore = visitTeamScoreElements.get(3).text();
            String fourthSectionScore = visitTeamScoreElements.get(4).text();

            dataAnalysisPOJO.setVisitTeamFirstSectionScore(firstSectionScore);
            dataAnalysisPOJO.setVisitTeamSecondSectionScore(secondSectionScore);
            dataAnalysisPOJO.setVisitTeamThirdSectionScore(thirdSectionScore);
            dataAnalysisPOJO.setVisitTeamFourthSectionScore(fourthSectionScore);
        }
    }

    /**
     * 爬取主队各节的比分
     * @param document
     * @param dataAnalysisPOJO
     */
    private void setHomeTeamScore(Document document, DataAnalysisPOJO dataAnalysisPOJO) {
        Elements homeTeamScoreElements = document.select("table.itinerary_table tr.home_score td");
        if (!homeTeamScoreElements.isEmpty()) {
            String firstSectionScore = homeTeamScoreElements.get(1).text();
            String secondSectionScore = homeTeamScoreElements.get(2).text();
            String thirdSectionScore = homeTeamScoreElements.get(3).text();
            String fourthSectionScore = homeTeamScoreElements.get(4).text();

            dataAnalysisPOJO.setHomeTeamFirstSectionScore(firstSectionScore);
            dataAnalysisPOJO.setHomeTeamSecondSectionScore(secondSectionScore);
            dataAnalysisPOJO.setHomeTeamThirdSectionScore(thirdSectionScore);
            dataAnalysisPOJO.setHomeTeamFourthSectionScore(fourthSectionScore);
        }
    }

    /**
     * 爬取客队的球员表现
     * @param document
     * @param dataAnalysisPOJO
     */
    private void setVisitTeamPlayerPerformance(Document document, DataAnalysisPOJO dataAnalysisPOJO) {
        Elements elements = document.select("#J_away_content tr");
        List<PlayerPerformancePOJO> visitTeamPlayerPerformances = getPlayerPerformances(elements);
        dataAnalysisPOJO.setVisitTeamPlayerPerformances(visitTeamPlayerPerformances);

    }

    /**
     * 爬取主队的球员表现
     * @param document
     * @param dataAnalysisPOJO
     */
    private void setHomeTeamPlayerPerformances(Document document, DataAnalysisPOJO dataAnalysisPOJO) {
        Elements elements = document.select("#J_home_content tr");
        List<PlayerPerformancePOJO> homeTeamPlayerPerformances = getPlayerPerformances(elements);
        dataAnalysisPOJO.setHomeTeamPlayerPerformances(homeTeamPlayerPerformances);

    }

    private List<PlayerPerformancePOJO> getPlayerPerformances(Elements elements) {
        List<PlayerPerformancePOJO> playerPerformances = new ArrayList<>();
        if (!elements.isEmpty()) {

            Iterator<Element> elementIterator = elements.iterator();
            while (elementIterator.hasNext()) {
                Element element = elementIterator.next();
                //非标题行
                if (!element.attr("class").startsWith("title")) {
                    PlayerPerformancePOJO playerPerformancePOJO = new PlayerPerformancePOJO();
                    Elements tdElements = element.select("td");

                    String name = tdElements.get(0).text();
                    playerPerformancePOJO.setName(name);
                    String homePageUrl = tdElements.select("a").get(0).attr("href");
                    playerPerformancePOJO.setHomePageUrl(homePageUrl);

                    String position = tdElements.get(1).text();
                    playerPerformancePOJO.setPosition(position);

                    String time = tdElements.get(2).text();
                    playerPerformancePOJO.setTime(time);

                    String shoot = tdElements.get(3).text();
                    String successShot = shoot.substring(0, shoot.indexOf(SPLIT_SIGN));
                    String totalShot = shoot.substring(shoot.indexOf(SPLIT_SIGN) + 1);
                    playerPerformancePOJO.setSuccessShot(successShot);
                    playerPerformancePOJO.setTotalShot(totalShot);

                    String threePointShot = tdElements.get(4).text();
                    String successThreePointShot = threePointShot.substring(0, threePointShot.indexOf(SPLIT_SIGN));
                    String totalThreePointShot = threePointShot.substring(threePointShot.indexOf(SPLIT_SIGN) + 1);
                    playerPerformancePOJO.setThreePointSuccessShot(successThreePointShot);
                    playerPerformancePOJO.setThreePointTotalShot(totalThreePointShot);

                    String freeThrows = tdElements.get(5).text();
                    String successFreeThrow = freeThrows.substring(0, freeThrows.indexOf(SPLIT_SIGN));
                    String totalFreeThrow = freeThrows.substring(freeThrows.indexOf(SPLIT_SIGN) + 1);
                    playerPerformancePOJO.setFreeThrowSuccessShot(successFreeThrow);
                    playerPerformancePOJO.setFreeThrowTotalShot(totalFreeThrow);

                    String frontRebound = tdElements.get(6).text();
                    playerPerformancePOJO.setFrontCourtRebound(frontRebound);

                    String bachRebound = tdElements.get(7).text();
                    playerPerformancePOJO.setBackCourtRebound(bachRebound);

                    String assist = tdElements.get(9).text();
                    playerPerformancePOJO.setAssist(assist);

                    String foul = tdElements.get(10).text();
                    playerPerformancePOJO.setFoul(foul);

                    String steal = tdElements.get(11).text();
                    playerPerformancePOJO.setSteal(steal);

                    String fault = tdElements.get(12).text();
                    playerPerformancePOJO.setFault(fault);

                    String block = tdElements.get(13).text();
                    playerPerformancePOJO.setBlock(block);

                    String score = tdElements.get(14).text();
                    playerPerformancePOJO.setScore(score);

                    String positiveAndNegativeValue = tdElements.get(15).text();
                    playerPerformancePOJO.setPositiveAndNegativeValue(positiveAndNegativeValue);

                    playerPerformances.add(playerPerformancePOJO);
                }
            }
        }
        return playerPerformances;
    }

}
