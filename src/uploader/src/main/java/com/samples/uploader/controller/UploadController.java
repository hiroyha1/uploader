package com.samples.uploader.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import com.samples.uploader.service.BlobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("upload")
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
    public void post(@RequestParam("file") MultipartFile file) {
        try {
            InputStream data = file.getInputStream();
            long length = file.getSize();
            String blobName = generateBlobName();
            blobService.upload(CONTANER_NAME, blobName, data, length);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
