package com.beam.uploadfile.suspiciousactivity;

import com.beam.uploadfile.base.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@EqualsAndHashCode(callSuper = true)
@Data
@Document
public class SuspiciousActivity extends Base {
    private String origin;
    private String category;
    private String potName;
    private Object payload;
    private LocalDateTime date;
}
