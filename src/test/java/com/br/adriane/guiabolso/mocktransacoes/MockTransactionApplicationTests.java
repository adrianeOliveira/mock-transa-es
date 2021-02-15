package com.br.adriane.guiabolso.mocktransacoes;

import com.br.adriane.guiabolso.mocktransacoes.exceptions.IdUserValueException;
import com.br.adriane.guiabolso.mocktransacoes.exceptions.TransactionValueException;
import com.br.adriane.guiabolso.mocktransacoes.rest.response.Transaction;
import com.br.adriane.guiabolso.mocktransacoes.service.TransactionService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class MockTransactionApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(MockTransactionApplicationTests.class);

	private final TransactionService transactionService;

	@Autowired
	MockTransactionApplicationTests(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	/*
	* TODO
	* erros de data devem gerar retorno http 4xx
	* gerar descrições mais legíveis
	* */

	@Test
	void assertThatSameResultAlwaysWithSameParameters()
			throws TransactionValueException, IdUserValueException {
		final int idUser = 1995;
		final int year = 2021;
		final int month = 2;

		final List<Transaction> transactionList = transactionService.listTransactions(idUser, year, month);
		final List<Transaction> transactionListCopy = transactionService.listTransactions(idUser, year, month);

		final var first = transactionList.get(0);
		final var copyFirst = transactionListCopy.get(0);

		// FALHANDO: dado dois conjuntos de dados iguais, as respostas devem ser as mesmas
		assertThat(copyFirst.getDate(), Matchers.equalTo(first.getDate()));
		assertThat(copyFirst.getValue(), Matchers.equalTo(first.getValue()));
		assertThat(copyFirst.getDescription(), Matchers.equalTo(first.getDescription()));
	}

	@Test
	void assertThatUserIdIsntCorrect()
			throws IdUserValueException, TransactionValueException {
		final int idUser = 999;
		final int year = LocalDateTime.now().getYear();
		final int month = LocalDateTime.now().getMonthValue();

		assertThrows(IdUserValueException.class, () ->
				transactionService.listTransactions(idUser, year, month));
	}

}
