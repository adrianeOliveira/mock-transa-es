package com.br.adriane.guiabolso.mocktransacoes.service.impl;

import com.br.adriane.guiabolso.mocktransacoes.exceptions.IdUserValueException;
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

    private static final int MAX_VALUE_TRANSACTION = 9_999_999;
    private static final int MIN_VALUE_TRANSACTION = -9_999_999;

    private static final int MAX_VALUE_ID_USER = 100_000;
    private static final int MIN_VALUE_ID_USER = 1000;

    public List<Transaction> listTransactions(final int idUser, final int year, final int month)
            throws TransactionValueException, IdUserValueException {
        log.info("M=listTrasactions, iduser={}, year={}, month={}", idUser, year, month);
        validadeIdUser(idUser);
        final List<Transaction> finalListOfTransactions = new ArrayList<>();
        final int transactionListSize = transactionListSize(month, findFirstDigit(idUser, 0));

        log.info("M=listTrasactions, I=Gerando transações");

        for (int index = 1; index <= transactionListSize; index++) {
            final Transaction transaction = generateTransaction(idUser, year, month, index);

            finalListOfTransactions.add(transaction);
        }

        log.info("M=listTrasactions, I= lista gerada com sucesso, total de {} transações", transactionListSize);

        return finalListOfTransactions;
    }

    private Transaction generateTransaction(int idUser, int year, int month, int index) throws TransactionValueException {
        final Transaction transaction = new Transaction();
        final int transactionValue = generateValue(idUser, index, month);

        validateTransactionValue(transactionValue);

        transaction.setValue(transactionValue);
        transaction.setDate(generateDate(month, year, index));
        transaction.setDescription(generateDescription());
        return transaction;
    }

    private void validateTransactionValue(int transactionValue) throws TransactionValueException {
        if (transactionValue > MAX_VALUE_TRANSACTION
                || transactionValue < MIN_VALUE_TRANSACTION) {
            log.info("M=validateTransactionValue, E=Valor da transação está fora do intervalo de -9.999.999 a 9.999.999");
            throw new TransactionValueException("Valor da transação está fora do intervalo de -9.999.999 a 9.999.999");
        }
    }

    private void validadeIdUser(int idUser) throws IdUserValueException {
        if (idUser < MIN_VALUE_ID_USER || idUser > MAX_VALUE_ID_USER){
            log.error("M=validadeIdUser, E=Id do Usuário deve ser maior que 1000 e menor que 100.000, id = {}", idUser);
            throw new IdUserValueException("Id do Usuário deve ser maior que 1000 e menor que 100.000, id = " + idUser);
        }
    }
}
