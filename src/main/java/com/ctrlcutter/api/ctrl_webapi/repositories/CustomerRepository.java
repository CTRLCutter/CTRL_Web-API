package com.ctrlcutter.api.ctrl_webapi.repositories;

import com.ctrlcutter.api.ctrl_webapi.models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    boolean existsCustomerByUsername(String username);

    boolean existsCustomerByEmail(String email);

    Customer getCustomerByEmail(String email);

    @Transactional
    void deleteCustomerByUsernameAndEmail(String username, String email);
}
