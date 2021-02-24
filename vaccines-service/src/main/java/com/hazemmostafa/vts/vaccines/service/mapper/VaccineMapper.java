package com.hazemmostafa.vts.vaccines.service.mapper;

import com.hazemmostafa.vts.vaccines.domain.Vaccine;
import com.hazemmostafa.vts.vaccines.dto.VaccineDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VaccineMapper extends BaseMapper<Vaccine, VaccineDTO> {
}
