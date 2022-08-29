package com.example.lcms_app_full.controller;

import com.example.lcms_app_full.dto.ApiResponse;
import com.example.lcms_app_full.entity.Attachment;
import com.example.lcms_app_full.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentService attachmentService;

    //file System
    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> upload(MultipartHttpServletRequest request) throws IOException {
       ApiResponse apiResponse=attachmentService.upload(request);

       return ResponseEntity.status(apiResponse.isSuccess()? 201:409).body(apiResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getFile(@PathVariable UUID id) throws MalformedURLException {
        return attachmentService.getAll(id);
    }

    //file db
    @PostMapping("/uploadDb")
    public ResponseEntity<?> uploadDB(MultipartHttpServletRequest request){
        ApiResponse response=attachmentService.uploadDb(request);
        return ResponseEntity.status(response.isSuccess()?201:404).body(response);
    }
    @GetMapping("/downloadDb/{attachmentId}")
    public ResponseEntity<?> downloadDB(@PathVariable(value = "attachmentId") UUID id){
        return attachmentService.downloadDB(id);
    }
}
