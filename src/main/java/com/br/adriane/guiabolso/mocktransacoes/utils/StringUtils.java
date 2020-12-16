package com.br.adriane.guiabolso.mocktransacoes.utils;

import java.util.Random;

public class StringUtils {

    private static final Random random = new Random();

    private StringUtils() {
        throw new IllegalStateException("É uma classe utilitária");
    }

    public static String generateDescription() {
        final String benedictCumberbatch = "benedictcumberbatch";
        final int length = benedictCumberbatch.length();
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
}
