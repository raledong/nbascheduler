package cn.deerowl.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class DataAnalysis extends BriefDataAnalysis{

    private List<PlayerPerformance> playerPerformances;

}
