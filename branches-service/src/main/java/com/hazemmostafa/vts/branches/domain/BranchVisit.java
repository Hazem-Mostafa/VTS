package com.hazemmostafa.vts.branches.domain;

import com.hazemmostafa.vts.branches.domain.enumeration.VisitPaymentMethod;
import com.hazemmostafa.vts.branches.domain.enumeration.VisitStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "VTS_BRANCH_VISITS")
public class BranchVisit extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branchVisitSeqGenerator")
    @SequenceGenerator(name = "branchVisitSeqGenerator", sequenceName = "BVS_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRANCH_ID")
    private Branch branch;

    @Column(name = "VACCINE_ID", nullable = false)
    private Long vaccineId;

    @Column(name = "CLIENT_ID", nullable = false)
    private Long clientId;

    @Column(name = "VISIT_DATE", nullable = false)
    private LocalDateTime visitDate;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private VisitStatus status;

    @Column(name = "PAYMENT_METHOD", nullable = false)
    @Enumerated(EnumType.STRING)
    private VisitPaymentMethod paymentMethod;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Long getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(Long vaccineId) {
        this.vaccineId = vaccineId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDateTime visitDate) {
        this.visitDate = visitDate;
    }

    public VisitStatus getStatus() {
        return status;
    }

    public void setStatus(VisitStatus status) {
        this.status = status;
    }

    public VisitPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(VisitPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
