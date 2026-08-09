package com.example.nobsv2.document;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "documents")
@Data
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    //un document poate avea mai multe versiuni
    @OneToMany(mappedBy = "document")
    private List<DocumentVersion> versions;
}