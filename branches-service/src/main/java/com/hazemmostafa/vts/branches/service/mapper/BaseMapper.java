package com.hazemmostafa.vts.branches.service.mapper;

import com.hazemmostafa.vts.branches.domain.BaseEntity;
import com.hazemmostafa.vts.branches.dto.BaseDTO;

import java.util.List;

public interface BaseMapper<E extends BaseEntity, D extends BaseDTO> {
    E toEntity(D dto);

    List<E> toEntity(List<D> dto);

    D toDto(E entity);

    List<D> toDto(List<E> entity);
}
