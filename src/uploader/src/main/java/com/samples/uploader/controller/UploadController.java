package com.samples.uploader.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import com.samples.uploader.dto.DataFileDto;
import com.samples.uploader.service.BlobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController
@RequestMapping("upload")
@Tag(name = "Uploader", description = "File upload API")
public class UploadController {
    private final static SimpleDateFormat DF = new SimpleDateFormat("yyyyMMdd_HHmmss");
    private final static String CONTANER_NAME = System.getenv("STORAGE_CONTAINER_NAME");

    @Autowired
    private BlobService blobService;

    private String generateBlobName() {
        Date now = Date.from(Instant.now());
        String blobName = String.format("%s.dat", DF.format(now));
        return blobName;
    }

    @RequestMapping(consumes={"multipart/form-data"}, method=RequestMethod.POST)
    @Operation(summary = "Upload file", tags = { "uploader" })
    public DataFileDto post(@RequestParam("file") MultipartFile file) {
        try {
            InputStream data = file.getInputStream();
            long length = file.getSize();
            String blobName = generateBlobName();
            DataFileDto dataFile = blobService.upload(CONTANER_NAME, blobName, data, length);
            return dataFile;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
