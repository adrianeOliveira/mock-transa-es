package com.br.adriane.guiabolso.mocktransacoes.service.impl;

import com.br.adriane.guiabolso.mocktransacoes.exceptions.TransactionValueException;
import com.br.adriane.guiabolso.mocktransacoes.rest.response.Transaction;
import com.br.adriane.guiabolso.mocktransacoes.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.br.adriane.guiabolso.mocktransacoes.utils.DateUtils.generateDate;
import static com.br.adriane.guiabolso.mocktransacoes.utils.IntegerUtils.findFirstDigit;
import static com.br.adriane.guiabolso.mocktransacoes.utils.IntegerUtils.generateValue;
import static com.br.adriane.guiabolso.mocktransacoes.utils.IntegerUtils.transactionListSize;
import static com.br.adriane.guiabolso.mocktransacoes.utils.StringUtils.generateDescription;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    public List<Transaction> listTransactions(final int idUser,
                                              final int year,
                                              final int month)
            throws TransactionValueException {
        log.info("M=listTrasactions, iduser={}, year={}, month={}", idUser, year, month);
        List<Transaction> finalListOfTransactions = new ArrayList<>();

        final int transactionListSize = transactionListSize(month,
                findFirstDigit(idUser, 0));

        log.info("M=listTrasactions, I=Gerando transações");
        for (int index = 0; index<transactionListSize; index++) {
            final Transaction transaction = new Transaction();

            transaction.setDate(generateDate(month, year));
            transaction.setDescription(generateDescription());
            transaction.setValue(generateValue(idUser, index, month));

            finalListOfTransactions.add(transaction);
        }
        log.info("M=listTrasactions, I= lista gerada com sucesso, total de {} transações", transactionListSize);
        return finalListOfTransactions;
    }

}
