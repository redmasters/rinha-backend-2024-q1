package io.red.repositories;

import io.red.domain.Cliente;
import io.red.domain.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findTop10ByClienteOrderByRealizadaEmDesc(Cliente cliente);
    List<Transacao> findTop3ByClienteOrderByRealizadaEmDesc(Cliente cliente);
}