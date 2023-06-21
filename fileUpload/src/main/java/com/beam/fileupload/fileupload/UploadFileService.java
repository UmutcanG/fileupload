package com.beam.fileupload.fileupload;

import com.beam.fileupload.account.Account;
import com.beam.fileupload.account.AccountRepository;
import com.beam.fileupload.disk.DiskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UploadFileService {
    private final DiskService diskService;
    private final AccountRepository accountRepository;
    private final Path fileUploadLocation;
    private final UploadFileRepository uploadFileRepository;

    @Autowired
    public UploadFileService(DiskService diskService, AccountRepository accountRepository, UploadFile fileStorageProperties, UploadFileRepository uploadFileRepository) {
        this.diskService = diskService;
        this.accountRepository = accountRepository;
        this.fileUploadLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        this.uploadFileRepository = uploadFileRepository;

        try {
            Files.createDirectories(this.fileUploadLocation);
        } catch (Exception e) {
            throw new RuntimeException("Cannot create the directory where you want.\", e");
        }
    }

    public UploadFile uploadFile(MultipartFile file, Account account) {
        try {
            String filename = diskService.saveFile(account.getId(), file.getBytes());
            if (filename.contains("..")) {
                throw new RuntimeException("Sorry!");
            }

            UploadFile uploadFile = new UploadFile();
            uploadFile.setId(UUID.randomUUID().toString());
            Path targetLocation = this.fileUploadLocation.resolve(filename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return uploadFileRepository.save(uploadFile);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }
}


/*
        public UploadFile uploadFile(MultipartFile file, Account account)  {
        try {
            String filename = diskService.saveFire(account.getId(), file.getBytes());
            UploadFile uploadFile = new UploadFile().setName()
        }catch (IOException exc) {
            throw new RuntimeException("Something went wrong writing file");
            return null;
        }
        return filename;
    }

    String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
 */