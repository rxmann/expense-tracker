package com.codex.utils;

import com.codex.exceptions.InvalidRequestException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileService {

    private static final String STORAGE_DIRECTORY = "C:\\Users\\rxman\\Downloads";


    public void saveFile (MultipartFile fileToSave) throws IOException {

        if (fileToSave == null || fileToSave.isEmpty()  ) {
            throw new InvalidRequestException("File is null!");
        }

        var targetFile = new File(STORAGE_DIRECTORY + File.separator + fileToSave.getOriginalFilename());

        System.out.println(targetFile.getParent() + " : " + STORAGE_DIRECTORY);

        if (!Objects.equals(targetFile.getParent(), STORAGE_DIRECTORY)) {
            throw new SecurityException("Invalid arguments: filename!");
        }

        Files.copy(fileToSave.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);



    }


}
