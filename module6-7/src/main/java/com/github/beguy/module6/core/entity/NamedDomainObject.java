package com.github.beguy.module6.core.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

@MappedSuperclass
public class NamedDomainObject extends DomainObject {
    @Column(name = "NAME")
    @NotBlank(message = "Name is mandatory")
    protected String name;

    public NamedDomainObject() {
        super();
    }

    protected NamedDomainObject(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
