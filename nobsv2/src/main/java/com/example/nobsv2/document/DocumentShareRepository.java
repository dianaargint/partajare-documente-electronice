package com.example.nobsv2.document;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentShareRepository extends JpaRepository<DocumentShare, Long> {
    List<DocumentShare> findBySharedWithUser(User user);
    boolean existsByDocumentAndSharedWithUser(Document document, User sharedWithUser);
}
