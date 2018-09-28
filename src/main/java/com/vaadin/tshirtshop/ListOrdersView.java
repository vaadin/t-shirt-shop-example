/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.tshirtshop;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.tshirtshop.domain.TShirtOrder;
import com.vaadin.tshirtshop.domain.TShirtOrderRepository;

@Route
public class ListOrdersView extends VerticalLayout {
    
    private final TShirtOrderRepository repo;
    final Grid<TShirtOrder> orders = new Grid<>(TShirtOrder.class);
    
    public ListOrdersView(TShirtOrderRepository repo) {
        this.repo = repo;
        // Build the layout
        H1 heading = new H1("List of submitted orders");
        Button update = new Button(VaadinIcon.REFRESH.create());
        RouterLink orderView = new RouterLink("Submit new order", MainView.class);
        add(heading, update, orders, orderView);
        
        orders.setColumns("name", "email", "shirtSize");
        orders.addComponentColumn(order -> {
            Button deleteBtn = new Button(VaadinIcon.TRASH.create());
            deleteBtn.addClickListener(e -> {
                repo.delete(order);
                listOrders();
            });
            return deleteBtn;
        });
        listOrders();
        
        update.addClickListener(e -> listOrders());
        
    }

    public void listOrders() {
        orders.setItems(repo.findAll());
    }
    
}
