package com.hazemmostafa.vts.branches.service.impl;

import com.hazemmostafa.vts.branches.client.ClientsServiceClient;
import com.hazemmostafa.vts.branches.client.VaccinesServiceClient;
import com.hazemmostafa.vts.branches.domain.Branch;
import com.hazemmostafa.vts.branches.domain.BranchStore;
import com.hazemmostafa.vts.branches.domain.BranchVisit;
import com.hazemmostafa.vts.branches.domain.enumeration.VisitStatus;
import com.hazemmostafa.vts.branches.dto.BranchDTO;
import com.hazemmostafa.vts.branches.dto.BranchStoreDTO;
import com.hazemmostafa.vts.branches.dto.BranchVisitDTO;
import com.hazemmostafa.vts.branches.repository.BranchRepository;
import com.hazemmostafa.vts.branches.repository.BranchStoreRepository;
import com.hazemmostafa.vts.branches.repository.BranchVisitRepository;
import com.hazemmostafa.vts.branches.service.BranchService;
import com.hazemmostafa.vts.branches.service.mapper.BranchMapper;
import com.hazemmostafa.vts.branches.service.mapper.BranchStoreMapper;
import com.hazemmostafa.vts.branches.service.mapper.BranchVisitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BranchServiceImpl extends BaseServiceImpl implements BranchService {
    private final Logger log = LoggerFactory.getLogger(BranchServiceImpl.class);

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    private final BranchStoreRepository branchStoreRepository;
    private final BranchStoreMapper branchStoreMapper;

    private final BranchVisitRepository branchVisitRepository;
    private final BranchVisitMapper branchVisitMapper;

    private final ClientsServiceClient clientsServiceClient;
    private final VaccinesServiceClient vaccinesServiceClient;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, BranchMapper branchMapper, BranchStoreRepository branchStoreRepository, BranchStoreMapper branchStoreMapper, BranchVisitRepository branchVisitRepository, BranchVisitMapper branchVisitMapper, ClientsServiceClient clientsServiceClient, VaccinesServiceClient vaccinesServiceClient) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
        this.branchStoreRepository = branchStoreRepository;
        this.branchStoreMapper = branchStoreMapper;
        this.branchVisitRepository = branchVisitRepository;
        this.branchVisitMapper = branchVisitMapper;
        this.clientsServiceClient = clientsServiceClient;
        this.vaccinesServiceClient = vaccinesServiceClient;
    }

    @Override
    public BranchDTO createBranch(BranchDTO branchDTO) {
        log.debug("Request to create a new branch: {}", branchDTO);
        Assert.notNull(branchDTO, "Branch mustn't be null");
        Branch branch = branchMapper.toEntity(branchDTO);
        branch = branchRepository.save(branch);
        BranchDTO createdBranchDTO = branchMapper.toDto(branch);
        log.debug("New branch created: {}", createdBranchDTO);
        return createdBranchDTO;
    }

    @Override
    public BranchDTO getById(Long id) {
        log.debug("find branch by id: {}", id);
        Assert.notNull(id, "Branch ID mustn't be null");
        Branch branch = getBranchDetails(id, null, null, null);
        BranchDTO branchDTO = branchMapper.toDto(branch);
        log.debug("find branch by id finished: {}", branchDTO);
        return branchDTO;
    }

    @Override
    public List<BranchDTO> getAll() {
        log.debug("list all branches");
        List<Branch> branches = branchRepository.findAll();
        List<BranchDTO> branchDTOList = branchMapper.toDto(branches);
        log.debug("list all branches finished: {}", branchDTOList);
        return branchDTOList;
    }

    @Override
    public BranchStoreDTO createStore(BranchStoreDTO branchStoreDTO){
        log.debug("Request to create a new branch store: {}", branchStoreDTO);
        Assert.notNull(branchStoreDTO, "Branch store mustn't be null");
        Assert.notNull(branchStoreDTO.getBranchId(), "Branch store branch Id mustn't be null");
        Assert.notNull(branchStoreDTO.getVaccineDTO(), "Branch store vaccine Id mustn't be null");
        Assert.notNull(branchStoreDTO.getVaccineDTO().getId(), "Branch store vaccine Id mustn't be null");
        Assert.isTrue(branchStoreDTO.getQuantity() > 0, "Branch store vaccine quantity mustn't be less than zero");
        BranchStore branchStore = branchStoreMapper.toEntity(branchStoreDTO);
        branchStore = branchStoreRepository.save(branchStore);
        BranchStoreDTO createdBranchStoreDTO = branchStoreMapper.toDto(branchStore);
        log.debug("New branch store created: {}", createdBranchStoreDTO);
        return createdBranchStoreDTO;
    }

    @Override
    public List<BranchDTO> getByVaccineId(Long vaccineId) {
        log.debug("get branches by vaccine id: {}", vaccineId);
        Assert.notNull(vaccineId, "Vaccine ID mustn't be null");
        List<BranchStore> branchStoreList = branchStoreRepository.findByVaccineId(vaccineId);
        List<BranchDTO> branchDTOList = new ArrayList<BranchDTO>();
        for(BranchStore bs: branchStoreList){
            BranchDTO branchDTO = branchMapper.toDto(bs.getBranch());
            branchDTOList.add(branchDTO);
        }
        log.debug("get branches by vaccine id finished: {}", branchDTOList);
        return branchDTOList;
    }

    @Override
    public void deductVaccineQuantity(Long branchId, Long vaccineId, Long quantity) {
        log.debug("deduct vaccine quantity for branch id: {}, vaccine id: {} and quantity {}", branchId, vaccineId, quantity);
        Assert.notNull(branchId, "Branch ID mustn't be null");
        Assert.notNull(vaccineId, "Vaccine ID mustn't be null");
        Assert.notNull(quantity, "Quantity mustn't be null");
        Assert.isTrue(quantity > 0, "Quantity must be greater than 0");
        BranchStore branchStore = branchStoreRepository.findByBranchIdAndVaccineId(branchId, vaccineId);
        Assert.notNull(branchStore, "Couldn't find branch with this Id contains this vaccine Id");
        Assert.isTrue((branchStore.getQuantity() - quantity) >= 0, "Store quantity can't be negative");
        branchStore.setQuantity(branchStore.getQuantity() - quantity);
        log.debug("deduct vaccine quantity finished");
    }

    @Override
    public void addVaccineQuantity(Long branchId, Long vaccineId, Long quantity) {
        log.debug("add vaccine quantity for branch id: {}, vaccine id: {} and quantity {}", branchId, vaccineId, quantity);
        Assert.notNull(branchId, "Branch ID mustn't be null");
        Assert.notNull(vaccineId, "Vaccine ID mustn't be null");
        Assert.notNull(quantity, "Quantity mustn't be null");
        Assert.isTrue(quantity > 0, "Quantity must be greater than 0");
        BranchStore branchStore = branchStoreRepository.findByBranchIdAndVaccineId(branchId, vaccineId);
        Assert.notNull(branchStore, "Couldn't find branch with this Id contains this vaccine Id");
        branchStore.setQuantity(branchStore.getQuantity() + quantity);
        log.debug("deduct vaccine quantity finished");
    }

    @Override
    public BranchVisitDTO createVisit(BranchVisitDTO branchVisitDTO) {
        log.debug("Request to create a new branch visit: {}", branchVisitDTO);
        Assert.notNull(branchVisitDTO, "Branch visit mustn't be null");
        Assert.isNull(branchVisitDTO.getId(), "Branch visit id must be null");
        Assert.notNull(branchVisitDTO.getClientDTO(), "Branch visit client Id mustn't be null");
        Assert.notNull(branchVisitDTO.getClientDTO().getId(), "Branch visit client Id mustn't be null");
        Assert.notNull(branchVisitDTO.getVaccineDTO(), "Branch visit vaccine Id mustn't be null");
        Assert.notNull(branchVisitDTO.getVaccineDTO().getId(), "Branch visit vaccine Id mustn't be null");
        Assert.notNull(branchVisitDTO.getBranchId(), "Branch visit branch Id mustn't be null");
        BranchDTO branchDTO = getById(branchVisitDTO.getBranchId());
        Assert.notNull(branchDTO, "Branch Id is not valid");
        Assert.notNull(branchDTO.getId(), "Branch Id is not valid");
        Assert.notNull(clientsServiceClient.getClientById(branchVisitDTO.getClientDTO().getId()), "Invalid branch visit client Id");
        Assert.notNull(vaccinesServiceClient.getVaccineById(branchVisitDTO.getVaccineDTO().getId()), "Invalid branch visit vaccine Id");
        BranchStore branchStore = branchStoreRepository.findByBranchIdAndVaccineId(branchDTO.getId(), branchVisitDTO.getVaccineDTO().getId());
        Assert.notNull(branchStore, "This vaccine is not exist in this branch");
        Assert.isTrue(branchStore.getQuantity() > 0, "Not enough vaccine quantity in this branch");
        deductVaccineQuantity(branchDTO.getId(), branchVisitDTO.getVaccineDTO().getId(), 1L);
        BranchVisit branchVisit = branchVisitMapper.toEntity(branchVisitDTO);
        branchVisit.setVisitDate(adjustVisitTimeToBranchTimeSlots(branchDTO.getOpenTime(), branchDTO.getCloseTime(), branchVisit.getVisitDate()));
        branchVisit = branchVisitRepository.save(branchVisit);
        BranchVisitDTO createdBranchVisitDTO = branchVisitMapper.toDto(branchVisit);
        log.debug("New branch visit created: {}", createdBranchVisitDTO);
        return createdBranchVisitDTO;
    }

    @Override
    public Optional<BranchVisitDTO> getVisitByBranchIdAndVisitTime(Long branchId, String timeSlotStr) {
        log.debug("Get visits by branch Id {} and visit time: {}", branchId, timeSlotStr);
        Assert.notNull(branchId, "Branch Id mustn't be null");
        Assert.notNull(timeSlotStr, "branch visit time slot mustn't be null");
        BranchDTO branchDTO = getById(branchId);
        Assert.notNull(branchDTO, "Branch Id is not valid");
        Assert.notNull(branchDTO.getId(), "Branch Id is not valid");
        LocalDateTime timeSlot = null;
        try {
            timeSlot = LocalDateTime.parse(timeSlotStr, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        } catch (Exception e) {
            throw new IllegalArgumentException("invalid branch visit time slot format, use \"YYYY-MM-DDTHH:MM\"");
        }
        timeSlot = adjustVisitTimeToBranchTimeSlots(branchDTO.getOpenTime(), branchDTO.getCloseTime(), timeSlot);
        BranchVisit branchVisit = branchVisitRepository.getByBranchIdAndVisitDate(branchId, timeSlot);
        Optional<BranchVisitDTO> branchVisitOptional = Optional.empty();
        if (branchVisit != null) {
            BranchVisitDTO branchVisitDTO = branchVisitMapper.toDto(branchVisit);
            branchVisitDTO.setClientDTO(clientsServiceClient.getClientById(branchVisitDTO.getClientDTO().getId()));
            branchVisitDTO.setVaccineDTO(vaccinesServiceClient.getVaccineById(branchVisitDTO.getVaccineDTO().getId()));
            branchVisitOptional = Optional.of(branchVisitDTO);
        }
        log.debug("Get visits by branch Id and visit time finished: {}", branchVisitOptional);
        return branchVisitOptional;
    }

    @Override
    public List<LocalTime> getAvailableTimeById(Long id) {
        log.debug("list available time by branch id {}", id);
        Assert.notNull(id, "Branch ID mustn't be null");
        BranchDTO branchDTO = getById(id);
        Assert.notNull(branchDTO, "Branch ID is not valid");
        Assert.notNull(branchDTO.getId(), "Branch ID is not valid");
        List<BranchVisitDTO> branchVisitDTOList = branchDTO.getBranchVisitList();
        List<LocalTime> availableTimes = new ArrayList<LocalTime>();
        for (LocalTime timeSlot = branchDTO.getOpenTime(); timeSlot.isBefore(branchDTO.getCloseTime()); timeSlot = timeSlot.plusMinutes(15)) {
            boolean found = false;
            for (BranchVisitDTO branchVisitDTO : branchVisitDTOList)
                if (timeSlot.equals(branchVisitDTO.getVisitDate().toLocalTime())) {
                    found = true;
                    break;
                }
            if (!found)
                availableTimes.add(timeSlot);
        }
        log.debug("list available time by branch id finished {}", availableTimes);
        return availableTimes;
    }

    @Override
    public BranchDTO getVisitsByBranchIdAndVisitStatusAndVisitDateRange(Long branchId, String visitStatusStr, String fromDateStr, String toDateStr) {
        log.debug("list visits by branch Id {}, visitStatus {} and visit date range {} -> {}", branchId, visitStatusStr, fromDateStr, toDateStr);
        Assert.notNull(branchId, "Branch ID mustn't be null");
        if(visitStatusStr == null && fromDateStr == null)
            throw new IllegalArgumentException("both visit status and from date are null");
        Branch branch = getBranchDetails(branchId, visitStatusStr, fromDateStr, toDateStr);
        BranchDTO branchDTO = branchMapper.toDto(branch);
        log.debug("list visits by branch Id, visitStatus and visit date range {}", branchDTO);
        return branchDTO;
    }

    @Override
    public List<BranchVisitDTO> getAllVisitsByVisitStatusAndVisitDateRange(String visitStatusStr, String fromDateStr, String toDateStr) {
        log.debug("list visits by visit status {} and date range {} -> {} ", visitStatusStr, fromDateStr, toDateStr);
        Assert.notNull(visitStatusStr, "Visit status mustn't be null");
        Assert.notNull(fromDateStr, "from date mustn't be null");
        VisitStatus visitStatus = null;
        LocalDateTime fromDate = null, toDate = null;
        try {
            visitStatus = VisitStatus.valueOf(visitStatusStr);
        } catch (Exception e) {
            throw new IllegalArgumentException("invalid visit status value");
        }
        try {
            fromDate = LocalDateTime.parse(fromDateStr + "T00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        } catch (Exception e) {
            throw new IllegalArgumentException("invalid from date format, use \"YYYY-MM-DD\"");
        }
        if (toDateStr != null && toDateStr.trim().length() > 0) {
            try {
                toDate = LocalDateTime.parse(toDateStr + "T23:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
            } catch (Exception e) {
                throw new IllegalArgumentException("invalid to date format, use \"YYYY-MM-DD\"");
            }
        } else {
            toDate = LocalDateTime.of(fromDate.toLocalDate(), LocalTime.MAX);
        }
        List<BranchVisit> branchVisitList = branchVisitRepository.getByVisitStatusAndVisitDateRange(visitStatus, fromDate, toDate);
        List<BranchVisitDTO> branchVisitDTOList = branchVisitMapper.toDto(branchVisitList);
        log.debug("list visits by visit status and date range {}", branchVisitDTOList);
        return branchVisitDTOList;
    }

    private Branch getBranchDetails(Long branchId, String visitStatusStr, String fromDateStr, String toDateStr){
        log.debug("find branch details using id: {} visitStatusStr {} fromDateStr {} toDateStr {}", branchId, visitStatusStr, fromDateStr, toDateStr);
        Assert.notNull(branchId, "Branch ID mustn't be null");
        VisitStatus visitStatus = null;
        LocalDateTime fromDate = null, toDate = null;

        Assert.isTrue(branchRepository.existsById(branchId), "Branch ID is not valid");
        if(visitStatusStr != null && visitStatusStr.trim().length() > 0) {
            try {
                visitStatus = VisitStatus.valueOf(visitStatusStr);
            } catch (Exception e) {
                throw new IllegalArgumentException("invalid visit status value, it must be one of " + Arrays.asList(VisitStatus.values()));
            }
        }
        if(fromDateStr != null && fromDateStr.trim().length() > 0) {
            try {
                fromDate = LocalDateTime.parse(fromDateStr + "T00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
            } catch (Exception e) {
                throw new IllegalArgumentException("invalid from date format, use \"YYYY-MM-DD\"");
            }
            if (toDateStr != null && toDateStr.trim().length() > 0) {
                try {
                    toDate = LocalDateTime.parse(toDateStr + "T23:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
                } catch (Exception e) {
                    throw new IllegalArgumentException("invalid to date format, use \"YYYY-MM-DD\"");
                }
            } else {
                toDate = LocalDateTime.of(fromDate.toLocalDate(), LocalTime.MAX);
            }
        }
        Branch branch = branchRepository.getByIdAndVisitStatusAndVisitDateRange(branchId, visitStatus, fromDate, toDate);
        return branch;
    }

    private LocalDateTime adjustVisitTimeToBranchTimeSlots(LocalTime openTime, LocalTime closeTime, LocalDateTime visitTime) {
        LocalTime timePart = visitTime.toLocalTime();
        for (LocalTime timeSlot = openTime; timeSlot.isBefore(closeTime); timeSlot = timeSlot.plusMinutes(15)) {
            if (timePart.isAfter(timeSlot) && timePart.isBefore(timeSlot.plusMinutes(15))) {
                visitTime = LocalDateTime.of(visitTime.toLocalDate(), timeSlot);
                break;
            }
        }
        return visitTime;
    }
}
