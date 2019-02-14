package org.launchcode.models;

import javax.persistence.Entity;

@Entity

public class Category {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    Category(){}

    Category(name) {
        this.name = name;
    }

    public void setName( String) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
