package org.acme.travels.services;

import org.acme.travels.springboot.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AuditService {

    private static final Logger logger = LoggerFactory.getLogger(AuditService.class);

    public User auditUser(User user) {

        logger.info("User {} is being processed", user.toString());

        return user;
    }
}
