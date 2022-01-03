package com.ctrlcutter.api.ctrl_webapi;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = CtrlWebApiApplication.class)
@AutoConfigureMockMvc
public class SignUpTest {
}
