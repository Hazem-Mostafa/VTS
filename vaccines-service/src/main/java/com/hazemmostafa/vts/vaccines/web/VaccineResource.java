package com.hazemmostafa.vts.vaccines.web;

import com.hazemmostafa.vts.vaccines.dto.VaccineDTO;
import com.hazemmostafa.vts.vaccines.service.VaccineService;
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
public class VaccineResource implements BaseResource {

    private final VaccineService vaccineService;

    @Autowired
    public VaccineResource(VaccineService vaccineService){
        this.vaccineService = vaccineService;
    }

    @GetMapping("/vaccines/{vaccineId}")
    public VaccineDTO getVaccineById(@PathVariable("vaccineId") long vaccineId) {
        return vaccineService.getById(vaccineId);
    }

    @GetMapping("/vaccines")
    public List<VaccineDTO> getAllVaccines() {
        return vaccineService.getAll();
    }

    @PostMapping("/vaccines")
    @ResponseStatus(HttpStatus.CREATED)
    public VaccineDTO createVaccine(@RequestBody VaccineDTO vaccineDTO){
        return vaccineService.create(vaccineDTO);
    }
}
