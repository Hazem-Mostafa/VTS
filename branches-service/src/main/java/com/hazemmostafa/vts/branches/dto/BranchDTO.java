package com.hazemmostafa.vts.branches.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalTime;
import java.util.List;

public class BranchDTO extends BaseDTO {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private LocalTime openTime;
    private LocalTime closeTime;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<BranchStoreDTO> branchStoreList;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<BranchVisitDTO> branchVisitList;

    public BranchDTO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }

    public List<BranchStoreDTO> getBranchStoreList() {
        return branchStoreList;
    }

    public void setBranchStoreList(List<BranchStoreDTO> branchStoreList) {
        this.branchStoreList = branchStoreList;
    }

    public List<BranchVisitDTO> getBranchVisitList() {
        return branchVisitList;
    }

    public void setBranchVisitList(List<BranchVisitDTO> branchVisitList) {
        this.branchVisitList = branchVisitList;
    }

    @Override
    public String toString() {
        return "BranchDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                ", branchStoreList=" + branchStoreList +
                ", branchVisitList=" + branchVisitList +
                '}';
    }
}
