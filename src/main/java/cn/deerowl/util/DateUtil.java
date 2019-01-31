package cn.deerowl.util;


import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    public static final String BASE_DATE_FORMAT = "yyyy-MM-dd";
    public static final String BASE_MONTH_DAY_FORMAT_CHINESE = "MM月dd日";

    public static final String BASE_DATE_TIME_FORMAT = "yyyy-MM-dd hh:MM:ss";
    public static final String BASE_DATE_TIME_FORMAT_WITHOUT_SECONDS = "yyyy-MM-dd HH:mm";

    public static String fromDateToString(LocalDate date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    public static LocalDate fromStringToDate(String dateString, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(dateString, formatter);
    }

    public static LocalDateTime fromStringToDatetime(String dataTimeString, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(dataTimeString, formatter);
    }

    public static String fromDateTimeToString(LocalDateTime date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }
}
