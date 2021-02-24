package com.hazemmostafa.vts.branches.repository;

import com.hazemmostafa.vts.branches.domain.BranchStore;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchStoreRepository extends BaseRepository<BranchStore, Long> {
    List<BranchStore> findByBranchId(Long branchId);

    List<BranchStore> findByVaccineId(Long vaccineId);

    BranchStore findByBranchIdAndVaccineId(Long branchId, Long vaccineId);
}
