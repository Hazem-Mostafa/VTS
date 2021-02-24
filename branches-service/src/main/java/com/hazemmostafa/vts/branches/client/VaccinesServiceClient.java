package com.hazemmostafa.vts.branches.client;

import com.hazemmostafa.vts.vaccines.dto.VaccineDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "vaccines-service")
public interface VaccinesServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/vaccines/{vaccineId}")
    VaccineDTO getVaccineById(@PathVariable("vaccineId") long vaccineId);
}
