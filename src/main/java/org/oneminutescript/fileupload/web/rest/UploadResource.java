package org.oneminutescript.fileupload.web.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.security.access.annotation.Secured;
import javax.servlet.http.HttpServletRequest;

import org.oneminutescript.fileupload.service.StorageService;

/**
 * REST controller for managing the uploaded files.
 */
@RestController
@RequestMapping("/api/upload")
public class UploadResource {

    @Autowired
    StorageService storageService;

    List <String> files = new ArrayList <String>();

    @PostMapping("/post")
    public ResponseEntity <String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.store(file);
            files.add(file.getOriginalFilename());
            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @GetMapping("/getallfiles")
    public ResponseEntity <List <String>> getListFiles(Model model) {
        List <String> fileNames = files
            .stream().map(fileName -> MvcUriComponentsBuilder
                .fromMethodName(UploadResource.class, "getFile", fileName).build().toString())
            .collect(Collectors.toList());

        return ResponseEntity.ok().body(fileNames);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity <Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.loadFile(filename);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
            .body(file);
    }
}