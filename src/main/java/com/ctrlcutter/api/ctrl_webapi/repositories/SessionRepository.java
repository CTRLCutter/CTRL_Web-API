package com.ctrlcutter.api.ctrl_webapi.repositories;

import com.ctrlcutter.api.ctrl_webapi.models.Customer;
import com.ctrlcutter.api.ctrl_webapi.models.Session;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {


    @Query(value = "SELECT EXISTS (SELECT * FROM sessions WHERE session_key = ?1)", nativeQuery = true)
    long existsSession(String session_key);

    @Query(value = "SELECT EXISTS (SELECT session_key FROM sessions WHERE customer_id = ?1)", nativeQuery = true)
    long existsSessionByCustomer(Customer customer);

    @Query(value = "SELECT * FROM sessions WHERE session_key = ?1", nativeQuery = true)
    Session getSession(String session_key);

    @Query(value = "SELECT * FROM sessions WHERE customer_id = ?1", nativeQuery = true)
    Session getSessionByCustomer(Customer customer);

    @Modifying
    @Query(value = "DELETE FROM sessions WHERE session_key = ?1", nativeQuery = true)
    void deleteSession(String session_key);

}
