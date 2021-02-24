package com.hazemmostafa.vts.clients.web;

import com.hazemmostafa.vts.clients.dto.ClientDTO;
import com.hazemmostafa.vts.clients.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientResource implements BaseResource {

    private final ClientService clientService;

    @Autowired
    public ClientResource(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping("/clients/{clientId}")
    public ClientDTO getClientById(@PathVariable("clientId") long clientId) {
        return clientService.getById(clientId);
    }

    @GetMapping("/clients")
    public List<ClientDTO> getAllClients() {
        return clientService.getAll();
    }

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO createClient(@RequestBody ClientDTO clientDTO){
        return clientService.create(clientDTO);
    }
}
