package com.eteh.eteh.models;

import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Audited
@Entity
public class Footing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String footing;
    private Long footingId;


    public Footing() {
    }

    public Footing(String footing, Long footingId) {
        this.footing=footing;
        this.footingId=footingId;
    }

    public Long getFootingId() {
        return footingId;
    }

    public void setFootingId(Long footingId) {
        this.footingId = footingId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFooting() {
        return footing;
    }

    public void setFooting(String footing) {
        this.footing = footing;
    }


}
