package com.example.STM.View;

import com.example.STM.Entity.Task;
import com.example.STM.Entity.User;
import com.example.STM.Service.TaskService;
import com.example.STM.Service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;


@Route(value = "service6", layout = MenuBar.class)
public class Service6 extends VerticalLayout {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    Label info = new Label("");
    FormLayout formLayout = new FormLayout();

    TextField titleInput = new TextField();
    TextArea descriptionInput = new TextArea();
    ComboBox<Task.Status> statusInput = new ComboBox<>();
    ComboBox<Task.Type> typeInput = new ComboBox<>();
    ComboBox<User> ownerInput = new ComboBox<>();

    Button saveButton = new Button("Save");
    Button clearButton = new Button("Clear");

    public Service6() {
        statusInput.setItems(Task.Status.values());
        typeInput.setItems(Task.Type.values());

        ownerInput.setItemLabelGenerator(u -> u.getName() + u.getLastName());

        formLayout.addFormItem(titleInput, "Title");
        formLayout.addFormItem(descriptionInput, "Description");
        formLayout.addFormItem(statusInput, "Status");
        formLayout.addFormItem(typeInput, "Type");
        formLayout.addFormItem(ownerInput, "Owner");

        saveButton.addClickListener(l -> {
            addTask();
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

    @PostConstruct
    public void init(){
        ownerInput.setItems(userService.allUsers());
    }

    public void addTask() {
        if (titleInput.isEmpty() ||
                descriptionInput.isEmpty() ||
                statusInput.isEmpty() ||
                typeInput.isEmpty() ||
                ownerInput.isEmpty()
        ) info.setText("Fill all fields");
        else {
            Task task = new Task(
                    titleInput.getValue(),
                    descriptionInput.getValue(),
                    typeInput.getValue(),
                    statusInput.getValue(),
                    ownerInput.getValue());
            taskService.create(task);
            info.setText("Task added");
        }
    }

    public void clearFields() {
        titleInput.clear();
        descriptionInput.clear();
    }
}