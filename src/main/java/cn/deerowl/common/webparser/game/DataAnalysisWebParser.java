package cn.deerowl.common.webparser.game;

import cn.deerowl.common.pojo.DataAnalysisPOJO;

import java.util.concurrent.Future;

public interface DataAnalysisWebParser {

        /**
         * 同步获得比赛统计数据
         * @param url
         * @return
         */
        DataAnalysisPOJO getDataAnalysis(String url);

}
