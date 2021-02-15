package com.br.adriane.guiabolso.mocktransacoes.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;

public class DateUtils {

    private DateUtils() {
        throw new IllegalStateException("É uma classe utilitária");
    }

    public static long generateDate(final int month, final int year, int index) {
        final LocalDate startMonth = LocalDate.now().withYear(year).withMonth(month);

        final long randomEpochDay = ThreadLocalRandom
                .current()
                .nextLong(startMonth.toEpochDay());

        final LocalDate randomLocalDate = LocalDate.ofEpochDay(randomEpochDay);

        return Timestamp.valueOf(
                LocalDateTime.of(randomLocalDate, LocalTime.MIDNIGHT))
                .getTime();
    }
}
