package com.hazemmostafa.vts.branches.client;

import com.hazemmostafa.vts.clients.dto.ClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "clients-service")
public interface ClientsServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/clients/{clientId}")
    ClientDTO getClientById(@PathVariable("clientId") long clientId);
}
