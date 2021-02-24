package com.hazemmostafa.vts.branches.dto;

import com.hazemmostafa.vts.vaccines.dto.VaccineDTO;

import java.time.LocalDate;

public class BranchStoreDTO extends BaseDTO {
    private Long id;
    private Long branchId;
    private VaccineDTO vaccineDTO;
    private Long quantity;
    private String name;
    private String address;
    private LocalDate productionDate;
    private LocalDate expirationDate;

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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
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

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "BranchStoreDTO{" +
                "id=" + id +
                ", branchId=" + branchId +
                ", vaccineDTO=" + vaccineDTO +
                ", quantity=" + quantity +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", productionDate=" + productionDate +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
