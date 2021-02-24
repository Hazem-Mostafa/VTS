package com.hazemmostafa.vts.vaccines.service.mapper;


import com.hazemmostafa.vts.vaccines.domain.BaseEntity;
import com.hazemmostafa.vts.vaccines.dto.BaseDTO;

public interface BaseMapper<E extends BaseEntity, D extends BaseDTO> {
    E toEntity(D dto);
    D toDto(E entity);
}
