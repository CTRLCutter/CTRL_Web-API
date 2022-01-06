package com.ctrlcutter.api.ctrl_webapi.services;

import com.ctrlcutter.api.ctrl_webapi.helper.ExistingParameters;
import com.ctrlcutter.api.ctrl_webapi.helper.LoginForm;
import com.ctrlcutter.api.ctrl_webapi.models.Customer;
import com.ctrlcutter.api.ctrl_webapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ExistingParameters parametersExist(String username, String email) {
        ExistingParameters existingParameters = new ExistingParameters();

        if (this.customerRepository.existsCustomerByUsername(username)) {
            existingParameters.setExisting();
            existingParameters.addParameter("username", username);
        }

        if (this.customerRepository.existsCustomerByEmail(email)) {
            existingParameters.setExisting();
            existingParameters.addParameter("email", email);
        }

        return existingParameters;
    }

    public void createCustomer(Customer customer) {
        customer.setPassword(this.bCrypt.encode(customer.getPassword()));
        customer.setRegistration_date(new Timestamp(Calendar.getInstance().getTime().getTime()));

        this.customerRepository.save(customer);
    }

    @Transactional
    public void deleteCustomer(String username, String email) {
        this.customerRepository.deleteCustomerByUsernameAndEmail(username, email);
    }

    public boolean customerExists(String email) {
        return this.customerRepository.existsCustomerByEmail(email);
    }

    public boolean loginCustomer(LoginForm loginForm) {
        return this.bCrypt.matches(loginForm.getPassword(), this.customerRepository.getCustomerByEmail(loginForm.getEmail()).getPassword());
    }
}
