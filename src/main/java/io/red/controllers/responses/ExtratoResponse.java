package io.red.controllers.responses;

import java.util.List;

public record ExtratoResponse(
        SaldoResponse saldo,
        List<UltimasTransacoesResponse> ultimasTransacoes
) {
}
