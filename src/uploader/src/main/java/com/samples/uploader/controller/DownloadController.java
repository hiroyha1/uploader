package com.samples.uploader.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.samples.uploader.service.BlobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController
@RequestMapping("download")
@Tag(name = "Downloader", description = "File download API")
public class DownloadController {
    private final static String CONTAINER_NAME = System.getenv("STORAGE_CONTAINER_NAME");

    @Autowired
    private BlobService blobService;

    @RequestMapping(value="/{blobName}", method=RequestMethod.GET)
    @Operation(summary = "Download file", tags = { "downloader" })
    public void get(final HttpServletResponse response, @PathVariable() String blobName) {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + blobName);
        try {
            blobService.download(CONTAINER_NAME, blobName, response.getOutputStream());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
