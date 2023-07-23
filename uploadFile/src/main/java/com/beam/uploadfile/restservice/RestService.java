package com.beam.uploadfile.restservice;

import com.beam.uploadfile.diskservice.DiskService;
import com.beam.uploadfile.jwtservice.JWTService;
import com.beam.uploadfile.fileupload.FileUpload;
import com.beam.uploadfile.suspiciousactivity.SuspiciousActivity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class RestService {

    private final RestTemplate restTemplate;
    private final JWTService jwtService;
    private final DiskService diskService;

    public RestService(RestTemplateBuilder restTemplateBuilder, JWTService jwtService,DiskService diskService) {
        this.restTemplate = restTemplateBuilder.build();
        this.jwtService = jwtService;
        this.diskService = diskService;
    }

    public HttpHeaders generateHeaders() {
        try {
            String jwt = jwtService.generateJWT();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("In-App-Auth-Token", jwt);
            return headers;
        } catch (Exception error) {
            log.error("Error while generating headers: {}", error.getMessage());
            return null;
        }
    }

    public Map<String, Object> generateBody(FileUpload fileUpload) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("targetElementId", fileUpload.getFileName());

        Map<String, Object> map = new HashMap<>();
        map.put("origin", fileUpload.getOrigin());
        map.put("payload", payload);
        map.put("category", "Unrestricted File Upload");
        map.put("potName", "File Upload");
        map.put("date", LocalDateTime.now());
        return map;
    }

    public void post(Map<String, Object> body, HttpHeaders headers) {
        String url = "http://localhost:8080/suspicious/server";
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        this.restTemplate.postForEntity(url, entity, SuspiciousActivity.class);
    }

    public void postSuspiciousFileActivity(FileUpload fileUpload, MultipartFile file) {
        try {
            diskService.saveFile(fileUpload.getId(), file.getBytes());
            HttpHeaders headers = generateHeaders();
            Map<String, Object> body = generateBody(fileUpload);
            post(body, headers);
        } catch (Exception error) {
            log.error("Error while posting suspicious activity: {}", error.getMessage());
        }
    }
}

