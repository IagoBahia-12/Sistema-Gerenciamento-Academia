import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class PagamentoTest {

    @Test
    void deveArmazenarValor() {
        Pagamento pagamento = new Pagamento();

        pagamento.setValor(150.00);

        assertEquals(150.00, pagamento.getValor(), 0.001);
    }

    @Test
    void deveArmazenarFormaPagamento() {
        Pagamento pagamento = new Pagamento();

        pagamento.setForma_pagamento("Cartão de crédito");

        assertEquals("Cartão de crédito", pagamento.getForma_pagamento());
    }

    @Test
    void deveArmazenarDataPagamento() {
        Pagamento pagamento = new Pagamento();
        LocalDate data = LocalDate.of(2026, 11, 18);

        pagamento.setData_pag(data);

        assertEquals(data, pagamento.getData_pag());
    }

    @Test
    void deveRegistrarPagamento() {
        Pagamento pagamento = new Pagamento();

        pagamento.registrar_pag(200.00, "Pix");

        assertEquals(200.00, pagamento.getValor(), 0.001);
        assertEquals("Pix", pagamento.getForma_pagamento());
        assertEquals(LocalDate.now(), pagamento.getData_pag());
    }

    @Test
    void dataDeveComecarNula() {
        Pagamento pagamento = new Pagamento();

        assertNull(pagamento.getData_pag());
    }

    @Test
    void valorDeveComecarZero() {
        Pagamento pagamento = new Pagamento();

        assertEquals(0.0, pagamento.getValor(), 0.001);
    }

    @Test
    void formaPagamentoDeveComecarNula() {
        Pagamento pagamento = new Pagamento();

        assertNull(pagamento.getForma_pagamento());
    }
}

