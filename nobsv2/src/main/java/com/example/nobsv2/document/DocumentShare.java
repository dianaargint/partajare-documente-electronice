package com.example.nobsv2.document;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "document_shares")
public class DocumentShare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    private Document document;

    @ManyToOne
    @JoinColumn(name = "shared_with_user_id", nullable = false)
    private User sharedWithUser;

    @ManyToOne
    @JoinColumn(name = "shared_by_user_id", nullable = false)
    private User sharedByUser;

    @Column(name = "shared_at")
    private LocalDateTime sharedAt = LocalDateTime.now();

    public DocumentShare() {

    }

    public long getId() {
        return id;
    }

    public Document getDocument() {
        return document;
    }

    public User getSharedWithUser() {
        return sharedWithUser;
    }

    public User getSharedByUser() {
        return sharedByUser;
    }

    public LocalDateTime getSharedAt() {
        return sharedAt;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public void setSharedWithUser(User sharedWithUser) {
        this.sharedWithUser = sharedWithUser;
    }

    public void setSharedByUser(User sharedByUser) {
        this.sharedByUser = sharedByUser;
    }

    public void setSharedAt(LocalDateTime sharedAt) {
        this.sharedAt = sharedAt;
    }
}
