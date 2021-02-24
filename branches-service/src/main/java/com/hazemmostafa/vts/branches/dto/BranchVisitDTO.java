package com.hazemmostafa.vts.branches.dto;

import com.hazemmostafa.vts.branches.domain.Branch;
import com.hazemmostafa.vts.branches.domain.enumeration.VisitPaymentMethod;
import com.hazemmostafa.vts.branches.domain.enumeration.VisitStatus;
import com.hazemmostafa.vts.clients.dto.ClientDTO;
import com.hazemmostafa.vts.vaccines.dto.VaccineDTO;

import java.time.LocalDateTime;

public class BranchVisitDTO extends BaseDTO{
    private Long id;
    private Long branchId;
    private VaccineDTO vaccineDTO;
    private ClientDTO clientDTO;
    private LocalDateTime visitDate;
    private VisitStatus status;
    private VisitPaymentMethod paymentMethod;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public VaccineDTO getVaccineDTO() {
        return vaccineDTO;
    }

    public void setVaccineDTO(VaccineDTO vaccineDTO) {
        this.vaccineDTO = vaccineDTO;
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
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

    @Override
    public String toString() {
        return "BranchVisitDTO{" +
                "id=" + id +
                ", branchId=" + branchId +
                ", vaccineDTO=" + vaccineDTO +
                ", clientDTO=" + clientDTO +
                ", visitDate=" + visitDate +
                ", status=" + status +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}
