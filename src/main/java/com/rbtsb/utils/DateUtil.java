package com.rbtsb.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * ISO Date 8601 Utils
 * <p>
 * https://stackoverflow.com/questions/2201925/converting-iso-8601-compliant-string-to-java-util-date
 *
 * @author Kent
 */
public final class DateUtil {

    private final static Logger log = LoggerFactory.getLogger(DateUtil.class);

    public static final String ddMMyyyyHHmm = "dd/MM/yyyy HH:mm";

    private final static DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd'T'HH:mm:ss.SSS").appendOffsetId().toFormatter();

    /**
     * Check if the given String is ISO 8601 Date Format
     *
     * @param dateStr
     * @return
     * @throws Exception
     */
    public static Boolean isISO8601(String dateStr) {
        return parseISO8601(dateStr) != null;
    }

    /**
     * Parse the given String from ISO 8601 format to Date object
     *
     * @param dateStr
     * @return
     * @throws Exception
     */
    public static Date parseISO8601(String dateStr) {
        try {
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateStr);

            if (offsetDateTime != null) {
                return Date.from(offsetDateTime.toInstant());
            }
        } catch (DateTimeParseException dtpe) {
            log.error(dtpe.getMessage());
            return null;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }

        return null;
    }

    /**
     * Format the Date object to ISO 8601 Date String
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static String formatISO8601(Date date) {
        return OffsetDateTime.ofInstant(date.toInstant(), ZoneId.of("Asia/Kuala_Lumpur")).toZonedDateTime().format(formatter);
    }

    /**
     * Get current Date in ISO 8601 Date String
     *
     * @return
     */
    public static String nowISO8601() {
        return OffsetDateTime.ofInstant(Instant.now(), ZoneId.of("Asia/Kuala_Lumpur")).toZonedDateTime().format(formatter);
    }

    /**
     * Get only date (yyyy-MM-dd) from ISO 8601 Date String
     *
     * @param dateStr
     * @return
     */
    public static String extractDateFromISO8601(String dateStr) {
        try {
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateStr);
            if (offsetDateTime != null) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                return simpleDateFormat.format(Date.from(offsetDateTime.toInstant()));
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }

        return null;
    }

    /**
     * Get current Date with Simple Date Format
     *
     * @return
     */
    public static String nowMilliseconds() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date());
    }


    /**
     * Get the String format with Pattern given
     *
     * @param pattern the date format pattern, e.g. yyyyMMddHHmm
     * @param date    the date object
     * @return the date in String with pattern format or null if failed to format
     */
    public static String getFormatWithPattern(String pattern, Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        return null;
    }
}
