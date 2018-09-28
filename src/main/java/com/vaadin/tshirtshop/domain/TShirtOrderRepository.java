
package com.vaadin.tshirtshop.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Empty JpaRepository is enough for a simple crud.
 */
public interface TShirtOrderRepository extends JpaRepository<TShirtOrder, Long> {
    
}
