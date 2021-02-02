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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


@Route(value = "service5", layout = MenuBar.class)
public class Service5 extends VerticalLayout {
    @Autowired
    private UserService userService;

    Label info = new Label();
    TextField input = new TextField();
    Button search = new Button("Delete");

    public Service5() {
        input.setLabel("Input User ID or email");

        search.addClickListener(l -> deleteUser());

        add(
                info,
                input,
                search);
    }

    public void deleteUser(){
        if(input.isEmpty()) info.setText("Please input ID or email");
        else{
            Optional<User> user = userService.findByIdOrEmail(input.getValue());
            if(user.isPresent()) {
                info.setText("User deleted");
                userService.delete(user.get());
            }
            else  info.setText("No user found");
        }
    }

}