package com.beam.fileupload.Base;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Base {
    @Id
    private String id;
}
