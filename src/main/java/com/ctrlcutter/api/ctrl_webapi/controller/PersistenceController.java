package com.ctrlcutter.api.ctrl_webapi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ctrlcutter.api.ctrl_webapi.dto.AnonymizedScriptDTO;
import com.ctrlcutter.api.ctrl_webapi.exception.exceptions.AuthenticationException;
import com.ctrlcutter.api.ctrl_webapi.exception.exceptions.MissingParameterException;
import com.ctrlcutter.api.ctrl_webapi.models.Shortcut;
import com.ctrlcutter.api.ctrl_webapi.services.PersistenceService;
import com.ctrlcutter.api.ctrl_webapi.services.SessionService;

@RestController
@RequestMapping("/scripts")
public class PersistenceController {

    private final SessionService sessionService;
    private final PersistenceService persistenceService;

    @Autowired
    public PersistenceController(SessionService sessionService, PersistenceService persistenceService) {
        this.sessionService = sessionService;
        this.persistenceService = persistenceService;
    }

    @PostMapping(value = "/saveAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveAnonymizedScripts(@RequestHeader Map<String, String> header, @RequestBody List<AnonymizedScriptDTO> scripts) {
        String sessionKey = header.get("sessionkey");

        if (sessionKey == null) {
            throw new MissingParameterException("Missing session key");
        }

        if (this.sessionService.checkSessionValidity(sessionKey)) {
            this.persistenceService.saveShortcuts(sessionKey, scripts);

            return new ResponseEntity<>("Successfully saved all scripts", HttpStatus.OK);
        } else {
            throw new AuthenticationException("Invalid session");
        }
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Shortcut>> getAnonymizedScripts(@RequestHeader Map<String, String> header) {
        String sessionKey = header.get("sessionkey");

        if (sessionKey == null) {
            throw new MissingParameterException("Missing session key");
        }

        if (this.sessionService.checkSessionValidity(sessionKey)) {
            List<Shortcut> shortcuts = this.persistenceService.getAllShortcuts(sessionKey);

            return new ResponseEntity<>(shortcuts, HttpStatus.OK);
        } else {
            throw new AuthenticationException("Invalid session");
        }
    }
}
