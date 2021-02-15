package com.br.adriane.guiabolso.mocktransacoes.utils;

public class IntegerUtils {

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

    public static int generateValue(final int idNumber, final int index, final int month) {
        return Integer.parseInt(String.format("%s%s%s", idNumber, index, month));
    }
}
