package com.beam.uploadfile.fileUpload;

import ch.qos.logback.core.util.FileUtil;
import com.beam.uploadfile.diskService.DiskService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

import static org.apache.tomcat.util.http.fileupload.FileUtils.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class FileUploadController {
    private final FileUploadService fileUploadService;
    private final DiskService diskService;
    private boolean isValidExtension(String fileExtension) {
        List<String> allowedExtensions = Arrays.asList(".txt", ".png", ".gif",".jpg");
        return allowedExtensions.contains(fileExtension.toLowerCase());
    }


    @PostMapping("/saveUser")
    public FileUpload saveUser(FileUpload fileUpload, @RequestParam("file") MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename != null ? FilenameUtils.getExtension(originalFilename) : "";
        if (!isValidExtension(fileExtension)){
            return fileUploadService.saveUser(fileUpload,file);
        }else {
            throw new RuntimeException("Invalid extension");
        }
    }
}
