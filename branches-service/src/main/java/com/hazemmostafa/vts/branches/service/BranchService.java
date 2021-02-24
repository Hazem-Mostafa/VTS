package com.hazemmostafa.vts.branches.service;

import com.hazemmostafa.vts.branches.dto.BranchDTO;
import com.hazemmostafa.vts.branches.dto.BranchStoreDTO;
import com.hazemmostafa.vts.branches.dto.BranchVisitDTO;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface BranchService extends BaseService {

    BranchDTO createBranch(BranchDTO branchDTO);

    BranchDTO getById(Long id);

    List<BranchDTO> getAll();


    BranchStoreDTO createStore(BranchStoreDTO branchStoreDTO);

    List<BranchDTO> getByVaccineId(Long vaccineId);

    void deductVaccineQuantity(Long branchId, Long vaccineId, Long quantity);

    void addVaccineQuantity(Long branchId, Long vaccineId, Long quantity);


    BranchVisitDTO createVisit(BranchVisitDTO branchVisitDTO);

    Optional<BranchVisitDTO> getVisitByBranchIdAndVisitTime(Long branchId, String timeSlotStr);

    List<LocalTime> getAvailableTimeById(Long id);

    BranchDTO getVisitsByBranchIdAndVisitStatusAndVisitDateRange(Long branchId, String visitStatusStr, String fromDateStr, String toDateStr);

    List<BranchVisitDTO> getAllVisitsByVisitStatusAndVisitDateRange(String visitStatusStr, String fromDateStr, String toDateStr);
}
