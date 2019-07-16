package com.github.beguy.module6.core.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

@MappedSuperclass
public class DomainObject {
    @Column(name = "ID")
    @Id
    // for unique Id per table
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected @NotNull Long id;

    @Column(name = "NAME")
    @NotBlank(message = "Name is mandatory")
    protected String name;

    public DomainObject() {
    }

    protected DomainObject(String name) {
        this.name = name;
    }

    public @NotNull Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
