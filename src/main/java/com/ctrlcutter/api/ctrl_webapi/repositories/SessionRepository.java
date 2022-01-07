package com.ctrlcutter.api.ctrl_webapi.repositories;

import com.ctrlcutter.api.ctrl_webapi.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
    
}
