package com.example.lcms_app_full.service;

import com.example.lcms_app_full.dto.ApiResponse;
import com.example.lcms_app_full.entity.Attachment;
import com.example.lcms_app_full.entity.AttachmentContent;
import com.example.lcms_app_full.exception.ResourceNotFoundException;
import com.example.lcms_app_full.repository.AttachmentContentRepository;
import com.example.lcms_app_full.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    private final Path root=Paths.get("C:\\Users\\Doniyor\\IdeaProjects\\lcms_app_full\\src\\main\\resources\\upload");
    public ApiResponse upload(MultipartHttpServletRequest request) throws IOException {

        Iterator<String> fileNames = request.getFileNames();
        Attachment save=null;
        while(fileNames.hasNext()){
            Attachment attachment=new Attachment();
            MultipartFile file = request.getFile(fileNames.next());

            attachment.setFileName(file.getOriginalFilename());
            attachment.setSize(file.getSize());
            attachment.setContenType(file.getContentType());
            Files.copy(file.getInputStream(),this.root.resolve(file.getOriginalFilename()));
            attachment.setUrl(this.root+file.getOriginalFilename());
             save = attachmentRepository.save(attachment);

        }
        return ApiResponse.builder().data(save).message("Added").success(true).build();
    }

    public ResponseEntity<?> getAll(UUID id) throws MalformedURLException {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("File", "id", id));
        Path file=root.resolve(attachment.getFileName());
        Resource resource=new UrlResource(file.toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(attachment.getContenType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; fileName=\""+attachment.getFileName())
                .body(resource);
    }

    @SneakyThrows
    public ApiResponse uploadDb(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        while (fileNames.hasNext()){
            MultipartFile file = request.getFile(fileNames.next());
            Attachment attachment=new Attachment();
            attachment.setFileName(file.getOriginalFilename());
            attachment.setSize(file.getSize());
            attachment.setContenType(file.getContentType());
            Attachment save = attachmentRepository.save(attachment);
            AttachmentContent attachmentContent=new AttachmentContent();
            attachmentContent.setAttachment(save);
            attachmentContent.setBytes(file.getBytes());
            attachmentContentRepository.save(attachmentContent);
        }
        return ApiResponse.builder().message("Added").success(true).build();
    }

    public ResponseEntity<?> downloadDB(UUID id) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("File", "id", id));
        AttachmentContent attachmentContent = attachmentContentRepository.findByAttachment(attachment);
                return ResponseEntity.ok()
                        .contentType(MediaType.valueOf(attachment.getContenType()))
                        .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; fileName=\""+attachment.getFileName())
                        .body(attachmentContent.getBytes());
    }
}
