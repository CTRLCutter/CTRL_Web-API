package com.ctrlcutter.api.ctrl_webapi.repositories;

import com.ctrlcutter.api.ctrl_webapi.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SessionRepository extends JpaRepository<Session, Long> {

//    boolean existsSessionBySession_key(String session_key);

    @Query(value = "SELECT EXISTS (SELECT * FROM sessions WHERE session_key = ?1)", nativeQuery = true)
    long existsSession(String session_key);

    @Query(value = "SELECT * FROM sessions WHERE session_key = ?1", nativeQuery = true)
    Session getSession(String session_key);

    @Modifying
    @Query(value = "DELETE FROM sessions WHERE session_key = ?1", nativeQuery = true)
    void deleteSession(String session_key);

}
