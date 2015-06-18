package com.ejemplo.vaadin;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("proyectovaadin7")
public class Proyectovaadin7 extends UI implements ClickListener {

    private PantallaInicial inicio;

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = Proyectovaadin7.class)
    public static class Servlet extends VaadinServlet {
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        // setContent(layout);

        inicio = new PantallaInicial();
        inicio.getButton_1().addClickListener(this);
        setContent(inicio);

        Table tabla = inicio.getTable_1();

        tabla.addContainerProperty("Nombre", String.class, null);
        tabla.addContainerProperty("Edad", Integer.class, null);
        tabla.setSelectable(true);
        Object item1 = tabla.addItem();
        Item fila = tabla.getItem(item1);

        fila.getItemProperty("Nombre").setValue("asdjklñasd");
        fila.getItemProperty("Edad").setValue(12);

        tabla.setPageLength(5);
        tabla.addItemClickListener(new ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                // String sel = (String) event.getPropertyId();

                // new Notification("Has pulsado " + sel,
                // Notification.Type.WARNING_MESSAGE).show(Page
                // .getCurrent());

                Property itNombre = event.getItem().getItemProperty("Nombre");
                Property itEdad = event.getItem().getItemProperty("Edad");

                Notification.show("Has pulsado: " + itNombre.getValue()
                        + ", edad: " + itEdad.getValue(),
                        "Comentario adicional toca Guevos",
                        Notification.Type.HUMANIZED_MESSAGE);

            }
        });

        /*
         * Button button = new Button("Click Me"); button.addClickListener(new
         * Button.ClickListener() { public void buttonClick(ClickEvent event) {
         * layout.addComponent(new Label("Thank you for clicking")); } });
         */

    }

    @Override
    public void buttonClick(ClickEvent event) {
        // TODO Auto-generated method stub
        if (event.getSource() == inicio.getButton_1()) {
            inicio.getTextField_1().setValue("Botón pulsado.");
        }

    }

}