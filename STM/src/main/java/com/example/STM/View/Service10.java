package com.example.STM.View;

import com.example.STM.Entity.Task;
import com.example.STM.Entity.User;
import com.example.STM.Repository.UserRepository;
import com.example.STM.Service.TaskService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "service10", layout = MenuBar.class)
public class Service10 extends VerticalLayout {
    @Autowired
    private TaskService taskService;

    Label info = new Label();
    TextField input = new TextField();
    Button search = new Button("Search");

    Button delete = new Button("Delete");

    Grid<Task> grid = new Grid<>(Task.class);

    Task currentTask = null;

    public Service10() {
        search.addClickListener(l -> find());
        delete.addClickListener(l -> delete());

        grid.removeColumnByKey("owner");
        grid.addColumn(t -> t.getOwner().getName() + " "+ t.getOwner().getLastName()).setHeader("Owner");

        input.setLabel("ID");

        add(
                info,
                new HorizontalLayout(input, search),
                delete,
                grid);
    }

    public void find(){
        if(input.isEmpty()) info.setText("ID cannot be empty");
        else {
            info.setText("");
            currentTask = taskService.findById(Long.valueOf(input.getValue()));
            if(currentTask != null)grid.setItems(currentTask);
        }
    }

    public void delete(){
        if(currentTask == null){
            info.setText("First find valid task to change status");
        } else{
            taskService.delete(currentTask);
            info.setText("Task deleted");
        }
    }
}