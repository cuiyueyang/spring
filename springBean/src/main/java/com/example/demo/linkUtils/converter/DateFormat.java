package com.example.demo.linkUtils.converter;

public class DateFormat {

    private DateFormat() {}


    private static final String[] formats = new String[]{
            "yyyy-MM-dd HH:mm:ss",
            "yyyy/MM/dd HH:mm:ss",
            "yyyyMMdd HH:mm:ss",
            "yyyy-MM-dd HH:mm",
            "yyyy/MM/dd HH:mm",
            "yyyyMMdd HH:mm",
            "yyyy-MM-dd HH",
            "yyyy/MM/dd HH",
            "yyyyMMdd HH",
            "yyyy-MM-dd",
            "yyyy/MM/dd",
            "yyyyMMdd",
            "yyyy-MM",
            "yyyy/MM",
            "yyyyMM",
            "yyyy",
            "HH:mm:ss",
            "HH:mm",
    };

    public static String[] getFormats() {
        return formats;
    }

}
