package com.ctrlcutter.api.ctrl_webapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ctrlcutter.api.ctrl_webapi.models.Shortcut;

@Repository
public interface ScriptRepository extends CrudRepository<Shortcut, Long> {

}
