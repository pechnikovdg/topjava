package ru.javawebinar.topjava.web.formatters;

import org.springframework.format.Formatter;
import org.springframework.lang.Nullable;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateFormatter implements Formatter<LocalDate> {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;

    @Override
    public LocalDate parse(String s, @Nullable Locale locale) throws ParseException {
        if (s.length() == 0) {
            return null;
        }
        return LocalDate.parse(s, dateFormatter);
    }

    @Override
    public String print(@Nullable LocalDate localDate, Locale locale) {
        if (localDate == null) {
            return "";
        }
        return localDate.format(dateFormatter);
    }
}
