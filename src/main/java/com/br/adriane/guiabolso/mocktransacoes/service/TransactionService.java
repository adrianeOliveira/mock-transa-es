package com.br.adriane.guiabolso.mocktransacoes.service;

import com.br.adriane.guiabolso.mocktransacoes.rest.response.Transaction;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TransactionService {

    public List<Transaction> listTransactions(final int idUser, final int year, final int month) {
        final String description = generateDescription();
        final long date = generateDate(month, year);
        final Transaction transaction = new Transaction();
        transaction.setDate(date);
        transaction.setDescription(description);
        return Arrays.asList(transaction);
    }

    private String generateDescription() {
        final String benedictCumberbatch = "benedictcumberbatch";
        final int length = benedictCumberbatch.length();
        final Random random = new Random();
        final StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(length);
            char randomChar = benedictCumberbatch.charAt(index);
            builder.append(randomChar);
        }

        final String firstname = builder.substring(0, 7);
        final String lastname = builder.substring(8, 18);

        return firstname+" "+lastname;
    }

    private long generateDate(final int month, final int year) {
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
