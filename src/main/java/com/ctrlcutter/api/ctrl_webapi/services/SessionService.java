package com.ctrlcutter.api.ctrl_webapi.services;

import com.ctrlcutter.api.ctrl_webapi.models.Customer;
import com.ctrlcutter.api.ctrl_webapi.models.Session;
import com.ctrlcutter.api.ctrl_webapi.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    //TODO delete Session

    //TODO check if Session exists
}
