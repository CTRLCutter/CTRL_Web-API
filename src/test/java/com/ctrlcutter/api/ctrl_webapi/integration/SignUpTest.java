package com.ctrlcutter.api.ctrl_webapi.integration;

import com.ctrlcutter.api.ctrl_webapi.CtrlWebApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(classes = CtrlWebApiApplication.class)
@AutoConfigureMockMvc
public class SignUpTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username = "ctrlcutter", password = "test123")
    public void missingDataTest() throws Exception {
        this.mockMvc.perform(post("/customer/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"mail@seufert.tech\", \"password\": \"test123\"}")
        ).andExpect(status().isBadRequest()).andExpect(content().string(containsString("Missing data(username, email or password)!")));

        this.mockMvc.perform(post("/customer/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"Lorenz\", \"password\": \"test123\"}")
        ).andExpect(status().isBadRequest()).andExpect(content().string(containsString("Missing data(username, email or password)!")));

        this.mockMvc.perform(post("/customer/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"Lorenz\", \"email\": \"mail@seufert.tech\"}")
        ).andExpect(status().isBadRequest()).andExpect(content().string(containsString("Missing data(username, email or password)!")));
    }

    @Test
    @WithMockUser(username = "ctrlcutter", password = "test123")
    public void createCustomerTest() throws Exception {
        this.mockMvc.perform(post("/customer/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"Lorenz\", \"email\": \"mail@seufert.tech\", \"password\": \"test123\"}")
        ).andExpect(status().isOk()).andExpect(content().string(containsString("Customer created!")));

        this.mockMvc.perform(post("/customer/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"Tom\", \"email\": \"tom@seufert.tech\", \"password\": \"password12356789test100\"}")
        ).andExpect(status().isOk()).andExpect(content().string(containsString("Customer created!")));
    }
}
