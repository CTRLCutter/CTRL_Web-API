package com.ctrlcutter.api.ctrl_webapi.repositories;

import com.ctrlcutter.api.ctrl_webapi.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsCustomerByUsername(String username);

    boolean existsCustomerByEmail(String email);

    Customer getCustomerByEmail(String email);
}
