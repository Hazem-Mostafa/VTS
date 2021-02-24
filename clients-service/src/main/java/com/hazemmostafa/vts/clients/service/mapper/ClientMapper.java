package com.hazemmostafa.vts.clients.service.mapper;

import com.hazemmostafa.vts.clients.domain.Client;
import com.hazemmostafa.vts.clients.dto.ClientDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper extends BaseMapper<Client, ClientDTO> {
}
