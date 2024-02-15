package io.red.service;

import io.red.controllers.responses.ExtratoResponse;
import io.red.controllers.responses.SaldoResponse;
import io.red.controllers.responses.UltimasTransacoesResponse;
import io.red.repositories.ClienteRepository;
import io.red.repositories.TransacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClienteService {
    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);
    private final ClienteRepository clienteRepository;
    private final TransacaoRepository transacaoRepository;

    public ClienteService(ClienteRepository clienteRepository, TransacaoRepository transacaoRepository) {
        this.clienteRepository = clienteRepository;
        this.transacaoRepository = transacaoRepository;
    }


    public ExtratoResponse extrato(Long idCliente) {
        logger.info("Buscando extrato do cliente com id {}", idCliente);
        var cliente = clienteRepository.findById(idCliente).
                orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        var transacaoList = transacaoRepository.findTop10ByClienteOrderByRealizadaEmDesc(cliente);

        var saldo = new SaldoResponse(
                cliente.getSaldo(),
                LocalDateTime.now(),
                cliente.getLimite()
        );

        var transacoes = transacaoList.stream()
                .map(transacao -> new UltimasTransacoesResponse(
                        transacao.getValor(),
                        transacao.getTipo(),
                        transacao.getDescricao(),
                        transacao.getRealizadaEm()
                ))
                .toList();


        return new ExtratoResponse(
                saldo,
                transacoes
        );
    }
}
