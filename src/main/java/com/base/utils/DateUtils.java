package com.base.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DateUtils {
    public static String strDf = "dd-MM-yyyy HH:mm:ss";
    public static String strDf1 = "dd-MM-yyyy";
    public static String strDf2 = "yyyy-MM-dd";
    public static String strDf3 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static String strDf4 = "yyyyMMddHHmmss";

    public static long GetTime(Object input) {
        Timestamp date = (Timestamp) input;
        return date.getTime();
    }

    public static String Date2String(Date input, String strDf) {
        DateFormat df = new SimpleDateFormat(strDf);
        String strDate = df.format(input);
        return strDate;
    }

    public static LocalDateTime string2Date(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(strDf);
        LocalDateTime localDate = LocalDateTime.parse(date, formatter);
        return localDate;
    }

    public static LocalDateTime stringToLocalDateTime(String dateStr, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false); // Ngăn chặn chuyển đổi tự động
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateToLocalDateTime(date);
    }

    public static LocalDate string2DateEx(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(strDf1);
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }

    public static Date string2Date(String dateStr, String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            dateFormat.setLenient(false); // Ngăn chặn chuyển đổi tự động
            Date date = dateFormat.parse(dateStr);
            return date;
        } catch (ParseException ex) {
            return null;
        }
    }

    public static Date localDateToDate(long minusDays) {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDatePrevious = localDateTime.minusDays(minusDays);
        return Date.from(localDatePrevious.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String getCurrentDate() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        String strDate = df.format(LocalDateTime.now());
        return strDate;
    }

    public static String getCurrentDate01(LocalDateTime dateTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String strDate = df.format(dateTime);
        return strDate;
    }

    public static String getCurrentDate02(LocalDateTime dateTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String strDate = df.format(dateTime);
        return strDate;
    }

    public static String getCurrentDate03(LocalDateTime dateTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        String strDate = df.format(dateTime);
        return strDate;
    }

    public static Timestamp convertUtcTimeToLocal(Timestamp time) {
        Timestamp currentTime = new Timestamp(Instant.now().toEpochMilli());
        if (currentTime.after(new Timestamp(time.getTime() + TimeUnit.HOURS.toMillis(6)))) {
            return new Timestamp(time.getTime() + TimeUnit.HOURS.toMillis(7));
        }
        return time;
    }

    public static Date stringToDate(String str, String date_format) {
        DateFormat df = new SimpleDateFormat(date_format);
        Date d = null;
        try {
            d = df.parse(str);
        } catch (Exception e) {
        }
        return d;
    }

    public static Timestamp stringToTimestamp(String dateStr, String dateFormat) {
        Date date = stringToDate(dateStr, dateFormat);
        try {
            return new Timestamp(date.getTime());
        } catch (Exception e) {
            log.error("stringToTimestamp Error date ={}, dateFormat ={} ", dateStr, dateFormat);
        }
        return null;
    }

    public static String getCurrentDate(String dateFormat) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormat);
        String strDate = df.format(LocalDateTime.now());
        return strDate;
    }

    public static String timestampToString(Timestamp time, String format) {
        Instant instant = time.toInstant();
        SimpleDateFormat sdf = new SimpleDateFormat(strDf); // Định dạng bạn muốn
        String formattedDate = sdf.format(time);
        return formattedDate;
    }

    public static String stringToString(String dateStr, String format1, String format2) {
        Date date = stringToDate(dateStr, format1);
        String strDate = Date2String(date, format2);
        return strDate;
    }

    public static Date addDate(Date date, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        Date newDate = calendar.getTime();
        return newDate;
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static boolean isDate(String value, String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            dateFormat.setLenient(false); // Ngăn chặn chuyển đổi tự động
            Date date = dateFormat.parse(value);
            return true;
        } catch (Exception e) {

        }
        return false;
    }
}
