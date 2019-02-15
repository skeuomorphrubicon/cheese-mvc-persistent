package org.launchcode.models.forms;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;

import javax.validation.constraints.NotNull;

public class AddMenuItemForm {

    @NotNull
    private Menu menu;

    @NotNull
    private int menuId;

    @NotNull
    private int cheeseId;

    @NotNull
    private Iterable<Cheese> cheeses;

    public AddMenuItemForm(){}

    public AddMenuItemForm(Menu menu, Iterable<Cheese> cheeses){
        this.menu = menu;
        this.cheeses = cheeses;
    }

    public void setMenu(Menu menu){
        this.menu = menu;
    }

    public Menu getMenu(){
        return menu;
    }

    public int getMenuId() {
        return menuId;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public Iterable<Cheese> getCheeses(){
        return cheeses;
    }

}
