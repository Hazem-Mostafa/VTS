package com.hazemmostafa.vts.clients.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "VTS_CLIENT")
public class Client extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taskSeqGenerator")
    @SequenceGenerator(name = "taskSeqGenerator", sequenceName = "CLN_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "EMAIL")
    private String email;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
