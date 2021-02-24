package com.hazemmostafa.vts.branches.service.mapper;

import com.hazemmostafa.vts.branches.domain.Branch;
import com.hazemmostafa.vts.branches.dto.BranchDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BranchStoreMapper.class, BranchVisitMapper.class})
public interface BranchMapper extends BaseMapper<Branch, BranchDTO> {
}
