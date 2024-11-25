package com.bookso.books.audit;


import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/*
* @author: santhosh kumar
* @description: Auditing Class
 */
@Component("booksAudit")
public class BooksAuditAware implements AuditorAware {
    @Override
    public Optional getCurrentAuditor() {
        return Optional.of("BOOKS_MS");
    }
}
