package com.hazemmostafa.vts.clients.service.impl;

import com.hazemmostafa.vts.clients.domain.Client;
import com.hazemmostafa.vts.clients.dto.ClientDTO;
import com.hazemmostafa.vts.clients.repository.ClientRepository;
import com.hazemmostafa.vts.clients.service.ClientService;
import com.hazemmostafa.vts.clients.service.mapper.ClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientServiceImpl extends BaseServiceImpl implements ClientService {
    private final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public ClientDTO create(ClientDTO clientDTO){
        log.debug("Request to create a new client: {}", clientDTO);
        Assert.notNull(clientDTO, "Client mustn't be null");
        Client client = clientMapper.toEntity(clientDTO);
        client = clientRepository.save(client);
        ClientDTO createdClientDTO = clientMapper.toDto(client);
        log.debug("New client created: {}", createdClientDTO);
        return createdClientDTO;
    }

    @Override
    public ClientDTO getById(Long id){
        log.debug("find client by id: {}", id);
        Assert.notNull(id, "Client ID mustn't be null");
        Optional<Client> clientOptional = clientRepository.findById(id);
        Assert.notNull(clientOptional, "Client ID is not valid");
        Assert.isTrue(clientOptional.isPresent(), "Client ID is not valid");
        Client client = clientOptional.get();
        ClientDTO clientDTO = clientMapper.toDto(client);
        log.debug("find client by id finished: {}", clientDTO);
        return clientDTO;
    }

    @Override
    public List<ClientDTO> getAll(){
        log.debug("list all clients");
        List<Client> clients = clientRepository.findAll();
        List<ClientDTO> clientsDTO = new ArrayList<ClientDTO>();
        for(Client c:clients){
            ClientDTO clientDTO = clientMapper.toDto(c);
            clientsDTO.add(clientDTO);
        }
        log.debug("list all clients finished: {}", clientsDTO);
        return clientsDTO;
    }
}
