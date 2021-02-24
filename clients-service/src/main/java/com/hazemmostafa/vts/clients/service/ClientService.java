package com.hazemmostafa.vts.clients.service;

import com.hazemmostafa.vts.clients.dto.ClientDTO;

import java.util.List;

public interface ClientService extends BaseService {

    ClientDTO create(ClientDTO clientDTO);

    ClientDTO getById(Long id);

    List<ClientDTO> getAll();
}
