package com.base.controller;

import com.base.service.PhotoService;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {


    @Autowired
    private PhotoService photoService;

//    @PreAuthorize(value = "@authorizedConfig.preAuth('BANK_VIEW')")
    @PostMapping("/upload")
    public ResponseEntity<String> uploadPhoto(@RequestParam("title") String title,
                                              @RequestParam("file") MultipartFile file) {
        try {
            String id = photoService.uploadPhoto(title, file);
            return ResponseEntity.ok(id);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @SneakyThrows
    @GetMapping("/view/{id}")
//    @PreAuthorize(value = "@authorizedConfig.preAuth('BANK_VIEW')")
    public ResponseEntity<InputStreamResource> getPhoto(@PathVariable String id) {
        GridFSFile file = photoService.getPhoto(id);
        if (file != null) {
            GridFsResource resource = photoService.getPhotoResource(file);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(resource.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(new InputStreamResource(resource.getInputStream()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize(value = "@authorizedConfig.preAuth('VIEW')")
    @PostMapping("/test-view")
    public ResponseEntity<String> test() {
        // Xử lý upload ở đây
        return ResponseEntity.ok("File uploaded successfully");
    }
    @PreAuthorize(value = "@authorizedConfig.preAuth('DELETE')")
    @PostMapping("/test-delete")
    public ResponseEntity<String> delete() {
        // Xử lý upload ở đây
        return ResponseEntity.ok("Fdelete successfully");
    }
}
