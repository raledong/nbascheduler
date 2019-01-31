package cn.deerowl.common.webparser.schedule;

import cn.deerowl.common.pojo.SchedulePOJO;
import cn.deerowl.common.webparser.Action;
import cn.deerowl.common.webparser.WebParser;
import cn.deerowl.util.DateUtil;
import cn.deerowl.util.LoggerUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service("hupuScheduleWebParser")
public class HupuScheduleWebParser implements ScheduleWebParser{

    private static final Logger logger = LoggerUtil.getLogger(ScheduleWebParser.class);

    private static final String HUPU_SCHEDULE_HOME_PAGE = "https://nba.hupu.com/schedule/";

    @Autowired
    private WebParser webParser;

    @Override
    public List<SchedulePOJO> getSchedules(LocalDate date) {
        List<SchedulePOJO> result = new ArrayList<>();
        String dateString = DateUtil.fromDateToString(date, DateUtil.BASE_DATE_FORMAT);
        String link = HUPU_SCHEDULE_HOME_PAGE + dateString;
        try {
            Document document = webParser.getDocumentFromUrl(link, Action.GET, null);
            if (document != null) {
                Element element = document.select("tbody tr.left.linglei").first();
                String dateBrief = DateUtil.fromDateToString(date, DateUtil.BASE_MONTH_DAY_FORMAT_CHINESE);
                if (element != null && element.text().startsWith(dateBrief)) {
                    element = element.nextElementSibling();
                    do {
                        SchedulePOJO schedulePOJO = new SchedulePOJO();
                        Elements childElements = element.select("td");

                        schedulePOJO.setDate(dateString + " " + childElements.get(0).text());

                        String[] teams = childElements.get(1).text().split("vs");
                        schedulePOJO.setVisitTeam(teams[0].trim());
                        schedulePOJO.setHomeTeam(teams[1].trim());

                        schedulePOJO.setDataAnalysisLink(childElements.get(2).select("a").attr("href"));

                        logger.info(schedulePOJO.toString());
                        result.add(schedulePOJO);
                        element = element.nextElementSibling();
                    }while (element != null && !element.hasClass("linglei"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }
}
