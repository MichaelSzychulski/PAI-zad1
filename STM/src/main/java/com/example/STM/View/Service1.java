package com.example.STM.View;

import com.example.STM.Entity.User;
import com.example.STM.Service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import org.springframework.beans.factory.annotation.Autowired;

@VaadinSessionScope
@Route(value = "service1", layout = MenuBar.class)
@RouteAlias(value = "", layout = MenuBar.class)
public class Service1 extends VerticalLayout {
    @Autowired
    private UserService userService;

    Label info = new Label("");
    FormLayout formLayout = new FormLayout();

    TextField nameInput = new TextField();
    TextField lastNameInput = new TextField();
    TextField emailInput = new TextField();
    PasswordField passwordInput = new PasswordField();

    Button saveButton = new Button("Save");
    Button clearButton = new Button("Clear");

    public Service1() {
        formLayout.addFormItem(nameInput, "Name");
        formLayout.addFormItem(lastNameInput, "Last Name");
        formLayout.addFormItem(emailInput, "Email");
        formLayout.addFormItem(passwordInput, "Password");

        saveButton.addClickListener(l -> {
            addUser();
        });

        clearButton.addClickListener(l -> {
            clearFields();
        });

        add(
                info,
                formLayout,
                saveButton,
                clearButton);

    }

    public void addUser() {
        if (nameInput.isEmpty() ||
                lastNameInput.isEmpty() ||
                emailInput.isEmpty() ||
                passwordInput.isEmpty()
        ) info.setText("Fill all fields");
        else {
            User user = new User(
                    nameInput.getValue(),
                    lastNameInput.getValue(),
                    emailInput.getValue(),
                    passwordInput.getValue());
            userService.createUser(user);
            info.setText("User added");
        }
    }

    public void clearFields(){
        nameInput.clear();
        lastNameInput.clear();
        emailInput.clear();
        passwordInput.clear();
    }
}