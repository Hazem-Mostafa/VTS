package com.hazemmostafa.vts.clients.service.mapper;


import com.hazemmostafa.vts.clients.domain.BaseEntity;
import com.hazemmostafa.vts.clients.dto.BaseDTO;

public interface BaseMapper<E extends BaseEntity, D extends BaseDTO> {
    E toEntity(D dto);
    D toDto(E entity);
}
