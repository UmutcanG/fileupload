package com.beam.fileupload.disk;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DiskService {

    public String saveFile(String id, byte[] data) throws IOException {
        String fileName = UUID.randomUUID().toString();
        String filePath = ServletUriComponentsBuilder.fromCurrentContextPath().path(fileName).toUriString();
        Path path = Paths.get(filePath + fileName);
        Files.write(path,data);
        return fileName;
    }
}
