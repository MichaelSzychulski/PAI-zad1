package com.example.STM.View;

import com.example.STM.Entity.User;
import com.example.STM.Service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


@Route(value = "service4", layout = MenuBar.class)
public class Service4 extends VerticalLayout {
    @Autowired
    private UserService userService;

    Label info = new Label();
    TextField input = new TextField();
    Button search = new Button("Change Status");

    Grid<User> grid = new Grid<>(User.class);

    public Service4() {
        input.setLabel("Input User ID or email");
        grid.removeColumnByKey("tasks");

        search.addClickListener(l -> findUserAndChangeStatus());

        add(
                info,
                input,
                search,
                grid);
    }

    public void findUserAndChangeStatus(){
        if(input.isEmpty()) info.setText("Please input ID or email");
        else{
            Optional<User> user = userService.findByIdOrEmail(input.getValue());
            if(user.isPresent()) {
                info.setText("Status Changed");
                userService.changeStatus(user.get());
                grid.setItems(userService.findByIdOrEmail(input.getValue()).get());
            }
            else {
                grid.setItems();
                info.setText("No user found");
            }
        }
    }

}