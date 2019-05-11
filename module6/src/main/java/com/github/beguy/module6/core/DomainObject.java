package com.github.beguy.module6.core;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

@MappedSuperclass
public class DomainObject {
    @Id
    // for unique Id per table
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @NotBlank(message = "Name is mandatory")
    protected String name;

    public DomainObject() {
    }

    protected DomainObject(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
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
