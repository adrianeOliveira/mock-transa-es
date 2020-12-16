package com.br.adriane.guiabolso.mocktransacoes.service;

import com.br.adriane.guiabolso.mocktransacoes.rest.response.Transaction;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TransactionService {

    public List<Transaction> listTransactions(final int idUser, final int year, final int month) {
        List<Transaction> finalListOfTransactions = new ArrayList<>();
        final int transactionListSize = transactionListSize(month, findFirstDigit(idUser, 0));

        for (int index = 0; index<transactionListSize; index++) {
            final Transaction transaction = new Transaction();
            transaction.setDate(generateDate(month, year));
            transaction.setDescription(generateDescription());
            transaction.setValue(generateValue(idUser, index, month));
            finalListOfTransactions.add(transaction);
        }

        return finalListOfTransactions;
    }

    private int transactionListSize(int month, int firstDigit) {
        return month * firstDigit;
    }

    private int findFirstDigit(final int idUser, int firstDigit) {
        if(idUser > 0) {
            if(idUser/10 == 0) {
                firstDigit = idUser;
            }
            firstDigit = findFirstDigit(idUser/10, firstDigit);
        }
        return firstDigit;
    }

    private Integer generateValue(final int idUser, final int index, final int month){
        return Integer.valueOf(String.format("%s%s%s", idUser,index,month));
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
