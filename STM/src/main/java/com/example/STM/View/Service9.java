package com.example.STM.View;

import com.example.STM.Entity.Task;
import com.example.STM.Service.TaskService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "service9", layout = MenuBar.class)
public class Service9 extends VerticalLayout {
    @Autowired
    private TaskService taskService;

    Label info = new Label();
    TextField input = new TextField();
    Button search = new Button("Search");

    ComboBox<Task.Status> statusInput = new ComboBox<>();
    Button change = new Button("Change");

    Grid<Task> grid = new Grid<>(Task.class);

    Task currentTask = null;

    public Service9() {
        statusInput.setItems(Task.Status.values());

        grid.removeColumnByKey("owner");
        grid.addColumn(t -> t.getOwner().getName() + " "+ t.getOwner().getLastName()).setHeader("Owner");

        search.addClickListener(l -> find());
        change.addClickListener(l -> changeStatus());

        input.setLabel("ID");


        add(
                info,
                new HorizontalLayout(input, search),
                new HorizontalLayout(statusInput, change),
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

    public void changeStatus(){
        if(currentTask == null){
            info.setText("First find valid task to change status");
        } else if(statusInput.isEmpty() || statusInput.getValue() == currentTask.getStatus()){
            info.setText("Choose new status");
        } else{
            taskService.changeStatus(currentTask, statusInput.getValue());
            info.setText("Status changed");
        }
    }
}