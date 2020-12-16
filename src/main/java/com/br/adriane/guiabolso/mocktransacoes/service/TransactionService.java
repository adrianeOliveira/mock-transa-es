package com.br.adriane.guiabolso.mocktransacoes.service;

import com.br.adriane.guiabolso.mocktransacoes.exceptions.TransactionValueException;
import com.br.adriane.guiabolso.mocktransacoes.rest.response.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> listTransactions(final int idUser, final int year, final int month) throws TransactionValueException;
}
