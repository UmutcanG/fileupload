package com.beam.fileupload.fileupload;

import com.beam.fileupload.Base.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;


@EqualsAndHashCode(callSuper = true)
@Data
@Document("uploadFile")
@Component
@ConfigurationProperties(prefix = "file")
public class UploadFile extends Base {

    private String uploadDir;
    private String name;
}
