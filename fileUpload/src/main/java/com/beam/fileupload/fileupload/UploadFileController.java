package com.beam.fileupload.fileupload;

import com.beam.fileupload.account.Account;
import com.beam.fileupload.disk.DiskService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequiredArgsConstructor
@RestController
public class UploadFileController {
    private final DiskService diskService;

    @Autowired
    private UploadFileService fileUploadService;

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file")MultipartFile file, Account account) {
        String fileName = String.valueOf(fileUploadService.uploadFile(file,account));
        ServletUriComponentsBuilder.fromCurrentContextPath().path(fileName).toUriString();
        return "upload successful";
    }

}
