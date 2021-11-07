package com.ctrlcutter.api.ctrl_webapi.controller;

import com.ctrlcutter.api.ctrl_webapi.helper.ExistingParameters;
import com.ctrlcutter.api.ctrl_webapi.models.User;
import com.ctrlcutter.api.ctrl_webapi.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/signup", produces = "application/json")
    public ResponseEntity<Object> signup(@RequestBody User user) {

        if (StringUtils.isEmpty(user.getUsername()) | StringUtils.isEmpty(user.getEmail()) | StringUtils.isEmpty(user.getPassword())) {
            return new ResponseEntity<>("Missing data(username, email or password)!", HttpStatus.BAD_REQUEST);
        }

        ExistingParameters parameters = this.userService.userExists(user.getUsername(), user.getEmail());

        if (parameters.areExisting()) {
            return new ResponseEntity<>(parameters.getParameters(), HttpStatus.BAD_REQUEST);
        }

        this.userService.createUser(user);

        return new ResponseEntity<>("User created!", HttpStatus.OK);
    }
}
