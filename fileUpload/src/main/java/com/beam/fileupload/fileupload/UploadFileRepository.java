package com.beam.fileupload.fileupload;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UploadFileRepository extends MongoRepository<UploadFile,String> {
}
