package com.example.nobsv2.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "document_versions")
public class DocumentVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "document_id")
    @JsonIgnore
    private Document document;

    @Column(name = "version_number")
    private int versionNumber;

    @Column(name = "original_file_name")
    private String originalFileName;

    @Column(name = "content_type")
    private String contentType;

    private Long size;

    @Lob
    @Column(name = "file_data", columnDefinition = "LONGBLOB")
    @JsonIgnore
    private byte[] fileData;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;

    @Column(name = "uploaded_by")
    private String uploadedBy;

    public DocumentVersion() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public Long getId() {
        return id;
    }

    public Document getDocument() {
        return document;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public String getContentType() {
        return contentType;
    }

    public Long getSize() {
        return size;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }
}