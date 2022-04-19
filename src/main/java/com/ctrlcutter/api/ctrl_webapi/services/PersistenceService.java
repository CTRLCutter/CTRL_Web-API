package com.ctrlcutter.api.ctrl_webapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctrlcutter.api.ctrl_webapi.dto.AnonymizedScriptDTO;
import com.ctrlcutter.api.ctrl_webapi.mapper.AnonymizedScriptDTOToShortcutMapper;
import com.ctrlcutter.api.ctrl_webapi.models.Customer;
import com.ctrlcutter.api.ctrl_webapi.models.Shortcut;
import com.ctrlcutter.api.ctrl_webapi.repositories.ShortcutRepository;

@Service
public class PersistenceService {

    private final ShortcutRepository shortcutRepository;
    private final SessionService sessionService;
    private final AnonymizedScriptDTOToShortcutMapper anonymizedScriptDTOToShortcutMapper;

    @Autowired
    public PersistenceService(ShortcutRepository shortcutRepository, SessionService sessionService,
            AnonymizedScriptDTOToShortcutMapper anonymizedScriptDTOToShortcutMapper) {
        this.shortcutRepository = shortcutRepository;
        this.sessionService = sessionService;
        this.anonymizedScriptDTOToShortcutMapper = anonymizedScriptDTOToShortcutMapper;
    }

    @Transactional
    public void saveShortcuts(String sessionKey, List<AnonymizedScriptDTO> scripts) {
        Customer customer = this.sessionService.getCustomerBySessionKey(sessionKey);

        this.deleteOldShortcuts(customer);

        List<Shortcut> shortcuts = this.anonymizedScriptDTOToShortcutMapper.mapListOfScripts(scripts, customer);

        this.shortcutRepository.saveAll(shortcuts);
    }

    @Transactional
    public void deleteOldShortcuts(Customer customer) {
        this.shortcutRepository.deleteShortcutsByCustomer(customer);
    }

    public List<Shortcut> getAllShortcuts(String sessionKey) {
        Customer customer = this.sessionService.getCustomerBySessionKey(sessionKey);

        return this.shortcutRepository.getShortcutsByCustomer(customer);
    }
}
