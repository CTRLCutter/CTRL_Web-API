package com.ctrlcutter.api.ctrl_webapi.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ctrlcutter.api.ctrl_webapi.models.Customer;
import com.ctrlcutter.api.ctrl_webapi.models.Shortcut;

@Repository
public interface ShortcutRepository extends CrudRepository<Shortcut, Long> {

    @Transactional
    void deleteShortcutsByCustomer(Customer customer);

    List<Shortcut> getShortcutsByCustomer(Customer customer);
}
