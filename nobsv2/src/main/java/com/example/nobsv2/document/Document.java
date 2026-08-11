package com.example.nobsv2.document;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "created_by_user_id")
    private User createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    //un document poate avea mai multe versiuni
    @OneToMany(mappedBy = "document")
    private List<DocumentVersion> versions;

    public Document() {
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<DocumentVersion> getVersions() {
        return versions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setVersions(List<DocumentVersion> versions) {
        this.versions = versions;
    }
}