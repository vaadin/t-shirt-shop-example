package com.vaadin.tshirtshop;

import com.vaadin.tshirtshop.domain.TShirtOrder;
import com.vaadin.tshirtshop.domain.TShirtOrderRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A service class for the UI to access backend services.
 */
@Service
class TShirtService {
    
    @Autowired
    private TShirtOrderRepository repository;

    List<String> getSizes() {
        return Arrays.asList("Small", "Medium", "Large", "Extra Large", "XXL");
    }

    public void placeOrder(TShirtOrder order) throws IllegalArgumentException {
        repository.save(order);
    }

}
