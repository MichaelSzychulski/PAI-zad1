package com.example.STM.View;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouterLayout;

@ParentLayout(MainView.class)
class MenuBar extends Div implements RouterLayout {

    public MenuBar() {
        VerticalLayout layout = new VerticalLayout();
        layout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.START);
        Div div = new Div();

        div.add(addMenuElement(Service1.class, "Zadanie 1."));
        div.add(addMenuElement(Service2.class, "Zadanie 2."));
        div.add(addMenuElement(Service3.class, "Zadanie 3."));
        div.add(addMenuElement(Service4.class, "Zadanie 4."));
        div.add(addMenuElement(Service5.class, "Zadanie 5."));
        div.add(addMenuElement(Service6.class, "Zadanie 6."));
        div.add(addMenuElement(Service7.class, "Zadanie 7."));
        div.add(addMenuElement(Service8.class, "Zadanie 8."));
        div.add(addMenuElement(Service9.class, "Zadanie 9."));
        div.add(addMenuElement(Service10.class, "Zadanie 10."));


        layout.add(div);
        add(layout);
    }


    private Component addMenuElement(
            Class<? extends Component> navigationTarget,
            String name)
    {
        return new Button(name, buttonClickEvent -> {
            getUI().ifPresent(ui -> ui.navigate(navigationTarget));
        });
    }
}