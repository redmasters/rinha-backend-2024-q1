package io.red.controllers.responses;

import java.time.LocalDateTime;

public record SaldoResponse(
        Double total,
        LocalDateTime data_extrato,
        Double limite
) {
}
