package com.example.demo.linkUtils.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToUtilDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String text) {
        if (!StringUtils.hasText(text)) {
            return null;
        }
        text = text.trim();
        Date data = null;
        for (String format : DateFormat.getFormats()) {
            if (format.length() == text.length()) {
                boolean success = true;
                try {
                    data = new SimpleDateFormat(format).parse(text);
                } catch (ParseException e) {
                    success = false;
                }
                if (success) {
                    break;
                }
            }
        }
        return data;
    }

}
