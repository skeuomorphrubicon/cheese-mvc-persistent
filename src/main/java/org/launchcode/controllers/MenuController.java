package org.launchcode.controllers;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CategoryDao;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "menu")
public class MenuController {

    @Autowired
    private CheeseDao cheeseDao;
    private CategoryDao categoryDao;
    private MenuDao menuDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("menus", menuDao.findAll());
        model.addAttribute("title", "Menus");

        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {

        Menu aMenu = new Menu();
        model.addAttribute("title", "Add a Menu");
        model.addAttribute("menu", aMenu);
        return "add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @RequestParam @Valid Menu menu, Errors errors) {

        if (errors.hasErrors()) {
            return "add";
        }
        menuDao.save(menu);
        return "redirect:view/" + menu.getId();

    }

    @RequestMapping(value = "view/{menuId}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable("menuId") int menuId) {

        Menu aMenu = menuDao.findOne(menuId);
        model.addAttribute("menu", aMenu);
        model.addAttribute("title", aMenu.getName());

        return "view";
    }

    @RequestMapping(value = "add-item/{menuId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable("menuId") int menuId) {
        Menu aMenu = menuDao.findOne(menuId);
        AddMenuItemForm amiForm = new AddMenuItemForm(aMenu, cheeseDao.findAll());

        model.addAttribute("form", amiForm);
        model.addAttribute("title", "Add item to menu:" + aMenu.getName());

        return "add-item";
    }

    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model, @RequestParam @Valid AddMenuItemForm form, Errors errors) {

        if (errors.hasErrors()) {
            return "add-item";
        }
            int formCheeseId = form.getCheeseId();
            int formMenuId = form.getMenuId();
            Cheese addCheese = cheeseDao.findOne(formCheeseId);
            Menu addMenu = menuDao.findOne(formMenuId);
            addMenu.addItem(addCheese);
            menuDao.save(addMenu);

            return "view/" + formMenuId;

        }


    }


