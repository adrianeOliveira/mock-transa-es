package com.br.adriane.guiabolso.mocktransacoes.utils;

import com.br.adriane.guiabolso.mocktransacoes.exceptions.TransactionValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntegerUtils {

    private static final Logger log = LoggerFactory.getLogger(IntegerUtils.class);

    private static final int MAX_VALUE_TRANSACTION = 9_999_999;
    private static final int MIN_VALUE_TRANSACTION = -9_999_999;

    private IntegerUtils() {
        throw new IllegalStateException("É uma classe utilitária");
    }

    public static int transactionListSize(int month, int firstDigit) {
        return month * firstDigit;
    }

    public static int findFirstDigit(final int number, int firstDigit) {
        if(number > 0) {
            if(number/10 == 0) {
                firstDigit = number;
            }
            firstDigit = findFirstDigit(number/10, firstDigit);
        }
        return firstDigit;
    }

    public static int generateValue(final int idNumber,
                                    final int index,
                                    final int month)
            throws TransactionValueException {

        final int transactionValue =
                Integer.parseInt(String.format("%s%s%s", idNumber, index, month));

        if (transactionValue > MAX_VALUE_TRANSACTION
                || transactionValue < MIN_VALUE_TRANSACTION) {
            log.info("M=generateValue, E=Valor da transação está fora do intervalo de -9.999.999 a 9.999.999");
            throw new TransactionValueException("Valor da transação está fora do intervalo de -9.999.999 a 9.999.999");
        }

        return transactionValue;
    }
}
