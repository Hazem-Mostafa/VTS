package com.hazemmostafa.vts.branches.service.mapper;

import com.hazemmostafa.vts.branches.domain.BranchStore;
import com.hazemmostafa.vts.branches.domain.BranchVisit;
import com.hazemmostafa.vts.branches.dto.BranchStoreDTO;
import com.hazemmostafa.vts.branches.dto.BranchVisitDTO;
import com.hazemmostafa.vts.clients.dto.ClientDTO;
import com.hazemmostafa.vts.vaccines.dto.VaccineDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BranchVisitMapper extends BaseMapper<BranchVisit, BranchVisitDTO> {
    @Mapping(target = "branch.id", source = "branchId")
    @Mapping(target = "vaccineId", source = "vaccineDTO.id")
    @Mapping(target = "clientId", source = "clientDTO.id")
    BranchVisit toEntity(BranchVisitDTO dto);

    @Mapping(target = "branchId", source = "branch.id")
    @Mapping(target = "vaccineDTO.id", source = "vaccineId")
    @Mapping(target = "clientDTO.id", source = "clientId")
    BranchVisitDTO toDto(BranchVisit entity);
}
