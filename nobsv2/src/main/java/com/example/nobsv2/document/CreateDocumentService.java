package com.example.nobsv2.document;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CreateDocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentVersionRepository versionRepository;
    private final UserRepository userRepository;

    public CreateDocumentService(DocumentRepository documentRepository,
                                 DocumentVersionRepository versionRepository,
                                 UserRepository userRepository) {
        this.documentRepository = documentRepository;
        this.versionRepository = versionRepository;
        this.userRepository = userRepository;
    }

    // creez un document nou
    public Document execute(String title, String description, MultipartFile file, String uploadedBy) throws IOException {
        User uploader = null;
        if (uploadedBy != null && !uploadedBy.isBlank()) {
            uploader = userRepository.findByUsername(uploadedBy);
        }

        Document doc = new Document();
        doc.setTitle(title);
        doc.setDescription(description);
        doc.setCreatedAt(LocalDateTime.now());
        doc.setCreatedBy(uploader);

        Document savedDoc = documentRepository.save(doc);

        // creez si salvez prima versiune (v1)
        DocumentVersion version = new DocumentVersion();
        version.setDocument(savedDoc);
        version.setVersionNumber(1);
        version.setFileData(file.getBytes());
        version.setOriginalFileName(file.getOriginalFilename());
        version.setContentType(file.getContentType());
        version.setUploadedAt(LocalDateTime.now());
        version.setSize(file.getSize());
        version.setUploadedBy(uploader);

        versionRepository.save(version);

        return savedDoc;
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public DocumentVersion getVersion(Long versionId) {
        return versionRepository.findById(versionId)
                .orElseThrow(() -> new RuntimeException("Versiunea nu a fost gasita!"));
    }

    // adaug o versiune noua fara suprascriere
    public DocumentVersion addVersion(Long documentId, MultipartFile file, String uploadedBy) throws IOException {
        Document doc = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        User uploader = null;
        if (uploadedBy != null && !uploadedBy.isBlank()) {
            uploader = userRepository.findByUsername(uploadedBy);
        }

        int nextVersionNumber = doc.getVersions().size() + 1;

        DocumentVersion version = new DocumentVersion();
        version.setDocument(doc);
        version.setVersionNumber(nextVersionNumber);
        version.setFileData(file.getBytes());
        version.setOriginalFileName(file.getOriginalFilename());
        version.setContentType(file.getContentType());
        version.setUploadedAt(LocalDateTime.now());
        version.setSize(file.getSize());
        version.setUploadedBy(uploader);

        return versionRepository.save(version);
    }
}