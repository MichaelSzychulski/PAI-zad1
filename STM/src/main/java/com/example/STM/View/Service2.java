package com.example.STM.View;

import com.example.STM.Entity.User;
import com.example.STM.Repository.UserRepository;
import com.example.STM.Service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@VaadinSessionScope
@Route(value = "service2", layout = MenuBar.class)
public class Service2 extends VerticalLayout {
    @Autowired
    private UserService userService;

    Grid<User> grid = new Grid<>(User.class);

    @PostConstruct
    public void init(){
        grid.removeColumnByKey("tasks");
        grid.setItems(userService.allUsers());
        add(grid);
    }
}