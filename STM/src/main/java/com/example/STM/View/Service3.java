package com.example.STM.View;

import com.example.STM.Entity.User;
import com.example.STM.Service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


@Route(value = "service3", layout = MenuBar.class)
public class Service3 extends VerticalLayout {
    @Autowired
    private UserService userService;

    Label info = new Label();
    TextField input = new TextField();
    Button search = new Button("Search");

    Grid<User> grid = new Grid<>(User.class);

    public Service3() {
        input.setLabel("Input User ID or email");
        grid.removeColumnByKey("tasks");

        search.addClickListener(l -> findUser());

        add(
                info,
                input,
                search,
                grid);
    }

    public void findUser(){
        if(input.isEmpty()) info.setText("Please input ID or email");
        else{
            info.setText("");
            Optional<User> user = userService.findByIdOrEmail(input.getValue());
            if(user.isPresent()) grid.setItems(user.get());
            else {
                grid.setItems();
                info.setText("No user found");
            }
        }
    }

}