package com.hazemmostafa.vts.vaccines.service;

import com.hazemmostafa.vts.vaccines.dto.VaccineDTO;

import java.util.List;

public interface VaccineService extends BaseService {

    VaccineDTO create(VaccineDTO vaccineDTO);

    VaccineDTO getById(Long id);

    List<VaccineDTO> getAll();
}
