package io.red.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "limite")
    private Double limite;

    @Column(name = "saldo")
    private Double saldo;

    public Cliente() {
    }

    public Cliente(Long id, String nome, Double limite, Double saldo) {
        this.id = id;
        this.nome = nome;
        this.limite = limite;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getLimite() {
        return limite;
    }

    public Double getSaldo() {
        return saldo;
    }
}
