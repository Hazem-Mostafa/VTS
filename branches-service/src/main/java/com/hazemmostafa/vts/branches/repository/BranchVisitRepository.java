package com.hazemmostafa.vts.branches.repository;

import com.hazemmostafa.vts.branches.domain.BranchVisit;
import com.hazemmostafa.vts.branches.domain.enumeration.VisitStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BranchVisitRepository extends BaseRepository<BranchVisit, Long> {

    List<BranchVisit> getByBranchId(Long branchId);

    BranchVisit getByBranchIdAndVisitDate(Long branchId, LocalDateTime timeSlot);

    List<BranchVisit> getByBranchIdAndStatus(Long branchId, VisitStatus visitStatus);

    @Query("SELECT v FROM BranchVisit v WHERE v.branch.id=:branchId AND v.status='COMPLETED' AND v.visitDate >= :fromDate AND v.visitDate <= :toDate")
    List<BranchVisit> getCompletedByBranchIdAndVisitDateRange(@Param("branchId") Long branchId, @Param("fromDate")LocalDateTime fromDate, @Param("toDate")LocalDateTime toDate);

    @Query("SELECT v FROM BranchVisit v WHERE v.status=:visitStatus AND v.visitDate >= :fromDate AND v.visitDate <= :toDate")
    List<BranchVisit> getByVisitStatusAndVisitDateRange(VisitStatus visitStatus, LocalDateTime fromDate, LocalDateTime toDate);
}
