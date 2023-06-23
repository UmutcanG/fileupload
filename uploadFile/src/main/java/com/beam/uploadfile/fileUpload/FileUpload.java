package com.beam.uploadfile.fileUpload;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Data
@Document
@TypeAlias("uploadFile")
@Component
@ConfigurationProperties(prefix = "file")
public class FileUpload {
    @Id
    private String id;
    private String fileName;
    private String filePath;
}
