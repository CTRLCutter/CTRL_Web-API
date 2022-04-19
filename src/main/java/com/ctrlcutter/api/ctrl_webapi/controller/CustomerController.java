package com.ctrlcutter.api.ctrl_webapi.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ctrlcutter.api.ctrl_webapi.dto.CustomerDTO;
import com.ctrlcutter.api.ctrl_webapi.dto.SessionDTO;
import com.ctrlcutter.api.ctrl_webapi.exception.exceptions.AuthenticationException;
import com.ctrlcutter.api.ctrl_webapi.exception.exceptions.ExistingParameterException;
import com.ctrlcutter.api.ctrl_webapi.exception.exceptions.MissingParameterException;
import com.ctrlcutter.api.ctrl_webapi.exception.exceptions.NoCustomerException;
import com.ctrlcutter.api.ctrl_webapi.helper.ExistingParameters;
import com.ctrlcutter.api.ctrl_webapi.helper.LoginForm;
import com.ctrlcutter.api.ctrl_webapi.models.Customer;
import com.ctrlcutter.api.ctrl_webapi.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SessionDTO> signup(@RequestBody Customer customer) {

        if (StringUtils.isEmpty(customer.getUsername()) | StringUtils.isEmpty(customer.getEmail()) | StringUtils.isEmpty(customer.getPassword())) {
            throw new MissingParameterException("Missing signup parameters");
        }

        ExistingParameters parameters = this.customerService.parametersExist(customer.getUsername(), customer.getEmail());

        if (parameters.areExisting()) {
            throw new ExistingParameterException("Parameters already existing", parameters.getParameters());
        }

        SessionDTO session = new SessionDTO(this.customerService.createCustomer(customer));

        return new ResponseEntity<>(session, HttpStatus.OK);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SessionDTO> login(@RequestBody LoginForm loginForm) {

        if (StringUtils.isEmpty(loginForm.getEmail()) | StringUtils.isEmpty(loginForm.getPassword())) {
            throw new MissingParameterException("Missing login parameters");
        }

        if (!this.customerService.customerExists(loginForm.getEmail())) {
            throw new NoCustomerException("No customer with matching credentials found");
        }

        SessionDTO session = new SessionDTO(this.customerService.loginCustomer(loginForm));

        if (session.getSessionKey() != null) {
            return new ResponseEntity<>(session, HttpStatus.OK);
        } else {
            throw new AuthenticationException("Login failed. Wrong password");
        }
    }

    @DeleteMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> logout(@RequestHeader Map<String, String> header) {
        String sessionKey = header.get("sessionkey");

        if (sessionKey == null) {
            throw new MissingParameterException("Missing session key");
        }

        this.customerService.logoutCustomer(sessionKey);

        return new ResponseEntity<>("Successfully logged out and deleted all sessions", HttpStatus.OK);
    }

    @GetMapping(value = "/customerData", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> customerData(@RequestHeader Map<String, String> header) {
        String sessionKey = header.get("sessionkey");

        if (sessionKey == null) {
            throw new MissingParameterException("Missing session key");
        }

        CustomerDTO customer = this.customerService.getCustomerData(sessionKey);

        if (customer == null) {
            throw new AuthenticationException("Invalid session");
        } else {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }
}
