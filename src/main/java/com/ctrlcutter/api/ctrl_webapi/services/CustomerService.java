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
    private final SessionService sessionService;
    BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

    @Autowired
    public CustomerService(CustomerRepository customerRepository, SessionService sessionService) {
        this.customerRepository = customerRepository;
        this.sessionService = sessionService;
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

    public String createCustomer(Customer customer) {
        customer.setPassword(this.bCrypt.encode(customer.getPassword()));
        customer.setRegistration_date(new Timestamp(Calendar.getInstance().getTime().getTime()));

        this.customerRepository.save(customer);

        return this.sessionService.createSession(customer);
    }

    @Transactional
    public void deleteCustomer(String username, String email) {
        this.customerRepository.deleteCustomerByUsernameAndEmail(username, email);
    }

    public boolean customerExists(String email) {
        return this.customerRepository.existsCustomerByEmail(email);
    }

    public String loginCustomer(LoginForm loginForm) {
        Customer customer = this.customerRepository.getCustomerByEmail(loginForm.getEmail());

        if (this.bCrypt.matches(loginForm.getPassword(), customer.getPassword())) {
            return this.sessionService.getSessionKeyByCustomer(customer);
        } else {
            return null;
        }
    }

    public void logoutCustomer(String sessionkey) {
        this.sessionService.deleteSession(sessionkey);
    }

    public Customer getCustomerData(String sessionKey) {
        if (this.sessionService.checkSessionValidity(sessionKey)) {
            Customer customer = this.sessionService.getCustomerBySessionKey(sessionKey);
            customer.setPassword(null);

            return customer;
        } else {
            return null;
        }
    }
}
