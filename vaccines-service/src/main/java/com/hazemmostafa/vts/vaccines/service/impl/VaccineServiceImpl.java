package com.hazemmostafa.vts.vaccines.service.impl;

import com.hazemmostafa.vts.vaccines.domain.Vaccine;
import com.hazemmostafa.vts.vaccines.dto.VaccineDTO;
import com.hazemmostafa.vts.vaccines.repository.VaccineRepository;
import com.hazemmostafa.vts.vaccines.service.VaccineService;
import com.hazemmostafa.vts.vaccines.service.mapper.VaccineMapper;
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
public class VaccineServiceImpl extends BaseServiceImpl implements VaccineService {
    private final Logger log = LoggerFactory.getLogger(VaccineServiceImpl.class);

    private final VaccineRepository vaccineRepository;

    private final VaccineMapper vaccineMapper;

    @Autowired
    public VaccineServiceImpl(VaccineRepository vaccineRepository, VaccineMapper vaccineMapper) {
        this.vaccineRepository = vaccineRepository;
        this.vaccineMapper = vaccineMapper;
    }

    @Override
    public VaccineDTO create(VaccineDTO vaccineDTO) {
        log.debug("Request to create a new vaccine: {}", vaccineDTO);
        Assert.notNull(vaccineDTO, "Vaccine mustn't be null");
        Vaccine vaccine = vaccineMapper.toEntity(vaccineDTO);
        vaccine = vaccineRepository.save(vaccine);
        VaccineDTO createdVaccineDTO = vaccineMapper.toDto(vaccine);
        log.debug("New vaccine created: {}", createdVaccineDTO);
        return createdVaccineDTO;
    }

    @Override
    public VaccineDTO getById(Long id) {
        log.debug("find vaccine by id: {}", id);
        Assert.notNull(id, "Vaccine ID mustn't be null");
        Optional<Vaccine> vaccineOptional = vaccineRepository.findById(id);
        Assert.notNull(vaccineOptional, "Vaccine ID is not valid");
        Assert.isTrue(vaccineOptional.isPresent(), "Vaccine ID is not valid");
        Vaccine vaccine = vaccineOptional.get();
        VaccineDTO vaccineDTO = vaccineMapper.toDto(vaccine);
        log.debug("find vaccine by id finished: {}", vaccineDTO);
        return vaccineDTO;
    }

    @Override
    public List<VaccineDTO> getAll() {
        log.debug("list all vaccines");
        List<Vaccine> vaccines = vaccineRepository.findAll();
        List<VaccineDTO> vaccineDTOList = new ArrayList<VaccineDTO>();
        for(Vaccine v: vaccines){
            VaccineDTO vaccineDTO = vaccineMapper.toDto(v);
            vaccineDTOList.add(vaccineDTO);
        }
        log.debug("list all vaccines finished: {}", vaccineDTOList);
        return vaccineDTOList;
    }
}
