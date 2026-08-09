package com.example.nobsv2.document;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin(origins = "*")
public class DocumentController {

    private final CreateDocumentService createDocumentService;

    public DocumentController(CreateDocumentService createDocumentService) {
        this.createDocumentService = createDocumentService;
    }

    //incarcare doc nou si creare versiune 1
    @PostMapping
    public ResponseEntity<?> createDocument(
            @RequestParam("title") String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "uploadedBy", required = false) String uploadedBy) {
        try {
            Document savedDoc = createDocumentService.execute(title, description, file, uploadedBy);
            return ResponseEntity.ok(savedDoc);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Eroare server: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Document>> getAllDocuments() {
        return ResponseEntity.ok(createDocumentService.getAllDocuments());
    }

    //descarc fisierul
    @GetMapping("/versions/{versionId}/download")
    public ResponseEntity<byte[]> downloadVersion(@PathVariable Long versionId) {
        DocumentVersion version = createDocumentService.getVersion(versionId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(version.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + version.getOriginalFileName() + "\"")
                .body(version.getFileData());
    }

    //incarcarea unei versiuni noi
    @PostMapping("/{documentId}/versions")
    public ResponseEntity<?> addVersion(
            @PathVariable Long documentId,
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "uploadedBy", required = false) String uploadedBy) {
        try {
            DocumentVersion savedVersion = createDocumentService.addVersion(documentId, file, uploadedBy);
            return ResponseEntity.ok(savedVersion);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Eroare la adaugarea versiunii: " + e.getMessage());
        }
    }

}