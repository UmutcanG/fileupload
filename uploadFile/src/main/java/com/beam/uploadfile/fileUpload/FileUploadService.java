package com.beam.uploadfile.fileUpload;

import com.beam.uploadfile.diskService.DiskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
@RequiredArgsConstructor
@Service
public class FileUploadService {
    private final FileUploadRepository fileUploadRepository;
    private final DiskService diskService;

    public FileUpload saveUser(FileUpload fileUpload, MultipartFile file) throws IOException {
        String filename = diskService.saveFile(fileUpload.getId(), file.getBytes());
        FileUpload upload = new FileUpload();
        upload.setFileName(file.getOriginalFilename());
        upload.setFilePath(filename);
        upload.setId(UUID.randomUUID().toString());
        return fileUploadRepository.save(upload);
    }
}


/*
public void saveUser(Account account, MultipartFile file) throws IOException {
        String fileName = diskService.saveFile(account.getId(),file.getBytes());
        if (accountRepository.findByName(account.getName()) != null)
            throw new RuntimeException("already have this name");
        account.setId(UUID.randomUUID().toString());
        accountRepository.save(account);
    }
 */

