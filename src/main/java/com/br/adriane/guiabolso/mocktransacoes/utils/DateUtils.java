package com.br.adriane.guiabolso.mocktransacoes.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

public class DateUtils {

    private DateUtils() {
        throw new IllegalStateException("É uma classe utilitária");
    }

    public static long generateDate(final int month, final int year) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);

        final int maxDaysAtMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        final LocalDate startMonth = LocalDate.of(year, month, 1);
        final LocalDate endMonth = LocalDate.of(year, month, maxDaysAtMonth);
        final long randomEpochDay = ThreadLocalRandom
                .current()
                .nextLong(startMonth.toEpochDay(), endMonth.toEpochDay());

        final LocalDate randomLocalDate = LocalDate.ofEpochDay(randomEpochDay);

        return Timestamp.valueOf(
                LocalDateTime.of(randomLocalDate, LocalTime.MIDNIGHT))
                .getTime();
    }
}
