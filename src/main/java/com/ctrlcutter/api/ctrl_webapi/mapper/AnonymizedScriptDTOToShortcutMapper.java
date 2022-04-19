package com.ctrlcutter.api.ctrl_webapi.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ctrlcutter.api.ctrl_webapi.dto.AnonymizedScriptDTO;
import com.ctrlcutter.api.ctrl_webapi.models.Customer;
import com.ctrlcutter.api.ctrl_webapi.models.Shortcut;

@Component
public class AnonymizedScriptDTOToShortcutMapper {

    public AnonymizedScriptDTOToShortcutMapper() {}

    public List<Shortcut> mapListOfScripts(List<AnonymizedScriptDTO> anonymizedScriptDTOS, Customer customer) {
        List<Shortcut> shortcuts = new ArrayList<>();

        for (AnonymizedScriptDTO dto : anonymizedScriptDTOS) {
            shortcuts.add(this.mapScript(dto, customer));
        }

        return shortcuts;
    }

    private Shortcut mapScript(AnonymizedScriptDTO anonymizedScriptDTO, Customer customer) {
        Shortcut shortcut = new Shortcut();

        shortcut.setCommand(anonymizedScriptDTO.getCommand());
        shortcut.setKeyboardKey(anonymizedScriptDTO.getKey());
        if (anonymizedScriptDTO.getModifierKeys() != null) {
            shortcut.setModifierKeys(Arrays.asList(anonymizedScriptDTO.getModifierKeys()));
        } else {
            shortcut.setModifierKeys(null);
        }
        if (anonymizedScriptDTO.getParameters() != null) {
            shortcut.setParameters(Arrays.asList(anonymizedScriptDTO.getParameters()));
        } else {
            shortcut.setParameters(null);
        }
        shortcut.setCustomer(customer);

        return shortcut;
    }
}
