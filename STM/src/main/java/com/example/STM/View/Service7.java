package com.example.STM.View;

import com.example.STM.Entity.Task;
import com.example.STM.Service.TaskService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route(value = "service7", layout = MenuBar.class)
public class Service7 extends VerticalLayout {
    @Autowired
    private TaskService taskService;

    Grid<Task> grid = new Grid<>(Task.class);

    public Service7() {
        grid.removeColumnByKey("owner");
        grid.addColumn(t -> t.getOwner().getName() + " "+ t.getOwner().getLastName()).setHeader("Owner");
        add(grid);
    }

    @PostConstruct
    public void init(){
        grid.setItems(taskService.getAllOrderByDateAddedDesc());
    }
}