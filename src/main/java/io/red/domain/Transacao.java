package io.red.domain;

import io.red.enums.TipoTransacaoEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoTransacaoEnum tipo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valor")
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "realizada_em")
    private LocalDateTime realizadaEm;

public Transacao() {
    }

    public Transacao(Long id, TipoTransacaoEnum tipo, String descricao, Double valor, Cliente cliente, LocalDateTime realizadaEm) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
        this.cliente = cliente;
        this.realizadaEm = realizadaEm;
    }

    public Long getId() {
        return id;
    }

    public TipoTransacaoEnum getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getValor() {
        return valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getRealizadaEm() {
        return realizadaEm;
    }
}
