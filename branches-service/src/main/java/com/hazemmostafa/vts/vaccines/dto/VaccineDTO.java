package com.hazemmostafa.vts.vaccines.dto;

public class VaccineDTO {
    private Long id;
    private String name;
    private String dose;
    private int repetitionCount;

    public VaccineDTO() {
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

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public int getRepetitionCount() {
        return repetitionCount;
    }

    public void setRepetitionCount(int repetitionCount) {
        this.repetitionCount = repetitionCount;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dose='" + dose + '\'' +
                ", repetitionCount=" + repetitionCount +
                '}';
    }
}
