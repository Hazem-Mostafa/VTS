package com.hazemmostafa.vts.branches.service.mapper;

import com.hazemmostafa.vts.branches.domain.BranchStore;
import com.hazemmostafa.vts.branches.dto.BranchStoreDTO;
import com.hazemmostafa.vts.vaccines.dto.VaccineDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BranchStoreMapper extends BaseMapper<BranchStore, BranchStoreDTO> {

    @Mapping(target = "branch.id", source = "branchId")
    @Mapping(target = "vaccineId", source = "vaccineDTO.id")
    BranchStore toEntity(BranchStoreDTO dto);

    @Mapping(target = "branchId", source = "branch.id")
    @Mapping(target = "vaccineDTO.id", source = "vaccineId")
    BranchStoreDTO toDto(BranchStore entity);
}
