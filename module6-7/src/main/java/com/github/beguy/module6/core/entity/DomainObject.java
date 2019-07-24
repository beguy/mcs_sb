package com.github.beguy.module6.core.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class DomainObject {
    @Column(name = "ID")
    @Id
    // for unique Id per table
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    public DomainObject() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id=" + id;
    }
}
