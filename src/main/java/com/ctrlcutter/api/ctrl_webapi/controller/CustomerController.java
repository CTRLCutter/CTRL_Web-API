package com.ctrlcutter.api.ctrl_webapi.controller;

import com.ctrlcutter.api.ctrl_webapi.helper.ExistingParameters;
import com.ctrlcutter.api.ctrl_webapi.helper.LoginForm;
import com.ctrlcutter.api.ctrl_webapi.models.Customer;
import com.ctrlcutter.api.ctrl_webapi.services.CustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/signup", produces = "application/json")
    public ResponseEntity<Object> signup(@RequestBody Customer customer) {

        if (StringUtils.isEmpty(customer.getUsername()) | StringUtils.isEmpty(customer.getEmail()) | StringUtils.isEmpty(customer.getPassword())) {
            return new ResponseEntity<>("Missing data(username, email or password).", HttpStatus.BAD_REQUEST);
        }

        ExistingParameters parameters = this.customerService.parametersExist(customer.getUsername(), customer.getEmail());

        if (parameters.areExisting()) {
            return new ResponseEntity<>(parameters.getParameters(), HttpStatus.BAD_REQUEST);
        }

        String sessionKey = this.customerService.createCustomer(customer);

        return new ResponseEntity<>("{\"session_key\": \"" + sessionKey + "\"}", HttpStatus.OK);
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<Object> login(@RequestBody LoginForm loginForm) {

        if (StringUtils.isEmpty(loginForm.getEmail()) | StringUtils.isEmpty(loginForm.getPassword())) {
            return new ResponseEntity<>("Missing login parameters(email, password).", HttpStatus.BAD_REQUEST);
        }

        if (!this.customerService.customerExists(loginForm.getEmail())) {
            return new ResponseEntity<>("Customer does not exist.", HttpStatus.BAD_REQUEST);
        }

        String sessionKey = this.customerService.loginCustomer(loginForm);

        if (sessionKey != null) {
            return new ResponseEntity<>("{\"session_key\": \"" + sessionKey + "\"}", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Login failed. Wrong password.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/logout", produces = "application/json")
    public ResponseEntity<Object> logout(@RequestHeader Map<String, String> header) {
        String sessionKey = header.get("sessionkey");

        if (sessionKey == null) {
            return new ResponseEntity<>("Session key not provided. Try again", HttpStatus.BAD_REQUEST);
        }

        this.customerService.logoutCustomer(sessionKey);

        return new ResponseEntity<>("Successfully logged out and deleted all sessions.", HttpStatus.OK);
    }

    @PostMapping(value = "/customerData", produces = "application/json")
    public ResponseEntity<Object> customerData(@RequestHeader Map<String, String> header) {
        String sessionKey = header.get("sessionkey");

        if (sessionKey == null) {
            return new ResponseEntity<>("Session key not provided. Try again", HttpStatus.BAD_REQUEST);
        }

        Customer customer = this.customerService.getCustomerData(sessionKey);

        if (customer == null) {
            return new ResponseEntity<>("Session invalid", HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }
}
