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


@Route(value = "service8", layout = MenuBar.class)
public class Service8 extends VerticalLayout {
    @Autowired
    private TaskService taskService;

    Label info = new Label();
    TextField inputTitle = new TextField();
    ComboBox<Task.Status> statusInput = new ComboBox<>();
    ComboBox<Task.Type> typeInput = new ComboBox<>();
    Button searchByTitle = new Button("Search by Title");
    Button searchByType = new Button("Search by Type");
    Button searchByStatus = new Button("Search by Status");

    Grid<Task> grid = new Grid<>(Task.class);

    public Service8() {
        statusInput.setItems(Task.Status.values());
        typeInput.setItems(Task.Type.values());

        grid.removeColumnByKey("owner");
        grid.addColumn(t -> t.getOwner().getName() + " "+ t.getOwner().getLastName()).setHeader("Owner");

        searchByStatus.addClickListener(l -> findByStatus());
        searchByTitle.addClickListener(l -> findByTitle());
        searchByType.addClickListener(l -> findByType());

        inputTitle.setLabel("Title");
        statusInput.setLabel("Status");
        typeInput.setLabel("Type");

        add(
                info,
                new HorizontalLayout(inputTitle, searchByTitle),
                new HorizontalLayout(statusInput, searchByStatus),
                new HorizontalLayout(typeInput, searchByType),
                grid);
    }

    public void findByStatus(){
        if(statusInput.isEmpty()) info.setText("Input cannot be empty");
        else {
            info.setText("");
            grid.setItems(taskService.findByTitleStatusType(null, statusInput.getValue(), null));
        }
    }

    public void findByType(){
        if(typeInput.isEmpty()) info.setText("Input cannot be empty");
        else {
            info.setText("");
            grid.setItems(taskService.findByTitleStatusType(null, null, typeInput.getValue()));
        }
    }

    public void findByTitle(){
        if(inputTitle.isEmpty()) info.setText("Input cannot be empty");
        else {
            info.setText("");
            grid.setItems(taskService.findByTitleStatusType(inputTitle.getValue(), null, null));
        }
    }


}