package com.hazemmostafa.vts.branches.web;

import com.hazemmostafa.vts.branches.dto.BranchDTO;
import com.hazemmostafa.vts.branches.dto.BranchStoreDTO;
import com.hazemmostafa.vts.branches.dto.BranchVisitDTO;
import com.hazemmostafa.vts.branches.service.BranchService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
public class BranchResource implements BaseResource {

    private final BranchService branchService;

    @Autowired
    public BranchResource(BranchService branchService) {
        this.branchService = branchService;
    }

    @Operation(summary = "Create a new branch")
    @PostMapping("/branches")
    @ResponseStatus(HttpStatus.CREATED)
    public BranchDTO createBranch(@RequestBody BranchDTO branchDTO) {
        return branchService.createBranch(branchDTO);
    }

    @Operation(summary = "List all branches")
    @GetMapping("/branches")
    public List<BranchDTO> getAllBranches() {
        return branchService.getAll();
    }

    @Operation(summary = "Get branch by ID")
    @GetMapping("/branches/{branchId}")
    public BranchDTO getByBranchId(@PathVariable("branchId") Long branchId) {
        return branchService.getById(branchId);
    }

    @Operation(summary = "Create a new branch store")
    @PostMapping("/branches/store")
    @ResponseStatus(HttpStatus.CREATED)
    public BranchStoreDTO createStore(@RequestBody BranchStoreDTO branchStoreDTO) {
        return branchService.createStore(branchStoreDTO);
    }

    @Operation(summary = "Get all branches by vaccine id")
    @GetMapping("/branches/vaccine/{vaccineId}")
    public List<BranchDTO> getByVaccineId(@PathVariable("vaccineId") Long vaccineId) {
        return branchService.getByVaccineId(vaccineId);
    }

    @Operation(summary = "Create a new branch visit")
    @PostMapping("/branches/visit")
    @ResponseStatus(HttpStatus.CREATED)
    public BranchVisitDTO createVisit(@RequestBody BranchVisitDTO branchVisitDTO) {
        return branchService.createVisit(branchVisitDTO);
    }

    @Operation(summary = "Get visit by branch Id and visit time")
    @GetMapping("/branches/{branchId}/visit/time-slots/{timeSlot}")
    Optional<BranchVisitDTO> getVisitByBranchIdAndVisitTime(@PathVariable("branchId") Long branchId, @PathVariable("timeSlot") String timeSlotStr) {
        return branchService.getVisitByBranchIdAndVisitTime(branchId, timeSlotStr);
    }

    @Operation(summary = "Get all available time slots by branch id")
    @GetMapping("/branches/{branchId}/time-slots")
    public List<LocalTime> getAvailableTimeById(@PathVariable("branchId") Long branchId) {
        return branchService.getAvailableTimeById(branchId);
    }

    @Operation(summary = "Get all visits by branch Id, visit status and visit date range. In case toDate is null then the range will be for one day")
    @GetMapping({
            "/branches/{branchId}/visit/status/{visitStatus}",
            "/branches/{branchId}/visit/date/{fromDate}",
            "/branches/{branchId}/visit/date/{fromDate}/{toDate}",
            "/branches/{branchId}/visit/status/{visitStatus}/date/{fromDate}",
            "/branches/{branchId}/visit/status/{visitStatus}/date/{fromDate}/{toDate}"})
    public BranchDTO getVisitsByBranchIdAndVisitStatus(@PathVariable("branchId") Long branchId,
                                                       @PathVariable(name = "visitStatus", required = false) String visitStatusStr,
                                                       @PathVariable(name = "fromDate", required = false) String fromDateStr,
                                                       @PathVariable(name = "toDate", required = false) String toDateStr) {
        return branchService.getVisitsByBranchIdAndVisitStatusAndVisitDateRange(branchId, visitStatusStr, fromDateStr, toDateStr);
    }

    @Operation(summary = "Get all visits by branch Id, visit status and visit date range. In case toDate is null then the range will be for one day")
    @GetMapping({
            "/branches/visit/status/{visitStatus}/date/{fromDate}",
            "/branches/visit/status/{visitStatus}/date/{fromDate}/{toDate}"})
    public List<BranchVisitDTO> getAllVisitsByVisitStatusAndVisitDateRange(@PathVariable("visitStatus") String visitStatusStr,
                                                                           @PathVariable("fromDate") String fromDateStr,
                                                                           @PathVariable(name = "toDate", required = false) String toDateStr) {
        return branchService.getAllVisitsByVisitStatusAndVisitDateRange(visitStatusStr, fromDateStr, toDateStr);
    }
}
