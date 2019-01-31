package cn.deerowl.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@EqualsAndHashCode
public class Game extends BriefGame{

    private DataAnalysis dataAnalysis;
}
