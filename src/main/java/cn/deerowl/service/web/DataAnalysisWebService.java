package cn.deerowl.service.web;

public interface DataAnalysisWebService {

    /**
     * 获取赛程的比赛结果并保存
     * 如果当前比赛结果已经存在，则更新比赛结果
     * @param scheduleId
     */
    void fetchDataAnalysisAndSave(long scheduleId);

}
