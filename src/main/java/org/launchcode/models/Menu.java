package org.launchcode.models;

import org.springframework.data.annotation.Id;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Menu {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 3, max = 15, message="Name cannot be empty")
    private String name;

    @ManyToMany
    private List<Cheese> cheeses;



    public void menu(String name) {
        this.name = name;
    }
    public Menu() {}

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }
    public void addItem(Cheese item) {
        cheeses.add(item);
    }





}
