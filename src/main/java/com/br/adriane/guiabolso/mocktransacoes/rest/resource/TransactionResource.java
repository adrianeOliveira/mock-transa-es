package com.br.adriane.guiabolso.mocktransacoes.rest.resource;

import com.br.adriane.guiabolso.mocktransacoes.exceptions.TransactionValueException;
import com.br.adriane.guiabolso.mocktransacoes.rest.response.Transaction;
import com.br.adriane.guiabolso.mocktransacoes.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/{id}/transacoes")
public class TransactionResource {

    private final Logger log = LoggerFactory.getLogger(TransactionResource.class);

    private final TransactionService transactionService;

    public TransactionResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(value = "/{ano}/{mes}")
    public List<Transaction> listTransactionsWithParameters(@PathVariable("ano") final int year,
                                                            @PathVariable("mes")final int month,
                                                            @PathVariable("id")final int idUser) {
        log.info("M=listTransactionsWithParameters, I=Iniciada requisição para listagem de transações");
        try {
            return transactionService.listTransactions(idUser,year, month);
        } catch (TransactionValueException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
