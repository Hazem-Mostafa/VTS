package com.hazemmostafa.vts.branches.repository;

import com.hazemmostafa.vts.branches.domain.Branch;
import com.hazemmostafa.vts.branches.domain.enumeration.VisitStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface BranchRepository extends BaseRepository<Branch, Long> {
    @Query("SELECT DISTINCT b FROM Branch b LEFT JOIN BranchVisit v ON b.id = v.branch.id WHERE b.id = :branchId AND" +
            " (:visitStatus is null or v.status = :visitStatus) AND (:fromDate is null or v.visitDate >= :fromDate) AND" +
            " (:toDate is null or v.visitDate <= :toDate)")
    Branch getByIdAndVisitStatusAndVisitDateRange(
            @Param("branchId") Long branchId, @Param("visitStatus") VisitStatus visitStatus,
            @Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate);
}
