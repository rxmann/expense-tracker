package com.codex.controller;


import com.codex.utils.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1")
public class FileManagerController {

    @Autowired
    private FileService fileService;
    private static final Logger logger = Logger.getLogger(FileManagerController.class.getName());

    @PostMapping("/upload-file")
    public boolean uploadFile (@RequestParam("file") MultipartFile file) {
        try {
            fileService.saveFile(file);
            return true;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Exception during upload!", e);
            return false;
        }
    }
}
