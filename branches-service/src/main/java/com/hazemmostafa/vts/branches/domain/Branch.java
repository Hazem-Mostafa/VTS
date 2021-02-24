package com.hazemmostafa.vts.branches.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "VTS_BRANCH")
public class Branch extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branchSeqGenerator")
    @SequenceGenerator(name = "branchSeqGenerator", sequenceName = "BRN_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "OPEN_TIME", nullable = false)
    private LocalTime openTime;

    @Column(name = "CLOSE_TIME", nullable = false)
    private LocalTime closeTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
    private List<BranchStore> branchStoreList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
    private List<BranchVisit> branchVisitList;

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

    public List<BranchStore> getBranchStoreList() {
        return branchStoreList;
    }

    public void setBranchStoreList(List<BranchStore> branchStoreList) {
        this.branchStoreList = branchStoreList;
    }

    public List<BranchVisit> getBranchVisitList() {
        return branchVisitList;
    }

    public void setBranchVisitList(List<BranchVisit> branchVisitList) {
        this.branchVisitList = branchVisitList;
    }
}
