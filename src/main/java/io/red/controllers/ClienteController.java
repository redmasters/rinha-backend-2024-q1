package io.red.controllers;

import io.red.controllers.responses.ExtratoResponse;
import io.red.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/{id}/extrato")
    @ResponseStatus(HttpStatus.OK)
    public ExtratoResponse extrato(@PathVariable(value = "id") Long idCliente) {
        return clienteService.extrato(idCliente);
    }
}
