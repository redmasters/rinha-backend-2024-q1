package io.red.enums;

public enum TipoTransacaoEnum {

    DEBITO(1L, "D"),
    CREDITO(2L, "C");

    private Long id;
    private String tipo;

    TipoTransacaoEnum(Long id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }
}
