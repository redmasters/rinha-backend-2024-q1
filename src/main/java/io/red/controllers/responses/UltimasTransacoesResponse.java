package io.red.controllers.responses;

import io.red.enums.TipoTransacaoEnum;

import java.time.LocalDateTime;

public record UltimasTransacoesResponse(
        Double valor,
        TipoTransacaoEnum tipo,
        String descricao,
        LocalDateTime realizada_em
) {
}
