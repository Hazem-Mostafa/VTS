package com.hazemmostafa.vts.vaccines.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "VTS_VACCINE")
public class Vaccine extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vaccineSeqGenerator")
    @SequenceGenerator(name = "vaccineSeqGenerator", sequenceName = "VCC_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DOSE")
    private String dose;

    @Column(name="REPETITION_COUNT")
    private int repetitionCount;

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
}
