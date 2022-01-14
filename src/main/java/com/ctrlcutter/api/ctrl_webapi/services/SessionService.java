package com.ctrlcutter.api.ctrl_webapi.services;

import com.ctrlcutter.api.ctrl_webapi.models.Customer;
import com.ctrlcutter.api.ctrl_webapi.models.Session;
import com.ctrlcutter.api.ctrl_webapi.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public String createSession(Customer customer) {
        UUID uuid = UUID.randomUUID();
        Timestamp creationTime = new Timestamp(Calendar.getInstance().getTime().getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(creationTime);
        calendar.add(Calendar.DAY_OF_WEEK, 14);
        Timestamp validUntil = new Timestamp(calendar.getTime().getTime());

        Session session = new Session(uuid.toString(), customer, creationTime, validUntil);

        this.sessionRepository.save(session);

        return uuid.toString();
    }

    @Transactional
    public boolean checkSessionValidity(String sessionKey) {
        if (this.sessionRepository.existsSession(sessionKey) == 1) {
            Timestamp sessionValidUntil = this.sessionRepository.getSession(sessionKey).getValid_until();

            if (sessionValidUntil.after(new Timestamp(Calendar.getInstance().getTime().getTime()))) {
                return true;
            } else {
                this.deleteSession(sessionKey);
                return false;
            }
        }
        return false;
    }

    @Transactional
    public String getSessionKeyByCustomer(Customer customer) {
        if (this.sessionRepository.existsSessionByCustomer(customer) == 1) {
            String sessionKey = this.sessionRepository.getSessionByCustomer(customer).getSession_key();

            if (this.checkSessionValidity(sessionKey)) {
                return sessionKey;
            } else {
                return this.createSession(customer);
            }
        }
        return this.createSession(customer);
    }

    public Customer getCustomerBySessionKey(String sessionKey) {
        return this.sessionRepository.getSession(sessionKey).getCustomer();
    }

    @Transactional
    public void deleteSession(String sessionKey) {
        this.sessionRepository.deleteSession(sessionKey);
    }
}
