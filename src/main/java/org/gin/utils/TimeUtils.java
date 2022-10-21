package org.gin.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * 时间工具类
 * @author bx002
 */
@SuppressWarnings("unused")
public class TimeUtils {
    public static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter FULL_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    public static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("Asia/Shanghai");

    /**
     * 智能识别字符串到 ZonedDateTime
     * @param string 字符串
     * @return ZonedDateTime
     */
    public static ZonedDateTime parse(String string) {
        return parse(string, DEFAULT_ZONE_ID);
    }

    /**
     * 智能识别字符串到 ZonedDateTime
     * @param string 字符串
     * @return ZonedDateTime
     */
    public static ZonedDateTime parse(String string, ZoneId zoneId) {
        try {
            final LocalDateTime parse = LocalDateTime.parse(string, FULL_FORMATTER);
            return ZonedDateTime.ofLocal(parse, zoneId, null);
        } catch (Exception ignored) {
        }
        try {
            final LocalDateTime parse = LocalDateTime.parse(string, DATE_TIME_FORMATTER);
            return ZonedDateTime.ofLocal(parse, zoneId, null);
        } catch (Exception ignored) {
        }
        try {
            final LocalDate parse = LocalDate.parse(string, DATE_FORMATTER);
            return ZonedDateTime.of(parse, LocalTime.MIN, zoneId);
        } catch (Exception ignored) {
        }
        try {
            return ZonedDateTime.parse(string);
        } catch (Exception ignored) {
        }

        throw new RuntimeException("无法识别的日期格式:" + string);
    }

    /**
     * 按格式输出一个日期时间
     * @param zdt ZonedDateTime
     * @return 日期时间
     */
    public static String format(ZonedDateTime zdt) {
        return format(zdt, DATE_TIME_FORMATTER);
    }

    /**
     * 按格式输出一个日期时间
     * @param formatter 格式
     * @param zdt       ZonedDateTime
     * @return 日期时间
     */
    public static String format(ZonedDateTime zdt, DateTimeFormatter formatter) {
        return formatter.format(zdt);
    }

    /**
     * 按格式输出一个日期时间(单位:秒)
     * @param time 时间戳
     * @return 日期时间
     */
    public static String format(long time) {
        return format(time, TimeUnit.SECONDS);
    }

    /**
     * 按格式输出一个日期时间
     * @param time     时间戳
     * @param timeUnit 时间单位
     * @return 日期时间
     */
    public static String format(long time, TimeUnit timeUnit) {
        return format(time, timeUnit, DATE_TIME_FORMATTER);
    }

    /**
     * 按格式输出一个日期时间
     * @param time      时间戳
     * @param timeUnit  时间单位
     * @param formatter 输出格式
     * @return 日期时间
     */
    public static String format(long time, TimeUnit timeUnit, DateTimeFormatter formatter) {
        return format(time, timeUnit, formatter, DEFAULT_ZONE_ID);
    }

    /**
     * 按格式输出一个日期时间
     * @param time      时间戳
     * @param timeUnit  时间单位
     * @param formatter 输出格式
     * @param zoneId    地区id
     * @return 日期时间
     */
    public static String format(long time, TimeUnit timeUnit, DateTimeFormatter formatter, ZoneId zoneId) {
        final long sec = timeUnit.toSeconds(time);
        return formatter.format(ZonedDateTime.ofInstant(Instant.ofEpochSecond(sec), zoneId));
    }


}
