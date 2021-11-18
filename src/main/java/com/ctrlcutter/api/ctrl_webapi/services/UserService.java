package com.ctrlcutter.api.ctrl_webapi.services;

import com.ctrlcutter.api.ctrl_webapi.helper.ExistingParameters;
import com.ctrlcutter.api.ctrl_webapi.models.User;
import com.ctrlcutter.api.ctrl_webapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;

@Service
public class UserService {

    private final UserRepository userRepository;
    BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ExistingParameters userExists(String username, String email) {
        ExistingParameters existingParameters = new ExistingParameters();

        if (this.userRepository.existsUserByUsername(username)) {
            existingParameters.setExisting();
            existingParameters.addParameter("username", username);
        }

        if (this.userRepository.existsUserByEmail(email)) {
            existingParameters.setExisting();
            existingParameters.addParameter("email", email);
        }

        return existingParameters;
    }

    public void createUser(User user) {
        user.setPassword(this.bCrypt.encode(user.getPassword()));
        user.setRegistration_date(new Timestamp(Calendar.getInstance().getTime().getTime()));

        this.userRepository.save(user);
    }
}
