package com.beam.uploadfile.diskService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DiskService {
    public String saveFile(String id,byte[] data) throws IOException {
        String filename = UUID.randomUUID().toString();
        String base = "C:\\Users\\umutcan.guclu\\IdeaProjects\\uploadFile\\upload";
        if (!Files.exists(Paths.get(base + id))) {
            Files.createDirectory(Paths.get(base + id));
        }
        Path path = Paths.get(base + id + File.separator + filename);
        Files.write(path, data);
        return filename;
    }
}