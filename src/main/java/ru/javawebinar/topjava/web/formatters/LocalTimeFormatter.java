package ru.javawebinar.topjava.web.formatters;

import org.springframework.format.Formatter;
import org.springframework.lang.Nullable;

import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalTimeFormatter implements Formatter<LocalTime> {
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_TIME;

    @Override
    public LocalTime parse(String s, @Nullable Locale locale) throws ParseException {
        if (s.length() == 0) {
            return null;
        }
        return LocalTime.parse(s, timeFormatter);
    }

    @Override
    public String print(@Nullable LocalTime localTime, @Nullable Locale locale) {
        if (localTime == null) {
            return "";
        }
        return localTime.format(timeFormatter);
    }
}
