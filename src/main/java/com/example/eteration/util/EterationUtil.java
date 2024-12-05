package com.example.eteration.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EterationUtil {

    private EterationUtil(){}

    public static boolean isNullOrEmptyObject(Object obj) {
        return (obj == null || obj == "");
    }

    public static final String formatLocalDateTimeAsString(LocalDateTime localDateTime, String dateFormat){
        var formatter = DateTimeFormatter.ofPattern(dateFormat);
        return localDateTime.format(formatter);
    }

}
