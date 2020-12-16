package com.br.adriane.guiabolso.mocktransacoes.rest.resource;

import com.br.adriane.guiabolso.mocktransacoes.rest.response.Transaction;
import com.br.adriane.guiabolso.mocktransacoes.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/{id}/transacoes")
public class TransactionResource {

    private final TransactionService transactionService;

    public TransactionResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(value = "/{ano}/{mes}")
    public List<Transaction> listTransactionsWithParameters(@PathVariable("ano") final int year,
                                                            @PathVariable("mes")final int month,
                                                            @PathVariable("id")final int idUser) {
        return transactionService.listTransactions(idUser,year, month);
    }
}
