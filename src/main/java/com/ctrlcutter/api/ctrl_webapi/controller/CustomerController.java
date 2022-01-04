package com.ctrlcutter.api.ctrl_webapi.controller;

import com.ctrlcutter.api.ctrl_webapi.helper.ExistingParameters;
import com.ctrlcutter.api.ctrl_webapi.helper.LoginForm;
import com.ctrlcutter.api.ctrl_webapi.models.Customer;
import com.ctrlcutter.api.ctrl_webapi.services.CustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            return new ResponseEntity<>("Missing data(username, email or password)!", HttpStatus.BAD_REQUEST);
        }

        ExistingParameters parameters = this.customerService.parametersExist(customer.getUsername(), customer.getEmail());

        if (parameters.areExisting()) {
            return new ResponseEntity<>(parameters.getParameters(), HttpStatus.BAD_REQUEST);
        }

        this.customerService.createCustomer(customer);

        return new ResponseEntity<>("Customer created!", HttpStatus.OK);
    }


    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<Object> login(@RequestBody LoginForm loginForm) {

        if (StringUtils.isEmpty(loginForm.getEmail()) | StringUtils.isEmpty(loginForm.getPassword())) {
            return new ResponseEntity<>("Missing login parameters(email, password)!", HttpStatus.BAD_REQUEST);
        }

        if (!this.customerService.customerExists(loginForm.getEmail())) {
            return new ResponseEntity<>("Customer does not exist.", HttpStatus.BAD_REQUEST);
        }

        if (this.customerService.loginCustomer(loginForm)) {
            return new ResponseEntity<>("Customer successfully logged in!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Login failed. Wrong password!", HttpStatus.BAD_REQUEST);
        }
    }
}
